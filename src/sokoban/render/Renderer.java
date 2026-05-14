package sokoban.render;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import sokoban.gameObject.*;
import sokoban.gameObject.Box;
import sokoban.manager.Position;
import sokoban.manager.SpriteManager;
import sokoban.scene.*;

public class Renderer {
	
	public static final int TILE_SIZE = 64;

    public boolean showMagic;

    public List<Position> magicPath;
    
    public SpriteManager spriteManager;
    
    public Renderer(SpriteManager spriteManager) {
    	this.spriteManager = spriteManager;
    }

    // Render Entry

    public void render(Graphics2D g, Scene scene) {

        clearScreen(g);

        if (scene instanceof TitleScene titleScene) {

            drawTitle(g, titleScene);

        } else if (scene instanceof StageScene stageScene) {

            drawStage(g, stageScene);
        }
    }

    // Title Scene

    private void drawTitle(Graphics2D g, TitleScene scene) {

        g.setColor(Color.LIGHT_GRAY);

        g.fillRect(0, 0, 704, 576);

        g.setColor(Color.BLACK);

        g.setFont(new Font("Arial", Font.BOLD, 48));

        g.drawString("SOKOBAN PUZZLE", 150, 200);

        g.setFont(new Font("Arial", Font.PLAIN, 24));

        g.drawString("PRESS ENTER", 260, 300);
    }

    // Stage Scene

    private void drawStage(Graphics2D g,
                           StageScene scene) {

        Board board = scene.getBoard();

        drawBoard(g, board);

        drawMagicEffect(g);
    }

    // Board

    private void drawBoard(Graphics2D g,
                           Board board) {

        for (int y = 0; y < board.getHeight(); y++) {

            for (int x = 0; x < board.getWidth(); x++) {

                Tile tile = board.getTile(x, y);

                drawTerrain(g, tile.getTerrain(), x, y);

                drawFloor(g, tile.getFloor(), x, y);

                drawObject(g, tile.getObject(), x, y);
            }
        }
    }

    private void drawTerrain(Graphics2D g, Terrain terrain, int x, int y) {

        int px = x * TILE_SIZE;
        int py = y * TILE_SIZE;

        switch (terrain.type) {
            case GROUND ->
                    g.drawImage(spriteManager.get("GROUND"), px, py, TILE_SIZE, TILE_SIZE, null);

            case PIT ->
                    g.drawImage(spriteManager.get("PIT"), px, py, TILE_SIZE, TILE_SIZE, null);
        }
    }

    private void drawFloor(Graphics2D g, Floor floor, int x, int y) {

        int px = x * TILE_SIZE;
        int py = y * TILE_SIZE;

        switch (floor.type) {

            case GOAL ->
                    g.drawImage(spriteManager.get("GOAL"), px, py, TILE_SIZE, TILE_SIZE, null);

            case NONE -> {}
        }
    }

    private void drawObject(Graphics2D g, GameObject obj, int x, int y) {

        if (obj == null) {
            return;
        }

        int px = x * TILE_SIZE;
        int py = y * TILE_SIZE;

        if (obj instanceof Mage player) {

            drawPlayer(g, player, px, py);

        } else if (obj instanceof Wall) {

            g.drawImage(
            		spriteManager.get("WALL"), px, py, TILE_SIZE, TILE_SIZE, null);

        } else if (obj instanceof Box) {

            g.drawImage(spriteManager.get("BOX"), px, py, TILE_SIZE, TILE_SIZE, null);

        } else if (obj instanceof Mirror mirror) {

            drawMirror(g, mirror, px, py);
        }
    }

    private void drawPlayer(Graphics2D g, Mage player, int px, int py) {

        BufferedImage sprite;
        
        if (player instanceof AttackMage) {
        	sprite = switch (player.getDirection()) {

            case UP -> spriteManager.get("ATTACKMAGE_UP");

            case DOWN -> spriteManager.get("ATTACKMAGE_DOWN");

            case LEFT -> spriteManager.get("ATTACKMAGE_LEFT");

            case RIGHT -> spriteManager.get("ATTACKMAGE_RIGHT");
        	};
        } else if (player instanceof TeleportMage) {
        	sprite = switch (player.getDirection()) {
        	
        	case UP -> spriteManager.get("TELEPORTMAGE_UP");

            case DOWN -> spriteManager.get("TELEPORTMAGE_DOWN");

            case LEFT -> spriteManager.get("TELEPORTMAGE_LEFT");

            case RIGHT -> spriteManager.get("TELEPORTMAGE_RIGHT");
        	};
        } else {
        	sprite = switch (player.getDirection()) {
        	
        	case UP -> spriteManager.get("PLAYER_UP");

            case DOWN -> spriteManager.get("PLAYER_DOWN");

            case LEFT -> spriteManager.get("PLAYER_LEFT");

            case RIGHT -> spriteManager.get("PLAYER_RIGHT");
        	};
        }

        g.drawImage(sprite, px, py, TILE_SIZE, TILE_SIZE, null);
    }

    private void drawMirror(Graphics2D g,
                            Mirror mirror,
                            int px,
                            int py) {

        switch (mirror.getMirrorDirection()) {

            case TOP_RIGHT_TO_BOTTOM_LEFT ->
                    g.drawImage(spriteManager.get("MIRROR_SLASH"), px, py, TILE_SIZE, TILE_SIZE, null);

            case TOP_LEFT_TO_BOTTOM_RIGHT ->
                    g.drawImage(spriteManager.get("MIRROR_BACKSLASH"), px, py, TILE_SIZE, TILE_SIZE, null);
        }
    }

    private void drawMagicEffect(Graphics2D g) {

        if (!showMagic || magicPath == null) {
            return;
        }

        for (Position step : magicPath) {

            int px = step.x * TILE_SIZE;
            int py = step.y * TILE_SIZE;

            g.drawImage(spriteManager.get("BEAM"), px + TILE_SIZE / 4, py + TILE_SIZE / 4, TILE_SIZE / 2, TILE_SIZE / 2, null);
        }
    }

    public void showMagicBeam(List<Position> path) {

        this.magicPath = path;

        this.showMagic = true;
    }

    public void hideMagicBeam() {

        this.magicPath = null;

        this.showMagic = false;
    }

    private void clearScreen(Graphics2D g) {

        g.setColor(Color.BLACK);

        g.fillRect(0, 0, 1280, 720);
    }
}