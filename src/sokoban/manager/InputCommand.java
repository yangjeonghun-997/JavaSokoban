package sokoban.manager;

import sokoban.gameObject.Direction;

public class InputCommand {

	public final Command command;
	public final Direction direction;
	
	public InputCommand(Command command, Direction direction) {
		this.command = command;
		this.direction = direction;
	}
}
