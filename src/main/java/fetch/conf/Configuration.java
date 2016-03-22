package fetch.conf;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import fetch.exception.FetchRuntimeException;
import fetch.log.LogManager;
import fetch.log.Logger;
import fetch.message.Messages;

public class Configuration {

    public final static String CONFIGURATION_FILE = "fetch.json";

    private static Configuration instance = new Configuration();
    private static Logger logger;

    ConfigurationMap map = new ConfigurationMap();

    public static Configuration getInstance() {
        return instance;
    }

    public ConfigurationMap getMap() {
        return map;
    }

    private Configuration() {
        load();
    }

    private void load() {
        Configuration.logger = LogManager.getLogger(Configuration.class);
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(CONFIGURATION_FILE);
            if (is != null) {
                map = mapper.readValue(is, ConfigurationMap.class);
            } else {
                logger.info("cf.file.not.found", CONFIGURATION_FILE);
            }
        } catch (IOException e) {
            Messages messages = new Messages();
            String msg = messages.get("cf.fail.read", CONFIGURATION_FILE);
            logger.error(e, msg);
            throw new FetchRuntimeException(msg, e);
        }
    }
}
