package dev.andrej.tilegame.entities.creatures.statics;

import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.items.Item;
import dev.andrej.tilegame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    private int damageMarkerDuration;

    public Tree(Handler handler, float x, float y){
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);

        this.damageMarkerDuration = 500;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) (x + bounds.width/2.8), (int) (y + bounds.height*2.1)));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if (System.currentTimeMillis() - damageTimestamp < damageMarkerDuration) {
            g.drawImage(Assets.cross, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset() + height/2), width, height/2, null);
        }
    }

}
