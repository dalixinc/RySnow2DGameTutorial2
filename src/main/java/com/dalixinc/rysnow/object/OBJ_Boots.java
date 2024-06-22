package com.dalixinc.rysnow.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {

        public OBJ_Boots() {

            name = "Boots";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/sprites/objects/boots.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            collision = true;
        }

}