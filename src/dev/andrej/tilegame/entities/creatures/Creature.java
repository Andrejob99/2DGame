package dev.andrej.tilegame.entities.creatures;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.entities.Entity;

public abstract class Creature extends Entity {

    protected int hp;
    protected float speed;
    protected float xMove, yMove;

    public static final int DEFAULT_HP = 10;
    public static final float DEFAULT_SPEED = 3.0f;

    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    public Creature(Game game, float x, float y, int width, int height) {
        super(game, x, y, width, height);
        hp = DEFAULT_HP;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        x += xMove;
        y += yMove;
    }



    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
