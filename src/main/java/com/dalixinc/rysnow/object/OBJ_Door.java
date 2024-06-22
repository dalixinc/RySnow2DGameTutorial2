package com.dalixinc.rysnow.object;

import com.dalixinc.rysnow.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{

        public OBJ_Door(GamePanel gamePanel) {

            name = "Door";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/door.png"));
                utilityFunctions.scaledImage( image, gamePanel.tileSize, gamePanel.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            collision = true;
        }
}
