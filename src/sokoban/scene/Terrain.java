package sokoban.scene;

public class Terrain {
	public TerrainType type;
	
	public Terrain(TerrainType type) {
		this.type = type;
	}
	
	public boolean isWalkable() {
		return type != TerrainType.PIT;
	}
	
	public void fillPit() {
		if (this.type == TerrainType.PIT) {
			this.type = TerrainType.GROUND;
		}
	}
}
