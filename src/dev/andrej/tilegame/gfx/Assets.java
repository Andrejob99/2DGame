package dev.andrej.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    //Menu sprites
    public static final int menuButtonWidth = 128,
                             menuButtonHeight = 64;

    public static BufferedImage menuStart0, menuStart1;

    public static BufferedImage[] menuSprites;

    //Player sprites
    private static final int playerWidth = 64,
                             playerHeight = 64;

    public static BufferedImage playerUp0, playerUp1, playerUp2, playerDown0, playerDown1, playerDown2,
                                playerLeft0, playerLeft1, playerLeft2, playerRight0, playerRight1, playerRight2;

    public static BufferedImage[] upSprites, downSprites, leftSprites, rightSprites;

    //Tile sprites
    private static final int tileWidth = 32,
                             tileHeight = 32;

    public static BufferedImage dirt, grass, rock, tree, wood, pebble;

    //Damage sprites
    public static BufferedImage cross;

    public static void init(){
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        //Menu
        menuStart0 = sheet.crop(menuButtonWidth, 0, menuButtonWidth, menuButtonHeight);
        menuStart1 = sheet.crop(menuButtonWidth, menuButtonHeight, menuButtonWidth, menuButtonHeight);

        menuSprites = new BufferedImage[]{menuStart0, menuStart1};

        //Player
        playerUp0 = playerSheet.crop(0, 0, playerWidth, playerHeight);
        playerUp1 = playerSheet.crop(playerWidth, 0, playerWidth, playerHeight);
        playerUp2 = playerSheet.crop(playerWidth * 2, 0, playerWidth, playerHeight);
        playerDown0 = playerSheet.crop(0, playerHeight, playerWidth, playerHeight);
        playerDown1 = playerSheet.crop(playerWidth, playerHeight, playerWidth, playerHeight);
        playerDown2 = playerSheet.crop(playerWidth * 2, playerHeight, playerWidth, playerHeight);
        playerLeft0 = playerSheet.crop(0, playerHeight * 2, playerWidth, playerHeight);
        playerLeft1 = playerSheet.crop(playerWidth, playerHeight * 2, playerWidth, playerHeight);
        playerLeft2 = playerSheet.crop(playerWidth * 2, playerHeight * 2, playerWidth, playerHeight);
        playerRight0 = playerSheet.crop(0, playerHeight * 3, playerWidth, playerHeight);
        playerRight1 = playerSheet.crop(playerWidth, playerHeight * 3, playerWidth, playerHeight);
        playerRight2 = playerSheet.crop(playerWidth * 2, playerHeight * 3, playerWidth, playerHeight);

        upSprites = new BufferedImage[]{playerUp0, playerUp1, playerUp0, playerUp2};
        downSprites = new BufferedImage[]{playerDown0, playerDown1, playerDown0, playerDown2};
        leftSprites = new BufferedImage[]{playerLeft0, playerLeft1, playerLeft0, playerLeft2};
        rightSprites = new BufferedImage[]{playerRight0, playerRight1, playerRight0, playerRight2};

        //Tiles
        dirt = sheet.crop(tileWidth, 0, tileWidth, tileHeight);
        grass = sheet.crop(tileWidth * 2, 0, tileWidth, tileHeight);
        rock = sheet.crop(tileWidth * 3, 0, tileWidth, tileHeight);
        tree = sheet.crop(0, tileHeight, tileWidth, tileHeight);

        //Damage
        cross = sheet.crop(tileWidth, tileHeight * 2, tileWidth, tileHeight);

        //Drops
        wood = sheet.crop(tileWidth, tileHeight * 3, tileWidth, tileHeight);
        pebble = sheet.crop(tileWidth, tileHeight * 3, tileWidth * 2, tileHeight);
    }
}