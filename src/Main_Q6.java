import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main_Q6 {
    public static void main(String[] args) throws IOException {
        String[] imagePaths = {
                "originale.jpg",
                "coul_2.png",
                "coul_3.png",
                "coul_5.png",
                "coul_10.png",
                "coul_20.png",
                "copie.png",
                "copie_pixels.png",
                "copie_nb.png",
                "copie_rouge.png",
                "copie_vert_bleu.png",
                "copie_proche_YG.png",
                "copie_proche_YGW.png",
                "copie_proche_YGWO.png",
                "copie_proche_YGWOP.png"
        };

        BufferedImage originalImage = ImageIO.read(new File("ressources/" + imagePaths[0]));

        for (int i = 1; i < imagePaths.length; i++) {
            BufferedImage transformedImage = ImageIO.read(new File("ressources/" + imagePaths[i]));

            long distance = Distance.distance(originalImage, transformedImage);

            System.out.println("distance(" + imagePaths[0] + ", " + imagePaths[i] + ") = " + distance);
        }
    }
}