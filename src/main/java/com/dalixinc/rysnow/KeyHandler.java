package com.dalixinc.rysnow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escapePressed;
    //DEBUG

    boolean debugToggle = false;

    boolean zoomToggle = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

   @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Done with a switch statement
        switch (keyCode) {
            case KeyEvent.VK_W:
                upPressed = true;
                System.out.println("W key pressed");
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                System.out.println("S key pressed");
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                System.out.println("A key pressed");
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                System.out.println("D key pressed");
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                System.out.println("Space key pressed");
                break;
            case KeyEvent.VK_ESCAPE:
                escapePressed = true;
                System.out.println("Escape key pressed");
                break;
            // PAUSE
/*            case KeyEvent.VK_0:
                if (gamePanel.gameState == gamePanel.PLAY_STATE) {
                    gamePanel.gameState = gamePanel.PAUSE_STATE;
                    gamePanel.stopMusic();
                } else if (gamePanel.gameState == gamePanel.PAUSE_STATE) {
                    gamePanel.gameState = gamePanel.PLAY_STATE;
                    gamePanel.playMusic(0);
                }
                System.out.println("0 key pressed");
                break;*/

            // ZOOM TOGGLE
            case KeyEvent.VK_Z:
                System.out.println("Z key pressed - ZOOM Toggled");
                zoomToggle = !zoomToggle;
                if (!zoomToggle) {
                    gamePanel.zoomInOut(0);
                }
                break;
            case KeyEvent.VK_UP:
                System.out.println("UP arrow key pressed");
                if (zoomToggle) {
                    gamePanel.zoomInOut(1);
                }
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN arrow key pressed");
                if (zoomToggle) {
                    gamePanel.zoomInOut(-1);
                }
                break;

           // DEBUG TOGGLE
            case KeyEvent.VK_P:
                System.out.println("P key pressed - DEBUG Toggled");
                debugToggle = !debugToggle;
                break;
            default:
                System.out.println("Unregistered Key pressed");
        }
    }

    public void keyReleased(KeyEvent e) {

       int keyCode = e.getKeyCode();

       // Alternative to switch statement - if ladder
        if (keyCode == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
        // Not needed
    }


}
