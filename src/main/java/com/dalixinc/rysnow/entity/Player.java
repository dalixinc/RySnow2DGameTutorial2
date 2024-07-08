package com.dalixinc.rysnow.entity;

import com.dalixinc.rysnow.GamePanel;
import com.dalixinc.rysnow.KeyHandler;
import com.dalixinc.rysnow.UtilityFunctions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {


    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;

    // For Tile-Based Collision
    boolean moving = false;
    int pixelCounter = 0;

    // DEBUG OPTIONS
    private boolean showCollisionRect = true;

    // COLLISION MODES
    int collisionMode = 0;  //0 = Standard collision mode, 1 = Tile-Based
    private boolean autoInterract = true; // Whether the player can auto-interract with NPCs or must press enter

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;  //2nd bit?

        // Mode 0: Standard Collision Mode (Default)
        solidArea = new Rectangle(8, 16, 32, 32);

        // Mode 1: Tile-Based Collision Mode
        if (collisionMode == 1) {
            solidArea.x = 1;
            solidArea.y = 1;
            solidArea.width = gamePanel.tileSize - 2;
            solidArea.height = gamePanel.tileSize - 2;
        }

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // SETUP methods
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

        String path = "/sprites/player/walking/";

        up1 = setupEntityBufferedImage(path + "boy_up_1");
        up2 = setupEntityBufferedImage(path + "boy_up_2");
        down1 = setupEntityBufferedImage(path + "boy_down_1");
        down2 = setupEntityBufferedImage(path + "boy_down_2");
        left1 = setupEntityBufferedImage(path + "boy_left_1");
        left2 = setupEntityBufferedImage(path + "boy_left_2");
        right1 = setupEntityBufferedImage(path + "boy_right_1");
        right2 = setupEntityBufferedImage(path + "boy_right_2");

    }

    public BufferedImage setupPlayerBufferedImage(String imageName) {
        UtilityFunctions utilityFunctions = new UtilityFunctions();
        BufferedImage image   = null;

        try {
            image = ImageIO.read( getClass().getResourceAsStream( "/sprites/player/walking/" + imageName + ".png" ) );
            image  = utilityFunctions.scaledImage(image , gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    public void update() {
        if (collisionMode == 0) {
            updateMode0();
        } else if (collisionMode == 1) {
            updateMode1();
        }
    }

    private void updateMode0() {
        // Mode 0: Standard Collision Mode
        boolean animateWhenStill = true;
        animateWhenStill= keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed;
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

            // CHECK NPC COLLISION
            int npcIndex = gamePanel.collisionChecker.checkEntityCollision(this, gamePanel.npc);
            interractionWithNPC(npcIndex);

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
            if (spriteCounter > 12) {
                spriteCounter = 0;
                spriteNum = (spriteNum == 1) ? 2 : 1;
            }
        }  else {
            standCounter++;
            //Snaps back to Sprite 1
            if (standCounter == gamePanel.FPS / 3) {
                spriteNum = 1;
                standCounter = 0;
            }
        }
    }


    // This method is supposed to ensure that each move completes to a new tile - but I can't get it to work
    private void updateMode1() {
        boolean animateWhenStill = true;
        animateWhenStill= keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed;


        if (animateWhenStill) {


            if (!moving) {

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

                System.out.println("Setting moving true!!");
                moving = true;

                // CHECK TILE COLLISION
                collisionOn = false;
                gamePanel.collisionChecker.checkCollision(this);

                // CHECK OBJECT COLLISION
                int objIndex = gamePanel.collisionChecker.checkObject(this, true);
                pickUpObject(objIndex);
            }

            if (moving) {

               //boolean keyIsPressed = keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed;

                // IF COLLISION IS FALSE, THEN MOVE
                if(!collisionOn) {
                    System.out.println("MOVING!!  " + pixelCounter  + " direction: " + direction + " worldX: " + worldX + " worldY: " + worldY) ;
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

                pixelCounter += speed;
                if (pixelCounter >= gamePanel.tileSize) {
                    moving = false;
                    pixelCounter = 0;
                } else {
                    System.out.println("Pixel Counter: " + pixelCounter);
                }
           }
        } else {
            standCounter++;
            //Snaps back to Sprite 1
            if (standCounter == gamePanel.FPS / 3) {
                spriteNum = 1;
                standCounter = 0;
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

    public void interractionWithNPC(int npcIndex) {
        if (npcIndex != 999) {
            System.out.println("Bump into NPC!");
            ///gamePanel.npc[npcIndex].interact();

            if (gamePanel.keyHandler.enterPressed || gamePanel.keyHandler.spacePressed || autoInterract) {
                gamePanel.gameState = gamePanel.DIALOGUE_STATE;
                gamePanel.npc[npcIndex].speak();
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

        //g2d.drawImage(img, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        g2d.drawImage(img, screenX, screenY, null);

        if (showCollisionRect) {
            g2d.setColor(Color.RED);
            g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}
