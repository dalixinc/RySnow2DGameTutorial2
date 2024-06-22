package com.dalixinc.rysnow.object;

import com.dalixinc.rysnow.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    GamePanel gamePanel;

    public OBJ_Key(GamePanel gamePanel) {

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/key.png"));
            utilityFunctions.scaledImage( image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
