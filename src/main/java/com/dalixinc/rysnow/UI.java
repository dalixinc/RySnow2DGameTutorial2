package com.dalixinc.rysnow;

import com.dalixinc.rysnow.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Graphics2D g2d;  //TODO: Not certain this is required
    Font arial_40, ariel_80B, maruMonica, purisaBold, bauhaus, blackadder ;
    BufferedImage keyImage; //TODO: Remove this
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameOver = false;
    double playTime = 0;
    public String currentDialogue = "";
    public int inputNum = 0;
    DecimalFormat df = new DecimalFormat("#.##");  // 2 decimal places ("#0.00")
    private static final int HEADING_SIZE = 80;  // 58

    private static final int MENU_SIZE = 36; // v24

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        ariel_80B = new Font("Arial", Font.BOLD, 80);

        // IMPORT FONTS
        InputStream is = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
        try {
            maruMonica =  Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/Purisa Bold.ttf");
            purisaBold = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/BAUHS93.ttf");
            bauhaus = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/fonts/ITCBLKAD.ttf");
            blackadder = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OBJ_Key key = new OBJ_Key(gamePanel); //TODO: Remove this
        keyImage = key.image; //TODO: Remove this
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }
    public void draw(Graphics2D graphics2d) {

        this.g2d = graphics2d;  //TODO: Not certain this is required - either this or we pass around as a method parameter.

        //DEFAULT FONT AND COLOUR
        graphics2d.setFont(maruMonica);  // previously ariel_40
        //graphics2d.setFont(purisaBold);  // previously ariel_40
        graphics2d.setRenderingHint((RenderingHints.KEY_ANTIALIASING), RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2d.setColor(Color.WHITE);

        // TITLE STATE
        if (gamePanel.gameState == gamePanel.TITLE_STATE) {
                drawTitleScreen(graphics2d);
        }

        // PLAY STATE
        if (gamePanel.gameState == gamePanel.PLAY_STATE) {
            drawPlayScreen(graphics2d);
        }
        // PAUSE STATE
        else if (gamePanel.gameState == gamePanel.PAUSE_STATE) {
            drawPauseScreen(graphics2d);
        }
        // DIALOGUE STATE
        else if (gamePanel.gameState == gamePanel.DIALOGUE_STATE) {
            drawDialogueScreen(graphics2d);
        }

    }

    private void drawTitleScreen(Graphics2D graphics2d) {

        graphics2d.setColor(new Color(0x21, 0x4B, 0, 255));
        graphics2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        // TITLE NAME
        ///graphics2d.setFont(ariel_80B);
        graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD, HEADING_SIZE));
        String text = "AWESOME ADVENTURE";
        int x = getXforCenteredText(text, graphics2d);
        int y = gamePanel.tileSize * 3;

        // SHADOW
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawString(text, x + 5, y + 5);

        // MAIN COLOUR
        graphics2d.setColor(Color.WHITE);
        graphics2d.drawString(text, x, y);

        // TITLE IMAGE
        x = gamePanel.screenWidth / 2 - gamePanel.tileSize;
        y = gamePanel.tileSize * 4;
        graphics2d.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);

        // PRESS ENTER
        graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD, MENU_SIZE));

        text = "NEW GAME";
        x = getXforCenteredText(text, graphics2d);
        y += gamePanel.tileSize * 3.5;
        graphics2d.drawString(text, x, y);
        if (inputNum == 0) {
            graphics2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text, graphics2d);
        y += gamePanel.tileSize;
        graphics2d.drawString(text, x, y);
        if (inputNum == 1) {
            graphics2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "EXIT GAME";
        x = getXforCenteredText(text, graphics2d);
        y += gamePanel.tileSize;
        graphics2d.drawString(text, x, y);
        if (inputNum == 2) {
            graphics2d.drawString(">", x - gamePanel.tileSize, y);
        }
    }

    private void drawPlayScreen(Graphics2D graphics2d) {
        if (gameOver) {
            String text = "GAME OVER";
            int textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
            int x = gamePanel.screenWidth / 2 - textLength / 2;
            int y = gamePanel.screenHeight / 2 - gamePanel.tileSize * 3;
            graphics2d.drawString(text, x, y);

            text = "Your time is: " + df.format(playTime) + " !!";
            textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + gamePanel.tileSize * 4;
            graphics2d.drawString(text, x, y);

            graphics2d.setFont(ariel_80B);
            graphics2d.setColor(Color.YELLOW);
            text = "Congratulations!";
            textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 + gamePanel.tileSize * 2;
            graphics2d.drawString(text, x, y);

            gamePanel.gameThread = null;

        } else {
            graphics2d.setColor(Color.WHITE);
            graphics2d.setFont(arial_40);
            graphics2d.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize , gamePanel.tileSize , null);
            graphics2d.drawString("x " + gamePanel.player.hasKey, 74, 65);
            //graphics2d.drawString("Keys: " + gamePanel.player.hasKey, 20, 50);

            //TIME
            playTime += 1.0 / gamePanel.FPS;
            ///graphics2d.drawString("Time: " + (int) playTime, gamePanel.tileSize * 11, 65);
            graphics2d.drawString("Time: " + df.format(playTime) , gamePanel.tileSize * 11, 65);

            //MESSAGE
            if (messageOn) {
                // graphics2d.setColor(Color.BLACK);
                graphics2d.setFont(graphics2d.getFont().deriveFont(20F));
                graphics2d.drawString(message, gamePanel.tileSize * 7 - 12, gamePanel.tileSize  * 7);

                messageCounter++;
                if (messageCounter > gamePanel.FPS * 2) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }

    private void drawPauseScreen(Graphics2D graphics2d) {
        graphics2d.setColor(Color.WHITE);
        graphics2d.setFont(ariel_80B);
        String text = "PAUSED";
        int x = getXforCenteredText(text, graphics2d);
        int y = gamePanel.screenHeight / 2;
        graphics2d.drawString(text, x, y);
    }

    private void drawDialogueScreen(Graphics2D g2d) {

        // WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - gamePanel.tileSize * 4;
        int height = gamePanel.tileSize * 4;
        drawSubWindow(g2d, x, y, width, height);

        // DIALOGUE
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 28F));
        x += gamePanel.tileSize; // /2;
        y += gamePanel.tileSize; // /2;

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 24F));

        for (String line : currentDialogue.split("\n")) {
            g2d.drawString(line, x, y);
            y += g2d.getFontMetrics().getHeight();
        }
        int size = g2d.getFont().getSize();
        String name = g2d.getFont().getFontName() + " : " + size;
        g2d.drawString(name, x, y);
        //g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 28F));
/*        graphics2d.setColor(Color.WHITE);
        graphics2d.setFont(ariel_80B);
        String text = "DIALOGUE";
        int x = getXforCenteredText(text, graphics2d);
        int y = gamePanel.screenHeight / 2;
        graphics2d.drawString(text, x, y);*/
    }

    public void drawSubWindow(Graphics2D graphics2d, int x, int y, int width, int height) {

        Color windowBG = new Color(0, 0, 0, 220);
        graphics2d.setColor(windowBG);
        graphics2d.fillRoundRect(x, y, width, height, 35, 35);
        System.out.println("Drawing SubWindow: Color is: " + windowBG.toString());

        Color windowBorder = new Color(255, 255, 255, 255);
        graphics2d.setColor(windowBorder);
        graphics2d.setStroke(new BasicStroke(5));
        graphics2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        /*graphics2d.setColor(Color.BLACK);
        graphics2d.fillRect(x, y, width, height);
        graphics2d.setColor(Color.WHITE);
        graphics2d.drawRect(x, y, width, height);*/
    }

    private int getXforCenteredText(String text, Graphics2D graphics2d) {
        int textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
        return gamePanel.screenWidth / 2 - textLength / 2;
    }
}
