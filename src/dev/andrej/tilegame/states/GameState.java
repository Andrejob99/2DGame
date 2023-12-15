package dev.andrej.tilegame.states;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.entities.creatures.Player;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.tiles.Tile;
import dev.andrej.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler) {
        super(handler);
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
        player = new Player(handler,world.getSpawnX()*64,world.getSpawnY()*64);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }
}
