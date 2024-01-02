package dev.andrej.tilegame.ui;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject{


    protected float x, y;
    protected int width, height;
    protected boolean hovering = false;
    protected Rectangle bounds;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        hovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseRelease(MouseEvent e){
        if (hovering)
            onClick();
    }

}
