package com.dalixinc.rysnow.object;

import com.dalixinc.rysnow.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{

        public OBJ_Chest(GamePanel gamePanel) {

            name = "Chest";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/chest.png"));
                utilityFunctions.scaledImage( image, gamePanel.tileSize, gamePanel.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //collision = true;
        }
}
