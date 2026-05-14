package sokoban.gameObject;

import sokoban.gameObject.interaction.*;

public class Mirror extends GameObject implements Pushable, Reflectable {
	
	private MirrorDirection dir;
	
	public Mirror(int x, int y) {
		this.x = x;
		this.y = y;
		this.dir = MirrorDirection.TOP_LEFT_TO_BOTTOM_RIGHT;
	}
	
	public Mirror(int x, int y, MirrorDirection dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public MirrorDirection getMirrorDirection() {
		return dir;
	}
	
	public void setMirrorDirection(MirrorDirection dir) {
		this.dir = dir;
	}
	
	public Direction reflect(Direction in) {
		
		return switch (this.dir) {

        case TOP_RIGHT_TO_BOTTOM_LEFT -> switch (in) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.UP;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
        };

        case TOP_LEFT_TO_BOTTOM_RIGHT -> switch (in) {
            case UP -> Direction.LEFT;
            case LEFT -> Direction.UP;
            case DOWN -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
        };
    };
	}
}
