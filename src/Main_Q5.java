import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main_Q5 {
    public static void main(String[] args) {
        String inputImagePath = "ressources/copie.png";
        String outputImagePath = "copiemoi.png";

        try {
            // Charger l'image d'entrée
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // Obtenir la taille de l'image
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            // Définir les couleurs à comparer
            Color[] colors = new Color[5];
            colors[0] = Color.YELLOW;
            colors[1] = Color.GREEN;
            colors[2] = Color.WHITE;
            colors[3] = Color.ORANGE;
            colors[4] = Color.PINK;

            // Parcourir chaque pixel de l'image
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int color = inputImage.getRGB(x, y);

                    // Trouver la couleur la plus proche parmi les couleurs données
                    int closestColorIndex = Utility.findClosestColorIndex(color, colors);
                    Color closestColor = colors[closestColorIndex];

                    // Appliquer la couleur la plus proche dans l'image de sortie
                    outputImage.setRGB(x, y, closestColor.getRGB());
                }
            }

            // Sauvegarder l'image de sortie
            ImageIO.write(outputImage, "png", new File(outputImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
