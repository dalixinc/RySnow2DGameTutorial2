package com.dalixinc.rysnow;

import com.dalixinc.rysnow.entity.Entity;
import com.dalixinc.rysnow.entity.Player;
import com.dalixinc.rysnow.object.SuperObject;
import com.dalixinc.rysnow.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int FPS = 60;  // Frames per second

    // SCREEN SETTINGS
    final int originalTileSize = 16;   // 16 x 16 tiles
    final int  scale = 3;               // 3x scale

    public final int tileSize = originalTileSize * scale;  // 48 x 48 pixels
    final int maxScreenCol = 16;                    // 16 columns
    final int maxScreenRow = 12;                    // 12 rows
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final  int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // GAME SETTINGS
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound sfx = new Sound();
    TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, keyHandler);
    public SuperObject[] superObjects = new SuperObject[10];
    public Entity[] npc = new Entity[10];

    // GAME STATE
    public int gameState;
    public static final int TITLE_STATE = 0;
    public static final int PLAY_STATE   = 1;
    public static final int PAUSE_STATE = 2;
    public static final int DIALOGUE_STATE  = 3;


    public GamePanel() {

        this.setPreferredSize( new Dimension( screenWidth, screenHeight ) );
        this.setBackground( Color.BLACK );
        this.setDoubleBuffered( true );
        this.addKeyListener( keyHandler );
        this.setFocusable( true );
    }

    public void setUpGame() {

        assetSetter.setObjects();
        assetSetter.setNPC();
        // playMusic(0);
        gameState = TITLE_STATE;
    }

    public void startGameThread() {
        gameThread = new Thread( this );
        gameThread.setName( "Game Thread");
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
        ////long count = 0;
        long timer = 0;
        int drawCount = 0;

        // Game Loop
        while( gameThread != null ) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            ////System.out.println( "Count: " + count++ + "  Delta: " + delta);

            if ( delta >= 1 ) {
                update();
                repaint();
                delta--;
                drawCount++;
                ////count = 0;
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

        if (gameState == PLAY_STATE) {
            // PLAYER
            player.update();
            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == PAUSE_STATE) {
            // Do nothing
        }


        // For Test Alone
/*        if( keyHandler.upPressed == true ) {
            playerY -= playerSpeed;
        } else if( keyHandler.downPressed == true ) {
            playerY += playerSpeed;
        } else if( keyHandler.leftPressed == true ) {
            playerX -= playerSpeed;
        } else if( keyHandler.rightPressed == true ) {
            playerX += playerSpeed;
        }*/
    }
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );

        Graphics2D g2d = (Graphics2D) g;

        //DEBUG INFO
        long drawStartTime = 0;
        if (keyHandler.debugToggle == true) {
            drawStartTime = System.nanoTime();
        }


        // TITLE SCREEN
        if (gameState == TITLE_STATE) {
            ui.draw(g2d);
        }

        // OTHERS
        else {
            // DRAW TILES
            tileManager.draw(g2d);

            //  DRAW OBJECTS
            for (int i = 0; i < superObjects.length; i++) {
                if (superObjects[i] != null) {
                    superObjects[i].draw(g2d, this);
                }
            }

            // DRAW NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].draw(g2d);
                }
            }

            // DRAW PLAYER
            player.draw(g2d);

            // DRAW UI
            ui.draw(g2d);

            // DEBUG INFO
            if (keyHandler.debugToggle == true) {

                long drawEndTime = System.nanoTime();
                long drawTime = drawEndTime - drawStartTime;
                g2d.setColor( Color.yellow);
                g2d.drawString( "Draw Time: " + drawTime / 1_000 + " μs", 10, 440 ); // 1 ms = 1,000 μs
                System.out.println( "Draw Time: " + drawTime / 1_000 + " μs"); // Divide by 1_000_000  to convert to ms
                System.out.println( "Draw Time: " + drawTime  + " ns");

                g2d.setColor( Color.WHITE );
                g2d.drawString( "Player X (Col) : " + player.worldX + " (" + (player.worldX / tileSize + 1) + ")", 10, 260 );
                g2d.drawString( "Player Y (Row) : " + player.worldY + " (" + (player.worldY / tileSize + 1) + ")", 10, 320 );
                //.drawString( "Player Row: " + player.worldX, 10, 340 );
                //g2d.drawString( "Player Col: " + player.screenX, 10, 360 );
                g2d.drawString( "Player Direction: " + player.direction, 10, 380 );
            }
        }

        // For Test only
        /*  g2d.setColor( Color.WHITE );
            g2d.fillRect( playerX, playerY, tileSize, tileSize);*/

        g2d.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSFX(int i) {
        sfx.setFile(i);
        sfx.play();
    }
}
