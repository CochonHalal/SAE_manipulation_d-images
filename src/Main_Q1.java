import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.write;

public class Main_Q1 {
    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("ressources/copie.png"));
        write(image, "PNG", new File("copiemoi.png"));
    }
}
