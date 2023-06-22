import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SolutionKMeans {
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

            Color[] centroides = new Color[Integer.parseInt(args[0])];

            // Initialisation centroïdes
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                centroides[i] = Utility.generateRandomColorSeed(198 + i);
            }

            // Liste de couleurs
            ArrayList<ArrayList> groupes = new ArrayList();

            // Initialisation Groupes
            for (int j = 0; j < Integer.parseInt(args[0]); j++){
                groupes.add(j, new ArrayList<>());
            }

            boolean fini = false;

            // Boucle principale
            while(!fini){

                // Initialisation Groupes
                for (int j = 0; j < Integer.parseInt(args[0]); j++){
                    groupes.add(j, new ArrayList<>());
                }

                // Construction des Groupes
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int k = Utility.findClosestColorIndex(inputImage.getRGB(x, y), centroides);
                        // Ajoute le pixel de l'image au groupe du centroides le plus proche
                        groupes.get(k).add(inputImage.getRGB(x, y));
                    }
                }

                // Mise à jour des centroïdes
                for(int z = 0; z < Integer.parseInt(args[0]); z++){
                    Utility.barycentre(groupes.get(z));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}