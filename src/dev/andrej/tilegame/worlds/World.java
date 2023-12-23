package dev.andrej.tilegame.worlds;

import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.entities.EntityManager;
import dev.andrej.tilegame.entities.creatures.Player;
import dev.andrej.tilegame.entities.creatures.statics.Tree;
import dev.andrej.tilegame.tiles.*;
import dev.andrej.tilegame.utils.Utils;

import java.awt.*;

public class World {

    //Initialize Tiles
    public static Tile dirtTile = new DirtTile(0);
    public static Tile grassTile = new GrassTile(1);
    public static Tile rockTile = new RockTile(2);
    public static Tile treeTile = new TreeTile(3);

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] worldTiles;
    //Entities
    private EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0, 0));
        entityManager.addEntity(new Tree(handler, 100, 200));
        entityManager.addEntity(new Tree(handler, 100, 300));
        entityManager.addEntity(new Tree(handler, 100, 400));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX*64);
        entityManager.getPlayer().setY(spawnY*64);
    }

    public void tick() {
        entityManager.tick();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void render(Graphics g) {
        //Efficient rendering, only render what you can see
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        //Tile render loop
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        //Entity render loop
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return grassTile;

        Tile t = Tile.tiles[worldTiles[x][y]];
        if (t == null) {
            return dirtTile;
        }

        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        worldTiles = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                worldTiles[x][y] = Utils.parseInt(tokens[4 + x + y * width]);
            }
        }
    }

    public int getSpawnX(){
        return spawnX;
    }

    public int getSpawnY(){
        return spawnY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
