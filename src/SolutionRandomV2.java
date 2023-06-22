import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SolutionRandomV2 {
    public static void main(String[] args) {
        String inputImagePath = "ressources/coul_10.png";
        String outputImagePath = "copiemoi.png";
        final int NBLANCE = 100;

        try {
            // Charger l'image d'entrée
            BufferedImage inputImage = ImageIO.read(new File(inputImagePath));

            // Obtenir la taille de l'image
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            // Créer une nouvelle image avec la même taille et le même type que l'image d'entrée
            BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

            //tableau du score de chaque couleur de l'itération précédente du tableau randomColors
            long[] scoreRand = new long[Integer.parseInt(args[0])];

            //tableau de score du tableau colors
            long[] score = new long[Integer.parseInt(args[0])];

            //tableau contenant le nombre de fois ou une couleur est utilisé dans l'image
            long[] nbUtil = new long[Integer.parseInt(args[0])];

            for (int i = 0; i < score.length; i++) {
                score[i] = 0;
                nbUtil[i] = 0;
            }

            //tableau de couleurs
            Color[] colors = new Color[Integer.parseInt(args[0])];
            for (int j = 0; j < Integer.parseInt(args[0]); j++) {
                colors[j] = Utility.generateRandomColor();
            }

            //tableau de couleurs aléatoires
            Color[] randomColors = new Color[Integer.parseInt(args[0])];

            // Boucle effectuant plusieurs "lancés" pour trouver les couleurs les plus proches
            for(int i = 0; i < NBLANCE; i++) {
                // Prendre des couleurs aléatoires en fonction du nb de couleurs choisies
                for (int j = 0; j < Integer.parseInt(args[0]); j++) {
                    randomColors[j] = Utility.generateRandomColor();
                }

                // Parcourir chaque pixel de l'image
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {

                        int color = inputImage.getRGB(x, y);

                        if(i == Integer.parseInt(args[0]) - 1) {
                            // Trouver la couleur la plus proche parmi les couleurs données
                            int closestColorIndex = Utility.findClosestColorIndex(color, colors);
                            Color closestColor = colors[closestColorIndex];

                            // Appliquer la couleur la plus proche dans l'image de sortie
                            outputImage.setRGB(x, y, closestColor.getRGB());
                        }
                        else {
                            // Trouver la couleur la plus proche parmi les couleurs données
                            int closestColorIndex = Utility.findClosestColorIndex(color, randomColors);
                            Color closestColor = randomColors[closestColorIndex];

                            // Utilisation +1
                            nbUtil[closestColorIndex] += 1;

                            // Ajouter le score de la couleur
                            scoreRand[closestColorIndex] = scoreRand[closestColorIndex] + Distance.distancePixel(inputImage, x, y, closestColor.getRGB());
                        }
                    }
                    System.out.println("y : " + y);
                }
                // Trouver le score le plus bas
                long scoreFin;
                ArrayList index = new ArrayList<>();

                //parcours du tableau de couleurs aléatoires et comparaison à celui de couleurs finales pour obtenir
                //les couleurs avec le meilleur score
                for(int v = 0; v < Integer.parseInt(args[0]); v++){
                    for(int w = 0; w < Integer.parseInt(args[0]); w++) {
                        if (nbUtil[w] != 0) {
                            scoreFin = scoreRand[w] / nbUtil[w];
                            if (score[v] > scoreFin && !index.contains(w)) {
                                index.add(w);
                                score[v] = scoreFin;
                                colors[v] = randomColors[w];
                            }
                        }
                    }
                }
            }

            // Sauvegarder l'image de sortie
            ImageIO.write(outputImage, "png", new File(outputImagePath));
            // Print la distance de la meilleure image
            long imageDistance = Distance.distance(inputImage, outputImage);
            System.out.println("distance : " + imageDistance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//Ramelon