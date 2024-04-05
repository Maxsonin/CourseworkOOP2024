package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Loader {
    public static BufferedImage GetSprite(String fileName) {
        BufferedImage img = null;
        InputStream is = Loader.class.getResourceAsStream("/imgs/" + fileName);
        try {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                System.out.println("Resource not found: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }
}