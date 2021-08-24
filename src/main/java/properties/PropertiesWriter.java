package properties;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesWriter extends PropertyManager {
    private static final Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    private static final String propertiesFile = "config.properties";

    public void writeSavingsGoalUidToProperties(String value) {
        readPropertiesFromConfigFile();
        if (!value.isEmpty() && getSavingsGoalUidPropertyOrNull() == null) {
            StringBuilder propertyString = new StringBuilder();
            propertyString.append("\n").append(savingsGoalUidKey).append("=").append(value);
            try {
                Files.write(
                        Paths.get(propertiesFile),
                        propertyString.toString().getBytes(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                logger.log(Level.WARNING, "Unable to store new property, " +
                        "will result in more requests sent. " +
                        "Error is:\n" + e.getMessage());
            }
        }
    }
}
