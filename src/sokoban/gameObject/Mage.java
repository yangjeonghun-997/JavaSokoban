package sokoban.gameObject;

import sokoban.gameObject.interaction.*;

public abstract class Mage extends GameObject implements Blocking, Teleportable {
	
	public Direction dir = Direction.DOWN;
	
	public Magic currentMagic = new Magic(this);
	
	public Direction getDirection() {
		return dir;
	}
	
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	public abstract void magic();
}
