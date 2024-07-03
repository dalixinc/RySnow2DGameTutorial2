package com.dalixinc.rysnow.entity;

import com.dalixinc.rysnow.GamePanel;
import com.dalixinc.rysnow.UtilityFunctions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    // GAME PANEL
    GamePanel gamePanel;

    // POSITION
    public int worldX, worldY;
    public int speed;

    // SPRITE INFO
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction ;
    public int spriteCounter = 0;
    public  int spriteNum = 1;

    // COLLISION INFO
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    // ENTITY ANIMATION
    public int actionLockCounter = 0;

    private boolean showCollisionRect = true;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {}

    public void update(){
        setAction();

        collisionOn = false;
        gamePanel.collisionChecker.checkCollision(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkCollisionWithPlayer(this);

        // IF COLLISION IS FALSE, THEN MOVE
        if(!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            spriteCounter = 0;
            spriteNum = (spriteNum == 1) ? 2 : 1;
        }
    }


    public void draw(Graphics g2d) {

        BufferedImage img = null;

        boolean animateWhenStill = false;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(     worldX + gamePanel.tileSize > gamePanel.player.worldX -gamePanel.player.screenX  &&
                worldX - gamePanel.tileSize  < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY ) {

                switch(direction) {
                    case "up":
                        if (spriteNum == 1) {
                            img = up1;
                        } if (spriteNum == 2) {
                        img = up2;
                    }
                        break;
                    case "down":
                        if (spriteNum == 1) {
                            img = down1;
                        } if (spriteNum == 2) {
                        img = down2;
                    }
                        break;
                    case "left":
                        if (spriteNum == 1) {
                            img = left1;
                        } if (spriteNum == 2) {
                        img = left2;
                    }
                        break;
                    case "right":
                        if (spriteNum == 1) {
                            img = right1;
                        } if (spriteNum == 2) {
                        img = right2;
                    }
                        break;
                }

            g2d.drawImage(img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
        if (showCollisionRect) {
            g2d.setColor(Color.RED);
            g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }


    }
    public BufferedImage setupEntityBufferedImage(String imagePath) {
        UtilityFunctions utilityFunctions = new UtilityFunctions();
        BufferedImage image   = null;

        try {
            image = ImageIO.read( getClass().getResourceAsStream( imagePath + ".png" ) );
            image  = utilityFunctions.scaledImage(image , gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
