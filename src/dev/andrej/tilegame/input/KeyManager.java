package dev.andrej.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean w, a, s, d, e, q, shift, up, down, left, right;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        //Movement keys
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        shift = keys[KeyEvent.VK_SHIFT];

        //Attack keys
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];

        //ACTION KEYS
        e = keys[KeyEvent.VK_E];
        q = keys[KeyEvent.VK_Q];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
