package sokoban.manager.stageSystem;

import sokoban.gameObject.Direction;
import sokoban.gameObject.GameObject;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class PushSystem {

	public boolean tryPush(Board board, int x, int y, Direction dir) {
		int nx = x + dir.dx;
		int ny = y + dir.dy;
		
		if (!board.isInside(nx, ny)) {
			return false;
		}
		
		Tile currentTile = board.getTile(x, y);
		Tile nextTile = board.getTile(nx, ny);
		
		GameObject pushedObject = currentTile.getObject();
		
		if (!nextTile.getTerrain().isWalkable()) {
			nextTile.getTerrain().fillPit();
			
			currentTile.setObject(null);
			
			return true;
		}
		
		if (nextTile.getObject() != null) {
			return false;
		}
		
		nextTile.setObject(pushedObject);
		
		currentTile.setObject(null);
		
		pushedObject.setPosition(nx, ny);
		
		return true;
	}
}
