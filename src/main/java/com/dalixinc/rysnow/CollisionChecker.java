package com.dalixinc.rysnow;

import com.dalixinc.rysnow.entity.Entity;

public class CollisionChecker {

    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkCollision(Entity entity) {
        double entityLeftWorldX = entity.worldX + entity.solidArea.x;
        double entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        double entityTopWorldY = entity.worldY + entity.solidArea.y;
        double entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        double entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        double entityRightCol = entityRightWorldX / gamePanel.tileSize;
        double entityTopRow = entityTopWorldY / gamePanel.tileSize;
        double entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)entityRightCol][(int)entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)entityLeftCol][(int)entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)entityRightCol][(int)entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)entityLeftCol][(int)entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)entityLeftCol][(int)entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[(int)entityRightCol][(int)entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[(int)entityRightCol][(int)entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index  = 999;

        for (int i = 0; i < gamePanel.superObjects.length; i++) {

            if (gamePanel.superObjects[i] != null) {

                //GET ENTITY SOLID AREA POSITION
                entity.solidArea.x = (int)entity.worldX  + entity.solidArea.x;
                entity.solidArea.y = (int)entity.worldY  + entity.solidArea.y;

                // GET OBJECT SOLID AREA POSITION
                gamePanel.superObjects[i].solidArea.x = gamePanel.superObjects[i].worldX + gamePanel.superObjects[i].solidArea.x;
                gamePanel.superObjects[i].solidArea.y = gamePanel.superObjects[i].worldY + gamePanel.superObjects[i].solidArea.y;

                // TODO cleanup the redundant code below
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.superObjects[i].solidArea)) {
                            if (gamePanel.superObjects[i].collision ) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            System.out.println("COLLISION UP");
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.superObjects[i].solidArea)) {
                            if (gamePanel.superObjects[i].collision ) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            System.out.println("COLLISION DOWN");
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.superObjects[i].solidArea)) {
                            if (gamePanel.superObjects[i].collision ) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            System.out.println("COLLISION LEFT");
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.superObjects[i].solidArea)) {
                            if (gamePanel.superObjects[i].collision ) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                            System.out.println("COLLISION RIGHT");
                        }
                        break;
                }
                entity.solidArea.x = (int) entity.solidAreaDefaultX;
                entity.solidArea.y = (int) entity.solidAreaDefaultY;
                gamePanel.superObjects[i].solidArea.x = gamePanel.superObjects[i].solidAreaDefaultX;
                gamePanel.superObjects[i].solidArea.y = gamePanel.superObjects[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
