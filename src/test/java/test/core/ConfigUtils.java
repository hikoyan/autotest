package test.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by yan on 16/4/28.
 */
public class ConfigUtils {
    public static Properties getProperties(String config) throws IOException {
        Properties properties = new Properties();
        FileInputStream inStream = new FileInputStream(new File(config));
        try {
            properties.load(inStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }
}
