package sokoban.manager;

import java.awt.*;

import javax.swing.*;

import sokoban.render.Renderer;

public class GamePanel extends JPanel {

	public Renderer renderer;
	public SceneManager sceneManager;
	
	public GamePanel(Renderer renderer, SceneManager sceneManager) {
		this.renderer = renderer;
		this.sceneManager = sceneManager;
		
		setPreferredSize(new Dimension(704, 576));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		renderer.render((Graphics2D) g, sceneManager.getCurrentScene());
	}
}
