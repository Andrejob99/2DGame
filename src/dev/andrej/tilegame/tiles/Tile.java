package dev.andrej.tilegame.tiles;

import com.sun.source.tree.Tree;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static final int TILEWIDTH = 64,
                            TILEHEIGHT = 64;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        //When generating a specific tile, the array gets an entry of the tile at the field with the key "id"
        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }

}
