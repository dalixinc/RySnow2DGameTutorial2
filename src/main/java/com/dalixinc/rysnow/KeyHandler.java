package com.dalixinc.rysnow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, escapePressed;
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
