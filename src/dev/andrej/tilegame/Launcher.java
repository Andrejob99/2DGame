package dev.andrej.tilegame;
import dev.andrej.tilegame.display.Display;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Now we are gaming", 400, 600);
        game.start();
    }
}
