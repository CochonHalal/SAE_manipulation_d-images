import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main_Q3 {
    public static void main(String[] args) throws IOException {
        // Charger l'image d'entrée
        BufferedImage inputImage = ImageIO.read(new File("ressources/copie.png"));

        // Obtenir la taille de l'image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Convertir l'image en noir et blanc
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = inputImage.getRGB(x, y);
                int[] rgbComponents = Utility.getRGBComponents(rgb);
                int grayLevel = Utility.calculateGrayLevel(rgbComponents[0], rgbComponents[1], rgbComponents[2]);
                Color grayColor = new Color(grayLevel, grayLevel, grayLevel);
                int grayRGB = grayColor.getRGB();
                outputImage.setRGB(x, y, grayRGB);
            }
        }

        // Sauvegarder l'image de sortie
        ImageIO.write(outputImage, "PNG", new File("copiemoi.png"));
    }
}
