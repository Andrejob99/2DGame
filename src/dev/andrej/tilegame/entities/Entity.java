package dev.andrej.tilegame.entities;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.Handler;

import java.awt.*;

public abstract class Entity {

    public static final int DEFAULT_HEALTH = 10;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected boolean active;
    protected Rectangle bounds;
    protected long damageTimestamp;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;
        this.active = true;
        this.damageTimestamp = System.currentTimeMillis() - 1000;

        bounds = new Rectangle(0,0,width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public void takeDamage(int amt){
        health -= amt;
        if (health <= 0){
            active = false;
            die();
        }
        else {
            damageTimestamp = System.currentTimeMillis();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }
}
