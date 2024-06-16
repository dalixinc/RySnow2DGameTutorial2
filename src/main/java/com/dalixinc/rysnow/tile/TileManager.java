package com.dalixinc.rysnow.tile;

import com.dalixinc.rysnow.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager  {

    GamePanel gamePanel;
    Tile[] tile;

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprites/tiles/water.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
/*        g2d.drawImage(tile[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[1].image, 48, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(tile[2].image, 96, 0, gamePanel.tileSize, gamePanel.tileSize, null);*/

        int row = 0;
        int col = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && gamePanel.maxScreenRow > row) {
            g2d.drawImage(tile[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }

    }
}
