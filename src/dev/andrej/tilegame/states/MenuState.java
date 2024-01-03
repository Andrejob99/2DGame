package dev.andrej.tilegame.states;

import dev.andrej.tilegame.Handler;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.ui.ClickListener;
import dev.andrej.tilegame.ui.UIImageButton;
import dev.andrej.tilegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton((int) (handler.getGame().getWidth()/2 - Assets.menuButtonWidth/2), (int) (handler.getGame().getHeight()/2 - Assets.menuButtonHeight/2), Assets.menuButtonWidth, Assets.menuButtonHeight, Assets.menuSprites, new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
