package sokoban.manager.stageSystem;

import sokoban.gameObject.GameObject;
import sokoban.gameObject.Mage;
import sokoban.gameObject.Magic;
import sokoban.gameObject.interaction.Teleportable;
import sokoban.manager.Position;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class TeleportSystem {

	public final Board board;
	
	public TeleportSystem(Board board) {
		this.board = board;
	}
	
	public void apply(Magic currentMagic) {
		
		GameObject target = currentMagic.target;
		
		if (!(target instanceof Teleportable)) {
			return;
		}
		
		Mage caster = currentMagic.caster;
		
		Position casterPos = caster.getPosition();
		Position targetPos = currentMagic.target.getPosition();
		
		Tile casterTile = board.getTile(casterPos.x, casterPos.y);
		Tile targetTile = board.getTile(targetPos.x, targetPos.y);
		
		targetTile.setObject(null);
		
		targetTile.setObject(caster);
		caster.setPosition(targetPos.x, targetPos.y);
		
		casterTile.setObject(null);
		
		casterTile.setObject(target);
		target.setPosition(casterPos.x, casterPos.y);
	}
}
