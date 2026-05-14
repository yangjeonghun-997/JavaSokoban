package sokoban.manager.stageSystem;

import sokoban.gameObject.Direction;
import sokoban.gameObject.GameObject;
import sokoban.gameObject.Mage;
import sokoban.gameObject.interaction.Pushable;
import sokoban.manager.Position;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class MoveSystem {

	public PushSystem pushSystem;
	
	public MoveSystem(PushSystem pushSystem) {
		this.pushSystem = pushSystem;
	}
	
	public boolean tryMove(Board board, Mage mage, Direction dir) {
		
		Position playerPos = findPlayer(board, mage);
		
		if (playerPos == null) {
			return false;
		}
		
		int nx = playerPos.x + dir.dx;
		int ny = playerPos.y + dir.dy;
		
		if (!board.isInside(nx,ny)) {
			return false;
		}
		
		Tile currentTile = board.getTile(playerPos.x, playerPos.y);
		Tile nextTile = board.getTile(nx, ny);
		
		if (!nextTile.getTerrain().isWalkable()) {
			return false;
		}
		
		GameObject target = nextTile.getObject();
		
		if (target == null) {
			moveObject(currentTile, nextTile);
			
			mage.setPosition(nx, ny);
			
			return true;
		}
		
		if (target instanceof Pushable) {
			boolean pushed = pushSystem.tryPush(board, nx, ny, dir);
			
			if (pushed) {
				moveObject(currentTile, nextTile);
				
				mage.setPosition(nx, ny);
				
				return true;
			}
		}

		return false;
	}
	
	private void moveObject(Tile from, Tile to) {
		GameObject obj = from.getObject();
		
		to.setObject(obj);
		
		from.setObject(null);
	}
	
	private Position findPlayer(Board board, Mage mage) {
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				
				GameObject object = board.getTile(x, y).getObject();
				
				if (object == mage) {
					return new Position(x, y);
				}
			}
		}
		
		return null;
	}
}
