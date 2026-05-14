package sokoban.scene;

import sokoban.manager.InputCommand;
import sokoban.manager.InputManager;
import sokoban.manager.SceneManager;
import sokoban.manager.stageSystem.GameManager;
import sokoban.render.Renderer;

public class StageScene implements Scene {

	private GameManager gameManager;
	
	private Board board;
	
	public StageScene(SceneManager sceneManager, int index) {
		
		MapLoader loader = new MapLoader();
		
		board = loader.load(index);
		
		this.gameManager = new GameManager(board, sceneManager);
	}
	
	public Board getBoard() {
		return board;
	}
	
	@Override
	public void update(InputManager inputManager, InputCommand input) {
		gameManager.update(input);
	}
	
	@Override
	public void onEnter() {
		
	}
	
	@Override
	public void onExit() {
		
	}
}
