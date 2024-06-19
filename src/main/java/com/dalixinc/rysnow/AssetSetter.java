package com.dalixinc.rysnow;

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
        gamePanel.superObjects[0].worldX = 22 * gamePanel.tileSize;
        gamePanel.superObjects[0].worldY = 9 * gamePanel.tileSize;

        gamePanel.superObjects[1] = new OBJ_Key();
        gamePanel.superObjects[1].worldX = 22 * gamePanel.tileSize;
        gamePanel.superObjects[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.superObjects[2] = new OBJ_Key();
        gamePanel.superObjects[2].worldX = 14 * gamePanel.tileSize;
        gamePanel.superObjects[2].worldY = 31 * gamePanel.tileSize;

        gamePanel.superObjects[3] = new OBJ_Door();
        gamePanel.superObjects[3].worldX = 35 * gamePanel.tileSize;
        gamePanel.superObjects[3].worldY = 24 * gamePanel.tileSize;

        gamePanel.superObjects[4] = new OBJ_Chest();
        gamePanel.superObjects[4].worldX = 22 * gamePanel.tileSize;
        gamePanel.superObjects[4].worldY = 36 * gamePanel.tileSize;

    }
}
