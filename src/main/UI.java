package main;

import object.Heart;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font font = new Font("Arial", Font.PLAIN, 40);
    Font bolder = new Font("Arial", Font.BOLD, 80);

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue;

    public int commandNum = 0;

    BufferedImage heart_full, heart_half, heart_empty;

    DecimalFormat df = new DecimalFormat("#0.00");

    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        Heart heart = new Heart(gp);

        heart_full = heart.image;
        heart_half = heart.image2;
        heart_empty = heart.image3;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            drawPlayerLife();
        } else if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            drawPlayerLife();
        } else if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72F));
        String text = "Blue Boy Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.gray);
        g2.drawString(text, x + 2, y + 2);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize * 3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_empty, x, y, null);
            x += gp.tileSize;
            i++;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if ( i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            x += gp.tileSize;
            i++;
        }

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public void drawDialogueScreen() {

        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color black = new Color(0, 0, 0, 200);
        g2.setColor(black);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        Color white = new Color(255, 255, 255);
        g2.setColor(white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}
