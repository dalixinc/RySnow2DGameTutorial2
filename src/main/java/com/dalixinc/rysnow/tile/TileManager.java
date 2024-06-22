package com.dalixinc.rysnow.tile;

import com.dalixinc.rysnow.GamePanel;
import com.dalixinc.rysnow.UtilityFunctions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager  {

    GamePanel gamePanel;
    public Tile[] tile;
    public int [][] mapTileNum;

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldCol];

        getTileImage();
        loadMap("/maps/world02.txt");
    }

    public void getTileImage() {

        setupBufferedImages(0, "grass", false);
        setupBufferedImages(1, "wall", true);
        setupBufferedImages(2, "water", true);
        setupBufferedImages(3, "earth", false);
        setupBufferedImages(4, "tree", true);
        setupBufferedImages(5, "sand", false);

    }

    public void setupBufferedImages(int index, String imageName, boolean collision) {

        UtilityFunctions utilityFunctions = new UtilityFunctions();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/" + imageName + ".png"));
            tile[index].image = utilityFunctions.scaledImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxWorldCol && gamePanel.maxWorldRow > row) {
                String line = br.readLine();

                while (col < gamePanel.maxWorldCol ) {
                    String[] tokens = line.split(" ");
                    int num = Integer.parseInt(tokens[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
/*        g2d.drawImage(tile[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[1].image, 48, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[2].image, 96, 0, gamePanel.tileSize, gamePanel.tileSize, null);*/

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.maxWorldCol && gamePanel.maxWorldRow > worldRow) {

            int tn = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;    ////gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;    ////gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;

        if(     worldX + gamePanel.tileSize > gamePanel.player.worldX -gamePanel.player.screenX  &&
                worldX - gamePanel.tileSize  < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY ) {
                //g2d.drawImage(tile[tn].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2d.drawImage(tile[tn].image, screenX, screenY, null);
            }

           //// - g2d.drawImage(tile[tn].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            worldCol++;

            if(worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
