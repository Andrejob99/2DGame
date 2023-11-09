package dev.andrej.tilegame.states;

import dev.andrej.tilegame.gfx.Assets;

import java.awt.*;

public class GameState extends State {

    public GameState() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.grass, 0, 0, null);
    }
}
