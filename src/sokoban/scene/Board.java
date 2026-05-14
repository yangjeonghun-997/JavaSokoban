package sokoban.scene;

import java.util.ArrayList;
import java.util.List;

import sokoban.gameObject.Mage;

public class Board {
	
	public Tile[][] tiles;
	
	public Board(int x, int y) {
		this.tiles = new Tile[y][x];
	}
	
	public Tile getTile(int x, int y) {
		return tiles[y][x];
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[y][x] = tile;
	}
	
	public int getWidth() {
		return tiles[0].length;
	}
	
	public int getHeight() {
		return tiles.length;
	}
	
	public boolean isInside(int x, int y) {
		return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
	}
	
	public List<Mage> getAllMages() {
		
		List<Mage> mages = new ArrayList<>();
		
		for (Tile[] row : tiles) {
			for (Tile t : row) {
				if (t.getObject() instanceof Mage) {
					mages.add((Mage) t.getObject());
				}
			}
		}
		return mages;
	}
}
