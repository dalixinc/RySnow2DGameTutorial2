package com.dalixinc.rysnow.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    // POSITION
    public int worldX, worldY;
    public int speed;

    // SPRITE INFO
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction ;
    public int spriteCounter = 0;
    public  int spriteNum = 1;

    // COLLISION INFO
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
