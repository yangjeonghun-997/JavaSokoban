package sokoban.manager.stageSystem;

import sokoban.gameObject.Mage;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class GoalSystem {
	
	public boolean isCleared(Board board) {
		
		for (int y = 0; y < board.getHeight(); y++) {
			for (int x = 0; x < board.getWidth(); x++) {
				
				Tile tile = board.getTile(x, y);
				
				if (!tile.getFloor().isGoal()) {
					continue;
				}
				
				if (!(tile.getObject() instanceof Mage)) {
					return false;
				}
			}
		}
		
		return true;
		
	}
}
