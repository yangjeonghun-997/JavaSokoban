package sokoban.manager.stageSystem;

import sokoban.gameObject.GameObject;
import sokoban.gameObject.Magic;
import sokoban.gameObject.interaction.Destructible;
import sokoban.manager.Position;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class AttackSystem {

	public Board board;
	
	public AttackSystem(Board board) {
		this.board = board;
	}
	
	public void apply(Magic currentMagic) {
		
		GameObject target = currentMagic.target;
		
		if (!(target instanceof Destructible)) {
			return;
		}
		
		Position pos = target.getPosition();
		
		Tile tile = board.getTile(pos.x, pos.y);
		
		tile.setObject(null);
	}
}
