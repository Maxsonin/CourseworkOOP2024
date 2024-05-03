//This class provides a static method that loads and returns a BufferedImage from a specified file path within the project's resources directory, handling resource loading and potential IOExceptions.

package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Loader {
    public static BufferedImage GetSprite(String filePath) {
        BufferedImage img = null;
        InputStream is = Loader.class.getResourceAsStream("/imgs/" + filePath);
        try {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                System.out.println("Resource not found: " + filePath);
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