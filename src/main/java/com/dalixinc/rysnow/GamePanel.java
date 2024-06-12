package com.dalixinc.rysnow;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS

    final int originalTileSize = 16;   // 16 x 16 tiles
    final int  scale = 3;               // 3x scale

    final int tileSize = originalTileSize * scale;  // 48 x 48 pixels
    final int maxScreenCol = 16;                    // 16 columns
    final int maxScreenRow = 12;                    // 12 rows
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels


    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.BLACK );
        this.setDoubleBuffered( true );

    }

    public void startGameThread() {
        gameThread = new Thread( this );
        gameThread.start();
    }

    @Override
    public void run() {

        // Game Loop

        // 1 UPDATE: update information like player position, enemy position, etc.
        update();

        // 2 RENDER: render the game state to the screen
        repaint();

        long count = 0;
        while( gameThread != null ) {

            System.out.println( "Game Loop is running!!!! " + count++ );
        }
    }

    public void update() {
        // Update game state
    }
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( Color.WHITE );
        g2d.fillRect( 100, 100, tileSize, tileSize);
        g2d.dispose();

    }
}
