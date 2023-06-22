import java.awt.*;

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

        return closestIndex;
    }
}
