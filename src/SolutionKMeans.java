import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class SolutionKMeans {
    public static void main(String[] args) {
        Instant start = Instant.now();

        String inputImagePath = "ressources/coul_10.png";
        String outputImagePath = "copiemoi_";
        int seed = -1;
        if(args.length > 1) {
            seed = Integer.parseInt(args[1]);
        }

        try {
            // Charger l'image d'entrée
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // Obtenir la taille de l'image
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            Color[] centroides = new Color[Integer.parseInt(args[0])];

            Random rand = new Random();
            if(seed != -1){
                rand = new Random(seed);
            }

            // Initialisation centroïdes à partir des données
            for (int i = 0; i < Integer.parseInt(args[0]); i++) {
                int x;
                int y;
                x = rand.nextInt(inputImage.getWidth()-1);
                y = rand.nextInt(inputImage.getHeight()-1);
                centroides[i] = new Color(inputImage.getRGB(x, y));
            }

            // Liste de couleurs
            ArrayList<ArrayList> groupes = new ArrayList();

            // Initialisation Groupes
            for (int j = 0; j < Integer.parseInt(args[0]); j++){
                groupes.add(j, new ArrayList<>());
            }

            boolean fini = false;
            int nbIte=0;

            // Boucle principale
            while(!fini){
                nbIte++;

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
                ImageIO.write(outputImage, "png", new File(outputImagePath+nbIte+".png"));

                // Nombre de centroides ayant convergé
                int countEnd = 0;

                // Mise à jour des centroïdes
                for(int z = 0; z < Integer.parseInt(args[0]); z++){
                    Color oldCentro = centroides[z];
                    centroides[z] = Utility.barycentre(groupes.get(z));
                    if(oldCentro.getRGB() == centroides[z].getRGB()){
                        countEnd += 1;
                    }
                    if(countEnd == Integer.parseInt(args[0])){
                        fini = true;
                    }
                }
                System.out.println("nb centroïdes convergés : " + countEnd + " à l'itération : " + nbIte);
            }


            // Print la distance de l'image par rapport à l'original
            long imageDistance = Distance.distance(inputImage, outputImage);
            System.out.println("Distance SolutionKMeans: " + imageDistance);

            Instant end = Instant.now();
            Duration elapsedTime = Duration.between(start, end);

            System.out.println("Temps de calcul SolutionKMeans : " + elapsedTime.toMillis() + " millisecondes");
            System.out.println("Nombre d'itérations : " + nbIte);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}