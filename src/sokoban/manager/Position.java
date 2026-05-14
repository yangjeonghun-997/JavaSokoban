package sokoban.manager;

import sokoban.gameObject.Direction;

public class Position {
	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position move(Direction dir) {
		this.x += dir.dx;
		this.y += dir.dy;
		return this;
	}
}
