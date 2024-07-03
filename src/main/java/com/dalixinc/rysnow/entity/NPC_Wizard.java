package com.dalixinc.rysnow.entity;

import com.dalixinc.rysnow.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_Wizard extends Entity{

    public  NPC_Wizard(GamePanel gamePanel) {
        super(gamePanel);
        //this.worldX = 0;
        //this.worldY = 0;
        this.speed = 1;
        this.direction = "down";

        getWizardImage();

        solidArea = new Rectangle(8, 8, 32, 32);

        //this.solidAreaDefaultX = 0;
        //this.solidAreaDefaultY = 0;
        //this.solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, 32, 32);
    }

    public void getWizardImage() {
        // Load wizard images

        String path = "/sprites/npc/";

        up1 = setupEntityBufferedImage(path + "oldman_up_1");
        up2 = setupEntityBufferedImage(path + "oldman_up_2");
        down1 = setupEntityBufferedImage(path + "oldman_down_1");
        down2 = setupEntityBufferedImage(path + "oldman_down_2");
        left1 = setupEntityBufferedImage(path + "oldman_left_1");
        left2 = setupEntityBufferedImage(path + "oldman_left_2");
        right1 = setupEntityBufferedImage(path + "oldman_right_1");
        right2 = setupEntityBufferedImage(path + "oldman_right_2");

    }

    // The Basic Wizard AI
    @Override
    public void setAction() {

        actionLockCounter++;
        if (collisionOn) {actionLockCounter = 120;}
        if(actionLockCounter == 120) {

            Random random = new Random();
            int randomNum = random.nextInt(100 ) + 1;

            if (randomNum <= 25) {
                direction = "up";
            } else if (randomNum > 25 && randomNum <= 50) {
                direction = "down";
            } else if (randomNum > 50 && randomNum <= 75) {
                direction = "left";
            } else if (randomNum > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }


    }
}
