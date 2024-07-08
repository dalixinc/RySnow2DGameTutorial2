package com.dalixinc.rysnow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escapePressed, enterPressed;

    //DEBUG
    boolean debugToggle = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

   @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

       if (gamePanel.gameState == gamePanel.PLAY_STATE) {
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
               case KeyEvent.VK_ENTER:
                   enterPressed = true;
                   System.out.println("Enter key pressed");
                   break;
               case KeyEvent.VK_0:
                       gamePanel.gameState = gamePanel.PAUSE_STATE;
                       gamePanel.stopMusic();
                   System.out.println("0 key pressed");
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

       // PAUSE STATE
       else if (gamePanel.gameState == gamePanel.PAUSE_STATE) {
           switch (keyCode) {
               case KeyEvent.VK_0:
                       gamePanel.gameState = gamePanel.PLAY_STATE;
                       gamePanel.playMusic(0);
                   System.out.println("0 key pressed");
                   break;
               case KeyEvent.VK_ESCAPE:  // Alternative way to exit pause state
                   System.out.println("Escape key pressed");
                   gamePanel.gameState = gamePanel.PLAY_STATE;
                   gamePanel.playMusic(0);
                   break;
               default:
                   System.out.println("Unregistered Key pressed");
           }
       }

       // DIALOGUE STATE
        else if (gamePanel.gameState == gamePanel.DIALOGUE_STATE) {
            switch (keyCode) {
                case KeyEvent.VK_SPACE:
                    gamePanel.gameState = gamePanel.PLAY_STATE;
                    System.out.println("Space key pressed");
                    break;
                case KeyEvent.VK_ESCAPE:
                    gamePanel.gameState = gamePanel.PLAY_STATE;
                    System.out.println("Escape key pressed");
                    break;
                default:
                    System.out.println("Unregistered Key pressed");
            }
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
