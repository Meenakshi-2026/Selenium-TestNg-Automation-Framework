package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties prop = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                prop.load(in);
            }
        } catch (Exception e) {
            // ignore - fallback to defaults where used
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
