package com.dalixinc.rysnow.object;

import com.dalixinc.rysnow.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {

        public OBJ_Boots(GamePanel gamePanel) {

            name = "Boots";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/boots.png"));
                utilityFunctions.scaledImage( image, gamePanel.tileSize, gamePanel.tileSize);
            } catch (IOException e) {
                e.printStackTrace();
            }
            collision = true;
        }

}
