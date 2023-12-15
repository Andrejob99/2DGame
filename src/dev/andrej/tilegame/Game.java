package dev.andrej.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.andrej.tilegame.display.Display;
import dev.andrej.tilegame.gfx.Assets;
import dev.andrej.tilegame.gfx.GameCamera;
import dev.andrej.tilegame.input.KeyManager;
import dev.andrej.tilegame.states.GameState;
import dev.andrej.tilegame.states.MenuState;
import dev.andrej.tilegame.states.State;

public class Game implements Runnable {
    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameCamera = new GameCamera(this, 0,0);
        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);

        State.setState(gameState);
    }

    private void tick(){
        keyManager.tick();

        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        //Constants
        final int TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_UPDATES = 1000000000.0 / TARGET_FPS;
        final int MAX_UPDATES_BEFORE_RENDER = 5;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime = System.nanoTime();

        int frameCount = 0;
        int tickCount = 0;

        //Initialize timer
        long timer = System.currentTimeMillis();

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            //tick as long as enough time has passed for the next update
            while (now - lastUpdateTime > TARGET_TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                tick();
                lastUpdateTime += TARGET_TIME_BETWEEN_UPDATES;
                updateCount++;
                tickCount++;
            }

            if (now - lastUpdateTime > TARGET_TIME_BETWEEN_UPDATES) {
                lastUpdateTime = now - TARGET_TIME_BETWEEN_UPDATES;
            }

            //render and increment the frame counter
            render();
            frameCount++;

            double thisFrameTime = System.nanoTime();

            //prevent full throttle until time for next frame has passed
            while (thisFrameTime - lastRenderTime < 1000000000 / TARGET_FPS) {
                //give hint to scheduler that processor can be used for other thread
                Thread.yield();
                try {
                    //Reduce busy-waiting
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                thisFrameTime = System.nanoTime();
            }

            lastRenderTime = thisFrameTime;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Ticks: " + tickCount + ", Frames: " + frameCount);
                tickCount = 0;
                frameCount = 0;
            }
        }

        stop();
    }


    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public synchronized void start(){
        if(running)
            return;

        running = true;

        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
