package com.dalixinc.rysnow.object;

import com.dalixinc.rysnow.GamePanel;
import com.dalixinc.rysnow.UtilityFunctions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); //   TODO 48 is the default size of the object - shouldn't be hardcoded
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityFunctions utilityFunctions = new UtilityFunctions();


    public void draw(Graphics2D g2d, GamePanel gamePanel) {

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(     worldX + gamePanel.tileSize > gamePanel.player.worldX -gamePanel.player.screenX  &&
                worldX - gamePanel.tileSize  < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY ) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

}
