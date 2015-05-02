package Task4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Download {

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://d3dsacqprgcsqh.cloudfront.net/photo/aozrdx0_700b.jpg");
        InputStream is = url.openStream();
        File file = new File("C:\\Users\\Ahmed\\Desktop\\image.jpg");
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream os = new FileOutputStream(file);
        byte[] b = new byte[4096];
        int length = 0;
        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

    }

}
