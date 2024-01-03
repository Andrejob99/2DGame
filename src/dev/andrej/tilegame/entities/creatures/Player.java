package dev.andrej.tilegame.entities.creatures;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.entities.Entity;
import dev.andrej.tilegame.gfx.Animation;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animation up, down, left, right;
    private Animation[] directions;
    private int animationIndex;
    private int animationPriority;
    private int priorityCounter;
    private boolean fast;

    //Attack
    private long lastAttackTime = System.currentTimeMillis();
    private long attackCooldown = 1000;

    private float speedMultiplier;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 48;
        bounds.y = 96;
        bounds.width = 32;
        bounds.height = 32;

        //Animations
        up = new Animation(200, Assets.upSprites);
        down = new Animation(200, Assets.downSprites);
        left = new Animation(200, Assets.leftSprites);
        right = new Animation(200, Assets.rightSprites);

        directions = new Animation[]{up, down, left, right};

        animationIndex = 1;
        animationPriority = 1;

        fast = false;
    }

    @Override
    public void tick() {
        directions[animationIndex].tick(fast);

        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        checkAttacks();
    }

    private void checkAttacks() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastAttackTime < attackCooldown) {
            return;
        }

        Rectangle playerBox = getCollisionBounds(0,0);
        Rectangle attackBox = new Rectangle();
        int attackSize = 20;
        attackBox.width = attackSize;
        attackBox.height = attackSize;

        if (handler.getKeyManager().up){
            attackBox.x = playerBox.x + playerBox.width / 2 - attackSize / 2;
            attackBox.y = playerBox.y - attackSize;
        } else if (handler.getKeyManager().down) {
            attackBox.x = playerBox.x + playerBox.width / 2 - attackSize / 2;
            attackBox.y = playerBox.y + playerBox.height;
        } else if (handler.getKeyManager().left) {
            attackBox.x = playerBox.x - attackSize;
            attackBox.y = playerBox.y + playerBox.height / 2 - attackSize / 2;
        } else if (handler.getKeyManager().right) {
            attackBox.x = playerBox.x + playerBox.width;
            attackBox.y = playerBox.y + playerBox.height / 2 - attackSize / 2;
        }

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(attackBox)){
                e.takeDamage(1);
                lastAttackTime = currentTime;
                System.out.println("Chop chop");
                return;
            }
        }

    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        speedMultiplier = 1;
        if(handler.getKeyManager().shift){
            speedMultiplier = 3;
            fast = true;
        } else {
            fast = false;
        }

        priorityCounter = 0;

        if(handler.getKeyManager().w){
            yMove = -speed * speedMultiplier;
            priorityCounter++;
        }
        if(handler.getKeyManager().s){
            yMove = speed * speedMultiplier;
            priorityCounter++;
        }
        if(handler.getKeyManager().a){
            xMove = -speed * speedMultiplier;
            priorityCounter++;
        }
        if(handler.getKeyManager().d){
            xMove = speed * speedMultiplier;
            priorityCounter++;
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
    }

    @Override
    public void die() {
        System.out.println("You Died");
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (priorityCounter > 2){
            switch (animationPriority) {
                case 0 -> animationPriority = 1;
                case 1 -> animationPriority = 0;
                case 2 -> animationPriority = 3;
                case 3 -> animationPriority = 2;
            }
            return directions[animationPriority].getCurrentFrame();
        }

        if(xMove != 0 && yMove != 0){
            return directions[animationPriority].getCurrentFrame();
        }

        if(yMove < 0){
            //turn north
            animationIndex = 0;
            animationPriority = animationIndex;

            return up.getCurrentFrame();
        } else if(yMove > 0){
            //turn south
            animationIndex = 1;
            animationPriority = animationIndex;

            return down.getCurrentFrame();
        } else if(xMove < 0){
            //turn west
            animationIndex = 2;
            animationPriority = animationIndex;

            return left.getCurrentFrame();
        } else if(xMove > 0) {
            //turn east
            animationIndex = 3;
            animationPriority = animationIndex;

            return right.getCurrentFrame();
        } else {
            //stand
            animationPriority = animationIndex;
            directions[animationIndex].resetAnimation();

            return directions[animationIndex].getCurrentFrame();
        }
    }
}
