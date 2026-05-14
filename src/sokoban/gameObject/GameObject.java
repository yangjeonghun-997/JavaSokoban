package sokoban.gameObject;

import sokoban.manager.Position;

public abstract class GameObject {

	public int x, y;
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position getPosition() {
		return new Position(x, y);
	}
}
