import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Utility {
    public static int[] getRGBComponents(int rgb) {
        int blue = rgb & 0xff;
        int green = (rgb & 0xff00) >> 8;
        int red = (rgb & 0xff0000) >> 16;

        int[] rgbComponents = {red, green, blue};
        return rgbComponents;
    }

    public static int calculateGrayLevel(int red, int green, int blue) {
        return (red + green + blue) / 3;
    }

    // Méthode pour trouver l'index de la couleur la plus proche parmi un tableau de couleurs
    public static int findClosestColorIndex(int color, Color[] colors) {
        int closestIndex = 0;
        if (colors != null) {
            int closestDistance = Integer.MAX_VALUE;

            int red = (color >> 16) & 0xFF;
            int green = (color >> 8) & 0xFF;
            int blue = color & 0xFF;

            for (int i = 0; i < colors.length; i++) {
                Color c = colors[i];
                int targetRed = c.getRed();
                int targetGreen = c.getGreen();
                int targetBlue = c.getBlue();

                int distance = (red - targetRed) * (red - targetRed) +
                        (green - targetGreen) * (green - targetGreen) +
                        (blue - targetBlue) * (blue - targetBlue);

                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestIndex = i;
                }
            }
        }
        return closestIndex;
    }

    public static Color generateRandomColor() {
        int red = (int) (Math.random() * 256);    // Valeur aléatoire entre 0 et 255 pour la composante rouge
        int green = (int) (Math.random() * 256);  // Valeur aléatoire entre 0 et 255 pour la composante verte
        int blue = (int) (Math.random() * 256);   // Valeur aléatoire entre 0 et 255 pour la composante bleue

        return new Color(red, green, blue);
    }

    public static Color generateRandomColorSeed(int seed) {
        Random rand = new Random(seed);
        int red = (rand.nextInt(256));    // Valeur aléatoire entre 0 et 255 pour la composante rouge
        int green = (rand.nextInt(256));  // Valeur aléatoire entre 0 et 255 pour la composante verte
        int blue = (rand.nextInt(256));   // Valeur aléatoire entre 0 et 255 pour la composante bleue

        System.out.println(rand.nextInt(256));
        return new Color(red, green, blue);
    }

    public static Color barycentre(ArrayList<Integer> groupe) {
        int moyenne = 0;
        int[] rgbComponents;
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int elem : groupe) {
            rgbComponents = Utility.getRGBComponents(elem);
            red += rgbComponents[0];
            green += rgbComponents[1];
            blue += rgbComponents[2];
        }
        if (groupe.size() == 0) {
            throw new Error("centroide isole");
        }
        red = red / groupe.size();
        green = green / groupe.size();
        blue = blue / groupe.size();

        return new Color(red, green, blue);
    }
}