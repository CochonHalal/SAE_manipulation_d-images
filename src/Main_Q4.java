import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main_Q4 {
    public static void main(String[] args) throws IOException {
        // Charger l'image d'entrée
        BufferedImage inputImage = ImageIO.read(new File("ressources/copie.png"));

        // Obtenir la taille de l'image
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        // Extraire la composante rouge de l'image initiale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = inputImage.getRGB(x, y);
                //int redComponent = rgb & 0x00FF0000; // Garder seulement la composante rouge
                int greenComponent = rgb & 0x0000FF00; // Garder seulement la composante verte
                int blueComponent = rgb & 0x000000FF; // Garder seulement la composante bleue
                int newRGB = greenComponent | blueComponent; // Combinaison des composantes verte et bleue
                Utility.getRGBComponents(rgb);
                outputImage.setRGB(x, y, newRGB);
            }
        }

        // Sauvegarder l'image de sortie
        ImageIO.write(outputImage, "jpg", new File("copiemoi.png"));
    }
}
