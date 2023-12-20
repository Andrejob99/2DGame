package dev.andrej.tilegame.entities.creatures;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.gfx.Animation;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animation up, down, left, right;
    private Animation[] directions;
    private int index;

    private float speedMultiplier;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 48;
        bounds.y = 96;
        bounds.width = 32;
        bounds.height = 32;

        //Animations
        up = new Animation(250, Assets.upSprites);
        down = new Animation(250, Assets.downSprites);
        left = new Animation(250, Assets.leftSprites);
        right = new Animation(250, Assets.rightSprites);

        directions = new Animation[]{up, down, left, right};

        index = 1;
    }

    @Override
    public void tick() {
        directions[index].tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        speedMultiplier = 1;
        if(handler.getKeyManager().xKey){
            speedMultiplier = 3;
        }

        if(handler.getKeyManager().up){
            yMove = -speed * speedMultiplier;
        }
        if(handler.getKeyManager().down){
            yMove = speed * speedMultiplier;
        }
        if(handler.getKeyManager().left){
            xMove = -speed * speedMultiplier;
        }
        if(handler.getKeyManager().right){
            xMove = speed * speedMultiplier;
        }

    }

    @Override
    public void moveX() {
        //Moving right
        if (xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            }

            else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }

        //Moving left
        else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            }

            else {
                x = tx * Tile.TILEWIDTH + 16;   //temporary fix
            }
        }
    }

    @Override
    public void moveY() {
        //Moving up
        if (yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            }

            else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }

        }

        //Moving down
        else if (yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
            }

            else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }

        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width * 2, height * 2, null);

        /*
        g.setColor(Color.red);
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
                */

    }

    private BufferedImage getCurrentAnimationFrame(){
        if(yMove < 0){
            index = 0;
            return up.getCurrentFrame();
        } else if(yMove > 0){
            index = 1;
            return down.getCurrentFrame();
        } else if(xMove < 0){
            index = 2;
            return left.getCurrentFrame();
        } else if(xMove > 0) {
            index = 3;
            return right.getCurrentFrame();
        } else {
            //stand
            directions[index].resetAnimation();
            return directions[index].getCurrentFrame();
        }
    }
}
