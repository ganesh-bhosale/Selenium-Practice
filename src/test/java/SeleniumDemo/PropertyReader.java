package SeleniumDemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static void main(String [] args) throws IOException {
        FileReader fileReader = new FileReader("src/test/resource/config.properties");

        Properties p = new Properties();
        p.load(fileReader);

        System.out.println(p.getProperty("url"));
        System.out.println(p.get("username"));
        System.out.println(p.getProperty("server"));
    }
}
