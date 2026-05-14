package sokoban.gameObject;

import sokoban.gameObject.interaction.Blocking;

public class Wall extends GameObject implements Blocking {

	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
