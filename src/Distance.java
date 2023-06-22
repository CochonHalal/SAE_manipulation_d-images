import java.awt.image.BufferedImage;

public class Distance {
    public static long distance(BufferedImage image1, BufferedImage image2) {
        int width = image1.getWidth();
        int height = image1.getHeight();

        long distance = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb1 = image1.getRGB(x, y);
                int rgb2 = image2.getRGB(x, y);

                int red1 = (rgb1 >> 16) & 0xFF;
                int green1 = (rgb1 >> 8) & 0xFF;
                int blue1 = rgb1 & 0xFF;

                int red2 = (rgb2 >> 16) & 0xFF;
                int green2 = (rgb2 >> 8) & 0xFF;
                int blue2 = rgb2 & 0xFF;

                long diffRed = red1 - red2;
                long diffGreen = green1 - green2;
                long diffBlue = blue1 - blue2;

                distance += (diffRed * diffRed) + (diffGreen * diffGreen) + (diffBlue * diffBlue);
            }
        }

        return distance;
    }
}
