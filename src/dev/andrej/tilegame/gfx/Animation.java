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

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index = (index+1)%3;
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
