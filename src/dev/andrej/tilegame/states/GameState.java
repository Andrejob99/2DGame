package dev.andrej.tilegame.states;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.entities.creatures.Player;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.tiles.Tile;
import dev.andrej.tilegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game) {
        super(game);
        world = new World(game,"res/worlds/world1.txt");
        player = new Player(game,world.getSpawnX()*64,world.getSpawnY()*64);
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
