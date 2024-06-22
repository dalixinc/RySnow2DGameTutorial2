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
    public int hasKey = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;  //2nd bit?

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 28;//25;
        worldY = gamePanel.tileSize * 27;//22;
        speed = 6;
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
        // animateWhenStill= keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed;
        if (animateWhenStill) {

            boolean keyIsPressed = keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed;

            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkCollision(this);

            // CHECK OBJECT COLLISION
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, THEN MOVE
            if(!collisionOn && keyIsPressed) {
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
            if (spriteCounter > 10) {
                spriteCounter = 0;
                spriteNum = (spriteNum == 1) ? 2 : 1;
            }
        }

    }

    public void pickUpObject (int objIndex) {
        if (objIndex != 999) {
            //gamePanel.superObjects[objIndex] = null;
            String objName = gamePanel.superObjects[objIndex].name;

            switch (objName) {
                case "Key":
                    gamePanel.playSFX(1);
                    hasKey++;
                    gamePanel.superObjects[objIndex] = null;
                    gamePanel.ui.showMessage("Picked up a key!");
                    System.out.println("PICKED UP KEYS: " + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gamePanel.playSFX(3);
                        hasKey--;
                        gamePanel.superObjects[objIndex] = null;
                        gamePanel.ui.showMessage("Opened a door!");
                        System.out.println("PICKED UP KEYS: " + hasKey);
                    } else {
                        gamePanel.ui.showMessage("You need a key to open this door!");
                    }
                    break;
                case "Boots":
                    gamePanel.playSFX(2);
                    speed += 2;
                    gamePanel.superObjects[objIndex] = null;
                    gamePanel.ui.showMessage("SPeed up!");
                    break;
                case "Chest":
                        gamePanel.ui.gameOver = true;
                        gamePanel.stopMusic();
                        gamePanel.playSFX(4);
                    break;
            }
            System.out.println("PICKED UP OBJECT");
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
