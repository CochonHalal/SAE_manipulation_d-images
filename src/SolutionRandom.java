import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class SolutionRandom {
    public static void main(String[] args) {
        Instant start = Instant.now();

        String inputImagePath = "ressources/coul_10.png";
        String outputImagePath = "copiemoi_";

        try {
            // Charger l'image d'entrée
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // Obtenir la taille de l'image
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            //image avec les couleurs les plus proches de l'original
            BufferedImage bestImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            //distance de la meilleure image par rapport à l'original
            long bestImageDistance = -1;

            //tableau de couleurs aléatoires
            Color[] colors = new Color[Integer.parseInt(args[0])];

            int nbIte = 0;

            // Boucle effectuant i "lancés" pour trouver les couleurs les plus proches
            for(int i = 0; i < Integer.parseInt(args[1]); i++) {
                nbIte++;

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
                }

                System.out.println("itération : " + nbIte);

                // Sauvegarder l'image de sortie
                ImageIO.write(outputImage, "png", new File(outputImagePath+nbIte+".png"));

                // Calculer la distance de l'image
                long imageDistance = Distance.distance(inputImage, outputImage);

                // Cas ou la best image n'a pas été initialisée
                if(bestImageDistance == -1){
                    bestImageDistance = imageDistance;

                    // Copie le tableau de pixels de l'inputImage dans la bestImage
                    int[] pixels = outputImage.getRGB(0, 0, width, height, null, 0, width);
                    bestImage.setRGB(0, 0, width, height, pixels, 0, width);
                }
                else{
                    if(imageDistance < bestImageDistance){
                        bestImageDistance = imageDistance;

                        // Copie le tableau de pixels de l'inputImage dans la bestImage
                        int[] pixels = outputImage.getRGB(0, 0, width, height, null, 0, width);
                        bestImage.setRGB(0, 0, width, height, pixels, 0, width);
                    }
                }
            }
            // Print la distance de la meilleure image
            System.out.println("Distance SolutionRandom : " + bestImageDistance);

            // Sauvegarder l'image de sortie
            ImageIO.write(bestImage, "png", new File(outputImagePath + "Finale.png"));

            Instant end = Instant.now();
            Duration elapsedTime = Duration.between(start, end);

            System.out.println("Temps de calcul SolutionRandom : " + elapsedTime.toMillis() + " millisecondes");
            System.out.println("Nombre d'itérations : " + nbIte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}