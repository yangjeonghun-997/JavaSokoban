package sokoban.manager;

import java.io.InputStream;

import sokoban.scene.Scene;
import sokoban.Game;
import sokoban.render.Renderer;
import sokoban.scene.*;

public class SceneManager {
	
	public InputManager inputManager;
	public Scene currentScene;
	public Game game;
	public Renderer renderer;
	
	public SceneManager(InputManager inputManager, Renderer renderer, Game game) {
		this.inputManager = inputManager;
		this.renderer = renderer;
		this.game = game;
		
		initStages();
	}
	
	private int currentStageIndex = 0;
	
	public void update(InputCommand input) {
		currentScene.update(inputManager, input);
	}
	
	public void changeScene(Scene scene) {
		if (currentScene != null) {
			currentScene.onExit();
		}
		
		currentScene = scene;
		
		currentScene.onEnter();
	}
	
	public Scene getCurrentScene() {
		return currentScene;
	}
	
	private int maxStage = 0;
	
	public void initStages() {
		while (true) {
			InputStream is = getClass()
					.getClassLoader()
					.getResourceAsStream("Stage" + maxStage + ".txt");
			
			if (is == null) {
				break;
			}
			
			maxStage++;
		}
	}
	
	public int getMaxStage() {
		return maxStage;
	}
	
	public void onStageCleared() {
		
		int nextStage = currentStageIndex + 1;
		
		if (nextStage < maxStage) {
			currentStageIndex = nextStage;
			
			changeScene(new StageScene(this, currentStageIndex));
		} else {
			currentStageIndex = 0;
			
			changeScene(new TitleScene(this));
		}
	}
	
	public void reset() {
		changeScene(new StageScene(this, currentStageIndex));
	}
	
	public void backToTitle() {
		changeScene(new TitleScene(this));
	}
}
