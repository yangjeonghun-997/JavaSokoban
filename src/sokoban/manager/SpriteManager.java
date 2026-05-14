package sokoban.manager;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class SpriteManager {

	public final Map<String, BufferedImage> sprites = new HashMap<>();
	
	public void load() {
		
		sprites.put("GROUND", load("/sprites/ground.png"));
        sprites.put("PIT", load("/sprites/pit.png"));
        sprites.put("GOAL", load("/sprites/goal.png"));

        sprites.put("WALL", load("/sprites/wall.png"));
        sprites.put("BOX", load("/sprites/box.png"));

        sprites.put("MIRROR_SLASH", load("/sprites/mirror_slash.png"));
        sprites.put("MIRROR_BACKSLASH", load("/sprites/mirror_backslash.png"));

        sprites.put("PLAYER_UP", load("/sprites/player_up.png"));
        sprites.put("PLAYER_DOWN", load("/sprites/player_down.png"));
        sprites.put("PLAYER_LEFT", load("/sprites/player_left.png"));
        sprites.put("PLAYER_RIGHT", load("/sprites/player_right.png"));
        
        sprites.put("ATTACKMAGE_UP", load("/sprites/attackmage_up.png"));
        sprites.put("ATTACKMAGE_DOWN", load("/sprites/attackmage_down.png"));
        sprites.put("ATTACKMAGE_LEFT", load("/sprites/attackmage_left.png"));
        sprites.put("ATTACKMAGE_RIGHT", load("/sprites/attackmage_right.png"));
        
        sprites.put("TELEPORTMAGE_UP", load("/sprites/teleportmage_up.png"));
        sprites.put("TELEPORTMAGE_DOWN", load("/sprites/teleportmage_down.png"));
        sprites.put("TELEPORTMAGE_LEFT", load("/sprites/teleportmage_left.png"));
        sprites.put("TELEPORTMAGE_RIGHT", load("/sprites/teleportmage_right.png"));

        sprites.put("BEAM", load("/sprites/beam.png"));
	}
	
	private BufferedImage load(String path) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			System.out.println("[SpriteManager] 로딩 실패: " + path);
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedImage get(String key) {
		return sprites.get(key);
	}
}
