package com.dalixinc.rysnow;

import com.dalixinc.rysnow.entity.Entity;

public class CollisionChecker {

    private final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkCollision(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
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
                entity.solidArea.x = entity.worldX  + entity.solidArea.x;
                entity.solidArea.y = entity.worldY  + entity.solidArea.y;

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
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.superObjects[i].solidArea.x = gamePanel.superObjects[i].solidAreaDefaultX;
                gamePanel.superObjects[i].solidArea.y = gamePanel.superObjects[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // NPC OR MONSTER COLLISION
    public  int checkEntityCollision(Entity entity, Entity[] target) {
        int index  = 999;

        for (int i = 0; i < target.length; i++) {

            if (target[i] != null) {

                //GET ENTITY SOLID AREA POSITION
                entity.solidArea.x = entity.worldX  + entity.solidArea.x;
                entity.solidArea.y = entity.worldY  + entity.solidArea.y;

                // GET OBJECT SOLID AREA POSITION
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                // TODO cleanup the redundant code below
                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionOn = true;
                                index = i;
                                System.out.println("COLLISION UP");
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            System.out.println("COLLISION DOWN");
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            System.out.println("COLLISION LEFT");
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                            System.out.println("COLLISION RIGHT");
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkCollisionWithPlayer(Entity entity) {
        //GET ENTITY SOLID AREA POSITION
        entity.solidArea.x = entity.worldX  + entity.solidArea.x;
        entity.solidArea.y = entity.worldY  + entity.solidArea.y;

        // GET OBJECT SOLID AREA POSITION
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        // TODO cleanup the redundant code below
        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                    System.out.println("COLLISION UP");
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                    System.out.println("COLLISION DOWN");
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                    System.out.println("COLLISION LEFT");
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
                    entity.collisionOn = true;
                    System.out.println("COLLISION RIGHT");
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
    }

}
