package sokoban.manager.stageSystem;

import java.util.ArrayList;
import java.util.List;

import sokoban.gameObject.Direction;
import sokoban.gameObject.Mage;
import sokoban.manager.InputCommand;
import sokoban.manager.Position;
import sokoban.manager.SceneManager;
import sokoban.render.Renderer;
import sokoban.scene.Board;
import sokoban.scene.Tile;

public class GameManager {
	
	public Board board;

	public SceneManager sceneManager;
	public PushSystem pushSystem;
	public MoveSystem moveSystem;
	public MagicSystem magicSystem;
	public GoalSystem goalSystem;
	
	public List<Mage> mages;
	public int currentMageIndex = 0;
	public Mage currentMage;
	
	public Renderer renderer;
	
	public Mage getCurrentMage() {
		return mages.get(currentMageIndex);
	}
	
	public GameManager(Board board, SceneManager sceneManager) {
		this.board = board;
		this.sceneManager = sceneManager;
		
		pushSystem = new PushSystem();
		moveSystem = new MoveSystem(pushSystem);
		magicSystem = new MagicSystem(board);
		goalSystem = new GoalSystem();
		
		this.renderer = sceneManager.renderer;
		
		mages = board.getAllMages();
		currentMage = getCurrentMage();
	}
	
	public void move(Direction dir) {
		boolean result = moveSystem.tryMove(board, currentMage, dir);
		
		currentMage.setDirection(dir);
		
		if (result == true) {
			
			if (goalSystem.isCleared(board)) {
				clearStage();
			}
		}
	}
	
	public void clearStage() {
		sceneManager.onStageCleared();
	}
	
	public void switchPlayer() {
		currentMageIndex = (currentMageIndex + 1) % mages.size();
		
		System.out.println(currentMageIndex);
		
		currentMage = getCurrentMage();
	}
	
	public void castMagic() {
		
		List<Position> path = magicSystem.cast(currentMage);
		
		renderer.showMagicBeam(path);
	}
	
	public void update(InputCommand input) {
		
		if (input == null) return;
		
		renderer.hideMagicBeam();
		
		switch (input.command) {
		
		case MOVE:
			move(input.direction);
			break;
			
		case ROTATE:
			currentMage.setDirection(input.direction);
			break;
			
		case SWITCH_PLAYER:
			switchPlayer();
			break;
			
		case MAGIC:
			castMagic();
			break;
			
		case UNDO:
			System.out.println("UNDO 미구현");
			break;
			
		case RESET:
			sceneManager.reset();
			break;
			
		case ESCAPE:
			sceneManager.backToTitle();
			break;
			
		}
	}
}
