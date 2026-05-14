package sokoban.gameObject;

import sokoban.gameObject.interaction.*;

public class Box extends GameObject implements Blocking, Destructible, Pushable, Teleportable {

	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
