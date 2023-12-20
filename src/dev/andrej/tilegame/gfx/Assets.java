package dev.andrej.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    //Player sprites
    private static final int playerWidth = 64,
                             playerHeight = 64;

    public static BufferedImage playerUp0, playerUp1, playerUp2, playerDown0, playerDown1, playerDown2,
                                playerLeft0, playerLeft1, playerLeft2, playerRight0, playerRight1, playerRight2;

    public static BufferedImage[] upSprites, downSprites, leftSprites, rightSprites;

    //Tile sprites
    private static final int width = 32,
                             height = 32;

    public static BufferedImage dirt, grass, rock, tree;

    public static void init(){
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet.png"));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

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

        upSprites = new BufferedImage[]{playerUp0, playerUp1, playerUp2};
        downSprites = new BufferedImage[]{playerDown0, playerDown1, playerDown2};
        leftSprites = new BufferedImage[]{playerLeft0, playerLeft1, playerLeft2};
        rightSprites = new BufferedImage[]{playerRight0, playerRight1, playerRight2};

        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        rock = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, height, width, height);
    }

}