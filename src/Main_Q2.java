import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main_Q2 {
    public static void main(String[] args) throws IOException {
        // Charger l'image d'entrée
        BufferedImage inputImage = ImageIO.read(new File("ressources/copie.png"));

        // Obtenir la taille de l'image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Copier pixel par pixel de l'image d'entrée à l'image de sortie
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = inputImage.getRGB(x, y);
                outputImage.setRGB(x, y, rgb);
            }
        }

        // Sauvegarder l'image de sortie
        ImageIO.write(outputImage, "PNG", new File("copiemoi.png"));
    }
}
