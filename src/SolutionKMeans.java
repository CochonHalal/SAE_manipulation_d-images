import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SolutionKMeans {
    public static void main(String[] args) {
        String inputImagePath = "ressources/originale.jpg";
        String outputImagePath = "copiemoi_";

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
                centroides[i] = Utility.generateRandomColorSeed(1234 + i);
            }

            // Liste de couleurs
            ArrayList<ArrayList> groupes = new ArrayList();

            // Initialisation Groupes
            for (int j = 0; j < Integer.parseInt(args[0]); j++){
                groupes.add(j, new ArrayList<>());
            }

            boolean fini = false;
            int nb=0;

            // Boucle principale
            while(!fini){
                nb++;

                // Réinitialisation Groupes
                for (int j = 0; j < Integer.parseInt(args[0]); j++){
                    groupes.add(j, new ArrayList<>());
                }

                // Construction des Groupes
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int k = Utility.findClosestColorIndex(inputImage.getRGB(x, y), centroides);

                        // Ajoute le pixel de l'image au groupe de centroides le plus proche
                        groupes.get(k).add(inputImage.getRGB(x, y));

                        // Appliquer la couleur la plus proche dans l'image de sortie
                        outputImage.setRGB(x, y, centroides[k].getRGB());
                    }
                }
                // Sauvegarder l'image de sortie
                ImageIO.write(outputImage, "png", new File(outputImagePath+nb+".png"));

                // Nombre de centroides ayant convergé
                int countEnd = 0;

                // Mise à jour des centroïdes
                for(int z = 0; z < Integer.parseInt(args[0]); z++){
                    Color oldCentro = centroides[z];
                    centroides[z] = Utility.barycentre(groupes.get(z));
                    System.out.println("z : " + z);
                    if(oldCentro.getRGB() == centroides[z].getRGB()){
                        countEnd += 1;
                        System.out.println("countEnd : " + countEnd);
                    }
                    if(countEnd == Integer.parseInt(args[0])){
                        fini = true;
                    }
                }
            }


            // Print la distance de l'image par rapport à l'original
            long imageDistance = Distance.distance(inputImage, outputImage);
            System.out.println("distance : " + imageDistance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//6798079666