package Task3;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.nitido.utils.toaster.Toaster;

public class JToaster {

    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("");

        BufferedImage image = ImageIO.read(file);

        Toaster toaster = new Toaster();
        toaster.setToasterHeight(image.getHeight());
        toaster.setToasterWidth(image.getWidth());
        toaster.setBackgroundImage(image);
        toaster.showToaster("Get up U");
        Thread.sleep(5000);

    }

}
