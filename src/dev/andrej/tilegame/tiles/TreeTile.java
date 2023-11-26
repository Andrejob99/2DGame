package dev.andrej.tilegame.tiles;

import dev.andrej.tilegame.gfx.Assets;

public class TreeTile extends Tile{

    public TreeTile(int id) {
        super(Assets.tree, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
