package com.dalixinc.rysnow;

import com.dalixinc.rysnow.object.OBJ_Boots;
import com.dalixinc.rysnow.object.OBJ_Chest;
import com.dalixinc.rysnow.object.OBJ_Door;
import com.dalixinc.rysnow.object.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObjects()  {

        gamePanel.superObjects[0] = new OBJ_Key();
        gamePanel.superObjects[0].worldX = 1 * gamePanel.tileSize;
        gamePanel.superObjects[0].worldY = 1 * gamePanel.tileSize;

        gamePanel.superObjects[1] = new OBJ_Key();
        gamePanel.superObjects[1].worldX = 33 * gamePanel.tileSize;
        gamePanel.superObjects[1].worldY = 21 * gamePanel.tileSize;

        gamePanel.superObjects[2] = new OBJ_Key();
        gamePanel.superObjects[2].worldX = 26 * gamePanel.tileSize;
        gamePanel.superObjects[2].worldY = 46 * gamePanel.tileSize;

        gamePanel.superObjects[3] = new OBJ_Door();
        gamePanel.superObjects[3].worldX = 4 * gamePanel.tileSize;
        gamePanel.superObjects[3].worldY = 1 * gamePanel.tileSize;

        gamePanel.superObjects[4] = new OBJ_Door();
        gamePanel.superObjects[4].worldX = 9 * gamePanel.tileSize;
        gamePanel.superObjects[4].worldY = 25 * gamePanel.tileSize;

        gamePanel.superObjects[5] = new OBJ_Door();
        gamePanel.superObjects[5].worldX = 37 * gamePanel.tileSize;
        gamePanel.superObjects[5].worldY = 24 * gamePanel.tileSize;

        gamePanel.superObjects[6] = new OBJ_Chest();
        gamePanel.superObjects[6].worldX = 9 * gamePanel.tileSize;
        gamePanel.superObjects[6].worldY = 29 * gamePanel.tileSize;

        gamePanel.superObjects[7] = new OBJ_Boots();
        gamePanel.superObjects[7].worldX = 45 * gamePanel.tileSize;
        gamePanel.superObjects[7].worldY = 27 * gamePanel.tileSize;

    }
}
