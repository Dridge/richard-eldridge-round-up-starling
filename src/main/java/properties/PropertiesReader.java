package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesReader {
    private static final Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    private static final String propertiesFile = "config.properties";

    Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            properties.load(in);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error whilst loading properties", e);
        }
        return properties;
    }
}
