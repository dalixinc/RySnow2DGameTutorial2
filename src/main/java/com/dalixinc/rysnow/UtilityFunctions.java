package com.dalixinc.rysnow;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityFunctions {

    public BufferedImage scaledImage(BufferedImage rawImage, int newWidth, int newHeight) {
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, rawImage.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(rawImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        return scaledImage;
    }
}
