package com.dalixinc.rysnow;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // SCREEN SETTINGS

    final int originalTileSize = 16;   // 16 x 16 tiles
    final int  scale = 3;               // 3x scale

    final int tileSize = originalTileSize * scale;  // 48 x 48 pixels
    final int maxScreenCol = 16;                    // 16 columns
    final int maxScreenRow = 12;                    // 12 rows
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public GamePanel() {

        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.BLACK );
        this.setDoubleBuffered( true );

    }

}
