package main;

import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font font = new Font("Arial", Font.PLAIN, 40);
    Font bolder = new Font("Arial", Font.BOLD, 80);

    Key key = new Key(gp);
    BufferedImage keyImage = key.image;

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

        if (gameFinished) {

            g2.setFont(font);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You won!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - (gp.tileSize * 3);

            text = "Your time is: " + df.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 4);


            g2.drawString(text, x, y);

            g2.setFont(bolder);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 2);

            g2.drawString(text, x, y);

            //STOP THE GAME
            gp.gameThread = null;

        } else {

            g2.setFont(font);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.keys, 74, 65);

            playTime += (double) 1/60;
            g2.drawString("Time: " + df.format(playTime), gp.tileSize * 11 - 5, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
}
