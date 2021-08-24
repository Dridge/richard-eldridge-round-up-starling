package properties;

import java.util.Optional;
import java.util.Properties;

public class PropertyManager {
    private static volatile Properties properties;
    private static final String authKey = "authKey";
    private static final String baseUrl = "baseUrl";
    protected static final String savingsGoalUidKey = "savingsGoalUid";

    protected PropertyManager() {
        readPropertiesFromConfigFile();
    }

    protected String getAuthProperty() {
        return String.valueOf(properties.get(authKey));
    }

    protected String getBaseUrlProperty() {
        return String.valueOf(properties.get(baseUrl));
    }

    protected String getSavingsGoalUidPropertyOrNull() {
        return (String) Optional.ofNullable(properties.get(savingsGoalUidKey)).orElse(null);
    }

    protected void setSavingsGoalUidProperty(String value) {
        addSavingsGoalUidProperty(value);
    }

    protected static void readPropertiesFromConfigFile() {
        PropertiesReader reader = new PropertiesReader();
        properties = reader.loadProperties();
    }

    private static void addSavingsGoalUidProperty(String value) {
        PropertiesWriter writer = new PropertiesWriter();
        writer.writeSavingsGoalUidToProperties(value);
        readPropertiesFromConfigFile();
    }
}
