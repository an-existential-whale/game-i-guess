package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends SuperObject {
    public Heart(GamePanel gp) {
        name = "Heart";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_full.png/")));
            image = ut.scaleImage(image, gp.tileSize, gp.tileSize);
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_half.png/")));
            image2 = ut.scaleImage(image2, gp.tileSize, gp.tileSize);
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heart_blank.png/")));
            image3 = ut.scaleImage(image3, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }

}
