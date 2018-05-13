import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 *
 * @author Natalie.
 *
 */
public class ImageParser {
    /**
     * The function creates an image file.
     * @param s a string.
     * @return an image file.
     */
    public Image imageFromString(String s) {
        String myImage = s.substring(0, s.length() - 1);
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(myImage);
        Image image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Unable to read image");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("Error closing the image file");
                }
            }
        }
        return image;
    }
}
