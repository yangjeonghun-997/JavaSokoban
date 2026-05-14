package sokoban.manager.stageSystem;

import java.util.ArrayList;
import java.util.List;

import sokoban.gameObject.Direction;
import sokoban.gameObject.GameObject;
import sokoban.gameObject.Mage;
import sokoban.gameObject.Magic;
import sokoban.gameObject.Mirror;
import sokoban.gameObject.interaction.*;
import sokoban.manager.Position;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class MagicSystem {

	public Board board;
	
	public GameObject target;
	
	public AttackSystem attackSystem;
	public TeleportSystem teleportSystem;
	
	public MagicSystem(Board board) {
		this.board = board;
		
		attackSystem = new AttackSystem(board);
		teleportSystem = new TeleportSystem(board);
	}
	
	public List<Position> cast(Mage mage) {
		
		Magic currentMagic= mage.currentMagic;
		
		mage.magic();
		
		List<Position> path = calculatePath(mage);
		
		currentMagic.setPath(path);
		
		currentMagic.setTarget(target);
		
		switch (currentMagic.type) {
		
		case ATTACK:
			attackSystem.apply(currentMagic);
			break;
			
		case TELEPORT:
			teleportSystem.apply(currentMagic);
			break;
			
		}
		
		return path;
	}
	
	public List<Position> calculatePath(Mage mage) {
		
		List<Position> path = new ArrayList<>();
		
		Position pos = mage.getPosition();
		Direction dir = mage.getDirection();
		
		int maxSteps = 100;	// 무한 루프 방지
		
		for (int i = 0; i < maxSteps; i++) {
			
			pos = pos.move(dir);
			
			if (!board.isInside(pos.x, pos.y)) {
				break;
			}
			
			Tile tile = board.getTile(pos.x, pos.y);
			
			if (tile == null) {
				break;
			}
			
			path.add(new Position(pos.x, pos.y));
			
			GameObject obj = tile.getObject();
			
			if (obj instanceof Reflectable) {
				Mirror mirror = (Mirror) obj;
				dir = mirror.reflect(dir);
				continue;
			}
			
			if (obj instanceof Blocking) {
				break;
			}
			
			if (!path.isEmpty()) {
				Position last = path.get(path.size() - 1);
				
				mage.currentMagic.setTarget(board.getTile(last.x, last.y).getObject());
				
			}
			
			
		}
		
		Position targetPos = path.remove(path.size() -1);
		
		target = board.getTile(targetPos.x, targetPos.y).getObject();
		
		return path;
	}
}
