package dev.andrej.tilegame.items;

import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    // Handler

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item pebbleItem = new Item(Assets.pebble, "Pebble", 0);

    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32, PICKED_UP = -1;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected int x, y, count;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        items[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public Item createNew(int x, int y) {
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and Setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
