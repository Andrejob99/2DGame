package dev.andrej.tilegame.entities.creatures;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.gfx.Assets;

import java.awt.*;

public class Player extends Creature {

    private float speedMultiplier;

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        speedMultiplier = 1;
        if(game.getKeyManager().xKey){
            speedMultiplier = 3;
        }

        if(game.getKeyManager().up){
            yMove = -speed * speedMultiplier;
        }
        if(game.getKeyManager().down){
            yMove = speed * speedMultiplier;
        }
        if(game.getKeyManager().left){
            xMove = -speed * speedMultiplier;
        }
        if(game.getKeyManager().right){
            xMove = speed * speedMultiplier;
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
