package sokoban.scene;

import java.io.*;
import java.util.*;

import sokoban.gameObject.*;

public class MapLoader {
	
	public List<String> readFile(int stageIndex) {
		
		String fileName = "Stage" + stageIndex + ".txt";
		
		List<String> lines = new ArrayList<>();
		
		try (InputStream is = getClass()
				.getClassLoader()
				.getResourceAsStream(fileName);
			BufferedReader reader = new BufferedReader(
						new InputStreamReader(is))) {
			
			if (is == null) {
				throw new FileNotFoundException("리소스 없음: " + fileName);
			}
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (Exception e) {
			System.out.println("[MapLoader] 로딩 실패: " + fileName);
			e.printStackTrace();
		}
		
		return lines;
	}

	public Board load(int index) {
		List<String> lines = readFile(index);
		
		int height = lines.size();
		int width = lines.get(0).length();
		
		Board board = new Board(width, height);
		
		for (int y = 0; y < height; y++) {
			String line = lines.get(y);
			for (int x = 0; x < width; x++) {
				char c = line.charAt(x);
				
				Tile tile = parseTile(c, x, y);
				
				board.setTile(x, y, tile);
			}
		}
		
		return board;
	}
	
	public Tile parseTile(char c, int x, int y) {
		
		Terrain terrain = new Terrain(TerrainType.GROUND);
		Floor floor = new Floor(FloorType.NONE);
		GameObject object = null;
		
		switch (c) {
		case '#':
            object = new Wall(x, y);
            break;

        case 'A':
            object = new AttackMage(x, y);
            break;
            
        case 'T':
            object = new TeleportMage(x, y);
            break;

        case 'B':
            object = new Box(x, y);
            break;
            
        case 'M':
            object = new Mirror(x, y, MirrorDirection.TOP_LEFT_TO_BOTTOM_RIGHT);
            break;
            
        case 'W':
            object = new Mirror(x, y, MirrorDirection.TOP_RIGHT_TO_BOTTOM_LEFT);
            break;

        case 'G':
            floor = new Floor(FloorType.GOAL);
            break;

        case 'P':
        	terrain = new Terrain(TerrainType.PIT);
        	break;
        	
    	default:
    		break;
		}
		
		return new Tile(terrain, floor, object);
	}
}
