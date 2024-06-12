package com.dalixinc.rysnow;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private static final int FPS = 60;  // Frames per second

    // SCREEN SETTINGS

    final int originalTileSize = 16;   // 16 x 16 tiles
    final int  scale = 3;               // 3x scale

    final int tileSize = originalTileSize * scale;  // 48 x 48 pixels
    final int maxScreenCol = 16;                    // 16 columns
    final int maxScreenRow = 12;                    // 12 rows
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;


    // Set Player's initial position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {

        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.BLACK );
        this.setDoubleBuffered( true );
        this.addKeyListener( keyHandler );
        this.setFocusable( true );


    }

    public void startGameThread() {
        gameThread = new Thread( this );
        gameThread.start();
    }

    // The sleep method of loop tinimg is not recommended for games
    //// @Override
    public void runSleep() {

        // Game Loop

        // 1 UPDATE: update information like player position, enemy position, etc.
        // update();

        // 2 RENDER: render the game state to the screen
        //repaint();

        double drawInterval = 1_000_000_000 / FPS;   // 0.01666 seconds (16.7 ms)
        double nextDrawTime = System.nanoTime() + drawInterval;


       /// long count = 0;
        while( gameThread != null ) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                if( remainingTime < 0 ) {remainingTime = 0;}     // Prevent negative time (Safety Valve)

                Thread.sleep( (long) (remainingTime / 1_000_000) );
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                //e.printStackTrace();
                throw new RuntimeException(e);
            }

            /// System.out.println( "Game Loop is running!!!! " + count++ );
        }
    }

    // Alternative to run() method (Delta/Accumulator)

     @Override
     public void run() {

        // Timer Vars
        double drawInterval = 1_000_000_000 / FPS;   // 0.01666 seconds (16.7 ms)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // "Proof" Vars
        //// long count = 0;
        long timer = 0;
        int drawCount = 0;

        // Game Loop
        while( gameThread != null ) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            //// System.out.println( "Count: " + count++ + "  Delta: " + delta);

            if ( delta >= 1 ) {
                update();
                repaint();
                delta--;
                drawCount++;
                //// System.out.println( "Delta Final: " + delta);
                //// System.exit(0);
            }

            if ( timer >= 1_000_000_000 ) {
                System.out.println( "FPS: " + drawCount );
                drawCount = 0;
                timer = 0;
            }
        }
     }



    public void update() {
        // Update game state

        if( keyHandler.upPressed == true ) {
            playerY -= playerSpeed;
        } else if( keyHandler.downPressed == true ) {
            playerY += playerSpeed;
        } else if( keyHandler.leftPressed == true ) {
            playerX -= playerSpeed;
        } else if( keyHandler.rightPressed == true ) {
            playerX += playerSpeed;
        }
    }
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor( Color.WHITE );
        g2d.fillRect( playerX, playerY, tileSize, tileSize);
        g2d.dispose();

    }
}
