import java.awt.image.BufferedImage;

public class Distance {
    public static long distance(BufferedImage image1, BufferedImage image2) {
        int width = image1.getWidth();
        int height = image1.getHeight();

        long distance = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                distance += distancePixel(image1, x, y, image2.getRGB(x, y));
            }
        }

        return distance;
    }

    // Donne un score pour le pixel en fonction de la distance au pixel de l'image
    public static long distancePixel(BufferedImage original, int x, int y, int rgb2){
        int rgb1 = original.getRGB(x, y);

        int red1 = (rgb1 >> 16) & 0xFF;
        int green1 = (rgb1 >> 8) & 0xFF;
        int blue1 = rgb1 & 0xFF;

        int red2 = (rgb2 >> 16) & 0xFF;
        int green2 = (rgb2 >> 8) & 0xFF;
        int blue2 = rgb2 & 0xFF;

        long diffRed = red1 - red2;
        long diffGreen = green1 - green2;
        long diffBlue = blue1 - blue2;

        return (diffRed * diffRed) + (diffGreen * diffGreen) + (diffBlue * diffBlue);
    }
}
