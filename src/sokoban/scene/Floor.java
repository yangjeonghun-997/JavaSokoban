package sokoban.scene;

public class Floor {
	public FloorType type;
	
	public Floor(FloorType type) {
		this.type = type;
	}
	
	public boolean isGoal() {
		return type == FloorType.GOAL;
	}
}
