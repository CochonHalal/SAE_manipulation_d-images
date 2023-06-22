import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SolutionRandom {
    public static void main(String[] args) {
        String inputImagePath = "ressources/coul_10.png";
        String outputImagePath = "copiemoi.png";

        try {
            // Charger l'image d'entrée
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // Obtenir la taille de l'image
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            //image avec les couleurs les plus proches de l'original
            BufferedImage bestImage = null;

            //distance de la meilleure image par rapport à l'original
            long bestImageDistance = -1;

            //tableau de couleurs aléatoires
            Color[] colors = new Color[Integer.parseInt(args[0])];

            // Boucle effectuant 100 "lancés" pour trouver les couleurs les plus proches
            for(int i = 0; i < 100; i++) {
                // Prendre des couleurs aléatoires en fonction du nb de couleurs choisies
                for (int j = 0; j < Integer.parseInt(args[0]); j++) {
                    colors[j] = Utility.generateRandomColor();
                }

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
                    System.out.println("y : " + y);
                }

                // Calculer la distance de l'image
                long imageDistance = Distance.distance(inputImage, outputImage);
                if(bestImageDistance != -1){
                    if(imageDistance < bestImageDistance){
                        bestImageDistance = imageDistance;
                        bestImage = outputImage;
                    }
                }
                else{
                    bestImageDistance = imageDistance;
                    bestImage = outputImage;
                }
            }

            // Sauvegarder l'image de sortie
            ImageIO.write(bestImage, "png", new File(outputImagePath));
            // Print la distance de la meilleure image
            System.out.println("distance : " + bestImageDistance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}