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

        // TITLE STATE
       if (gamePanel.gameState == gamePanel.TITLE_STATE) {

           if (gamePanel.ui.titleScreenState==0) {

               switch (keyCode) {
                   case KeyEvent.VK_UP:
                       gamePanel.ui.inputNum--;
                       if (gamePanel.ui.inputNum < 0) gamePanel.ui.inputNum = 2;
                       System.out.println("UP key pressed");
                       break;
                   case KeyEvent.VK_DOWN:
                       gamePanel.ui.inputNum++;
                       System.out.println("DOWN key pressed");
                       if (gamePanel.ui.inputNum > 2) gamePanel.ui.inputNum = 0;
                       break;
                   case KeyEvent.VK_ENTER:
                       switch (gamePanel.ui.inputNum) {
                           case 0:
                               gamePanel.gameState = gamePanel.PLAY_STATE;
                               ///gamePanel.ui.titleScreenState = 1;
                               gamePanel.playMusic(0); // TODo: Not here probably
                               break;
                           case 1:
                               // Add Later (Load Game)
                            /*gamePanel.gameState = gamePanel.PLAY_STATE;
                            gamePanel.playMusic(1);
                            break;*/
                           case 2:
                               System.exit(0);
                               break;
                       }
                       System.out.println("Enter key pressed");
                       break;
                   default:
                       System.out.println("Unregistered Key pressed");
               }
           } else if (gamePanel.ui.titleScreenState==1) {

               switch (keyCode) {
                   case KeyEvent.VK_UP:
                       gamePanel.ui.inputNum--;
                       if (gamePanel.ui.inputNum < 0) gamePanel.ui.inputNum = 3;
                       System.out.println("UP key pressed");
                       break;
                   case KeyEvent.VK_DOWN:
                       gamePanel.ui.inputNum++;
                       System.out.println("DOWN key pressed");
                       if (gamePanel.ui.inputNum > 3) gamePanel.ui.inputNum = 0;
                       break;
                   case KeyEvent.VK_ENTER:
                       switch (gamePanel.ui.inputNum) {
                           case 0:
                               System.out.println("You are a mighty Warrior!");
                               gamePanel.gameState = gamePanel.PLAY_STATE;
                               ///gamePanel.playMusic(0);
                               break;
                           case 1:
                               System.out.println("You are a powerful Wizard!");
                               gamePanel.gameState = gamePanel.PLAY_STATE;
                               ///gamePanel.playMusic(0);
                               break;
                           case 2:
                               System.out.println("You are a wily Burglar!");
                               gamePanel.gameState = gamePanel.PLAY_STATE;
                               ///gamePanel.playMusic(0);
                               break;
                           case 3:
                                gamePanel.ui.titleScreenState = 0;
                               break;
                       }
                       System.out.println("Enter key pressed");
                       break;
                   default:
                       System.out.println("Unregistered Key pressed");
               }
           }


       }
        // PLAY STATE
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
