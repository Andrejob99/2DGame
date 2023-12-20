package dev.andrej.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(boolean fast){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (fast && timer > speed/3) {
            index = (index+1) % 4;
            timer = 0;
        } else if (timer > speed) {
            index = (index+1) % 4;
            timer = 0;
        }
    }

    public void resetAnimation(){
        index = 0;
        timer = 0;
    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
