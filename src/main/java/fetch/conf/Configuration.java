package fetch.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fetch.log.LogManager;
import fetch.log.Logger;

public class Configuration {

    public final static String CONFIGURATION_FILE = "fetch.properties";

    private Properties properties = new Properties();
    private static Configuration instance = new Configuration();
    private static Logger logger = LogManager.getLogger(Configuration.class);

    public String get(String key) {
        return get(key, null);
    }

    public String get(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public int get(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            String msg = "Configuration error: expected int value for key " + key
                    + ". Using default value " + defaultValue + " instead.";
            logger.warn(msg);
            return defaultValue;
        }
    }

    public static Configuration getInstance() {
        return instance;
    }

    private Configuration() {
        load();
    }

    private void load() {
        try {
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(CONFIGURATION_FILE);
            properties.load(is);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
