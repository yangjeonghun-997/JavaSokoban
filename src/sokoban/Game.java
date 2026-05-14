package sokoban;

import javax.swing.*;

import sokoban.manager.GamePanel;
import sokoban.manager.InputCommand;
import sokoban.manager.InputManager;
import sokoban.manager.SceneManager;
import sokoban.manager.SpriteManager;
import sokoban.render.Renderer;
import sokoban.scene.*;

public class Game {
	
	public Renderer renderer;
	public InputManager inputManager;
	public SceneManager sceneManager;
	public SpriteManager spriteManager;
	
	public GamePanel panel;
	public JFrame frame;
	
	public boolean running = false;
	
	public void exitGame() {
		running = false;
	}
	
	public void start() {
		inputManager = new InputManager();
		spriteManager = new SpriteManager();
		
		spriteManager.load();

		renderer = new Renderer(spriteManager);

		sceneManager = new SceneManager(inputManager, renderer, this);
		
		panel = new GamePanel(renderer, sceneManager);
		
		frame = new JFrame();
		
		frame.add(panel);
		
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setFocusable(true);

		frame.addKeyListener(inputManager);

		frame.setVisible(true);
		
		frame.requestFocus();
		
		sceneManager.changeScene(new TitleScene(sceneManager));
		
		running = true;
		
		gameLoop();
	}
	
	public void gameLoop() {
		
		final double FPS = 60.0;
		final double deltaTime = 1000000000 / FPS;
		
		long previous = System.nanoTime();
		
		double lag = 0.0;
		
		while (running) {
			
			long current = System.nanoTime();
			lag += current - previous;
			previous = current;

			if (lag >= deltaTime) {
				InputCommand input = inputManager.poll();
				
				sceneManager.update(input);
				
				inputManager.update();
				
				lag -= deltaTime;
			}
			
			panel.repaint();
		}
		
		frame.dispose();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}
