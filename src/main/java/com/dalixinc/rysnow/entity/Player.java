package com.dalixinc.rysnow.entity;

import com.dalixinc.rysnow.GamePanel;
import com.dalixinc.rysnow.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;  //2nd bit?
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 27;
        worldY = gamePanel.tileSize * 22;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        // Load player images
        try {
            up1 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_up_1.png" ) );
            up2 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_up_2.png") );
            down1 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_down_1.png" ) );
            down2 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_down_2.png" ) );
            left1 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_left_1.png" ) );
            left2 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_left_2.png") );
            right1 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_right_1.png" ) );
            right2 = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/boy_right_2.png") );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        boolean animateWhenStill = true;
        /// animateWhenStill= keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed;

        if (animateWhenStill) {
            if (keyHandler.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            if (keyHandler.downPressed) {
                direction = "down";
                worldY += speed;
            }
            if (keyHandler.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            if (keyHandler.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                spriteCounter = 0;
                spriteNum = (spriteNum == 1) ? 2 : 1;
            }
        }
    }

    public void draw(Graphics g2d) {
        // g2d.setColor( Color.WHITE );
        // g2d.fillRect( x, y, gamePanel.tileSize, gamePanel.tileSize);
        BufferedImage img = null;

        boolean animateWhenStill = false;

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
}
