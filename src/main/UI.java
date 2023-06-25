package main;

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

    DecimalFormat df = new DecimalFormat("#0.00");
    double playTime;

    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
            //to do later
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}
