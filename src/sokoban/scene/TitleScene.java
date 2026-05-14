package sokoban.scene;

import sokoban.manager.InputCommand;
import sokoban.manager.InputManager;
import sokoban.manager.SceneManager;
import sokoban.render.Renderer;

public class TitleScene implements Scene {
	
	private SceneManager sceneManager;
	
	public TitleScene(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	@Override
	public void update(InputManager inputManager, InputCommand input) {

		if (input == null) return;
		
		switch (input.command) {
		case ESCAPE:
			sceneManager.game.exitGame();
			break;
		default:
				sceneManager.changeScene(new StageScene(sceneManager, 0));
		}
	}
	
	@Override
	public void onEnter() {
		
	}
	
	@Override
	public void onExit() {
		
	}
	
}
