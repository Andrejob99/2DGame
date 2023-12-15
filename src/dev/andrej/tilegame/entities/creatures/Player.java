package dev.andrej.tilegame.entities.creatures;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    private float speedMultiplier;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
    }

    @Override
    public void tick() {
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
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                //(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                //bounds.width, bounds.height);
    }
}
