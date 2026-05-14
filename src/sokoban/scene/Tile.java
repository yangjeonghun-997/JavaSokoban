package sokoban.scene;

import sokoban.gameObject.GameObject;

public class Tile {

	public Terrain terrain;
	public Floor floor;
	public GameObject object;
	
	public Terrain getTerrain() {return terrain;}
	public Floor getFloor() {return floor;}
	public GameObject getObject() {return object;}
	
	public void setObject(GameObject object) {
		this.object = object;
	}
	
	public Tile(Terrain terrain, Floor floor, GameObject object) {
		this.terrain = terrain;
		this.floor = floor;
		this.object = object;
	}
	
	public Tile(Tile tile) {
		this.terrain = tile.terrain;
		this.floor = tile.floor;
		this.object = tile.object;
	}
}
