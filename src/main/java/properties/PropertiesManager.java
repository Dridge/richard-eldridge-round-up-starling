package properties;

import java.util.Properties;

public class PropertiesManager {
    private static volatile Properties properties;
    private static final String authKey = "authKey";
    private static final String baseUrl = "baseUrl";
    private static final String accountUidKey = "accountHolderUid";
    private static final String savingsGoalUidKey = "savingsGoalUid";

    protected PropertiesManager() {
        populateProperties();
    }

    protected String getAuthProperty() {
        return String.valueOf(properties.get(authKey));
    }

    protected String getBaseUrlProperty() {
        return String.valueOf(properties.get(baseUrl));
    }

    protected String getAccountUidProperty() {
        return String.valueOf(properties.get(accountUidKey));
    }

    protected String getSavingsGoalUidProperty() {
        return String.valueOf(properties.get(savingsGoalUidKey));
    }

    protected void setSavingsGoalUidProperty(String value) {
        addProperty(savingsGoalUidKey, value);
    }

    private static void populateProperties() {
        PropertiesReader reader = new PropertiesReader();
        properties = reader.loadProperties();
    }

    private static void addProperty(String key, String value) {
        PropertiesWriter writer = new PropertiesWriter();
        writer.writeProperties(key, value);
        populateProperties();
    }
}
