package sokoban.scene;

import sokoban.manager.InputCommand;
import sokoban.manager.InputManager;
import sokoban.render.Renderer;

public interface Scene {
	public void update(InputManager inputManager, InputCommand input);
	
	void onEnter();
	
	void onExit();
}
