package sokoban.manager;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

import sokoban.gameObject.Direction;

public class InputManager implements KeyListener {
	
	private final boolean[] keys = new boolean[256];
	
	private final boolean[] prevKeys = new boolean[256];
	
	public void update() {
		System.arraycopy(keys, 0, prevKeys, 0, keys.length);
	}
	
	public InputManager() {
		System.out.println("InputManager created");
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	
	
	public boolean isKeyDown(int keyCode) {
		return keys[keyCode];
	}
	
	public boolean isKeyPressed(int keyCode) {
		return keys[keyCode] && !prevKeys[keyCode];
	}
	
	
	private Direction getDirectionPressed() {
		if (isKeyPressed(KeyEvent.VK_UP) || isKeyPressed(KeyEvent.VK_W)) {
	        return Direction.UP;
	    }

	    if (isKeyPressed(KeyEvent.VK_DOWN) || isKeyPressed(KeyEvent.VK_S)) {
	        return Direction.DOWN;
	    }

	    if (isKeyPressed(KeyEvent.VK_LEFT) || isKeyPressed(KeyEvent.VK_A)) {
	        return Direction.LEFT;
	    }

	    if (isKeyPressed(KeyEvent.VK_RIGHT) || isKeyPressed(KeyEvent.VK_D)) {
	        return Direction.RIGHT;
	    }

	    return null;
	}
	
	private InputCommand buildMoveCommand(Direction dir, boolean shift) {
		if (shift) {
	        return new InputCommand(Command.ROTATE, dir);
	    } else {
	        return new InputCommand(Command.MOVE, dir);
	    }
	}
	
	public InputCommand poll() {
		
		boolean shift = isKeyDown(KeyEvent.VK_SHIFT);
		
		Direction dir = getDirectionPressed();

	    if (dir != null) {
	        return buildMoveCommand(dir, shift);
	    }

	    if (isKeyPressed(KeyEvent.VK_Z)) {
	        return new InputCommand(Command.UNDO, null);
	    }

	    if (isKeyPressed(KeyEvent.VK_R)) {
	        return new InputCommand(Command.RESET, null);
	    }

	    if (isKeyPressed(KeyEvent.VK_SPACE)) {
	        return new InputCommand(Command.MAGIC, null);
	    }

	    if (isKeyPressed(KeyEvent.VK_X)) {
	        return new InputCommand(Command.SWITCH_PLAYER, null);
	    }
	    
	    if (isKeyPressed(KeyEvent.VK_ESCAPE)) {
	    	return new InputCommand(Command.ESCAPE, null);
	    }

	    return null;
	}
}
