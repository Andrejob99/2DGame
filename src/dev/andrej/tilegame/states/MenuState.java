package dev.andrej.tilegame.states;

import dev.andrej.tilegame.Game;
import dev.andrej.tilegame.Launcher;
import dev.andrej.tilegame.display.Display;
import dev.andrej.tilegame.gfx.Assets;

import java.awt.*;

public class MenuState extends State {

    public MenuState(Game game) {
        super(game);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, 0, 0, null);
        g.drawImage(Assets.tree, 370, 0, null);
    }
}
