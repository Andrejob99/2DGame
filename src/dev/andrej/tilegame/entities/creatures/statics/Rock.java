package dev.andrej.tilegame.entities.creatures.statics;

import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.items.Item;
import dev.andrej.tilegame.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    private int damageMarkerDuration;

    public Rock(Handler handler, float x, float y){
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
        handler.getWorld().getItemManager().addItem(Item.pebbleItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if (System.currentTimeMillis() - damageTimestamp < damageMarkerDuration) {
            g.drawImage(Assets.cross, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset() + height/2), width, height/2, null);
        }
    }

}
