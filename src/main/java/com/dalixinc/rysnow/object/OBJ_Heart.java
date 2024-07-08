package com.dalixinc.rysnow.object;

import com.dalixinc.rysnow.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject{
        GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel) {

        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/heart_blank.png"));
            image = utilityFunctions.scaledImage( image, gamePanel.tileSize, gamePanel.tileSize);
            image2 = utilityFunctions.scaledImage( image2, gamePanel.tileSize, gamePanel.tileSize);
            image3 = utilityFunctions.scaledImage( image3, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
