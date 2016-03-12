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
    private static Logger logger;

    static {
        logger = LogManager.getLogger(Configuration.class);
    }

    public String get(String key) {
        return get(key, null);
    }

    private String get(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
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
            if (logger != null) {
                logger.info(e);
            } else {
                e.printStackTrace();
            }
        }
    }
}
