package properties;

import java.util.Properties;

public class PropertiesManager {
    private static volatile Properties properties;
    private static final String authKey = "authKey";
    private static final String baseUrl = "baseUrl";
    private static final String accountUidKey = "accountHolderUid";

    public static String getAccountUidValue() {
        return getProperty(accountUidKey);
    }

    public static String getAuthKeyValue() {
        return getProperty(authKey);
    }

    public static String getBaseUrlValue() {
        return getProperty(baseUrl);
    }

    /**
     * Gets a given property from the properties file, loads the current file every time
     * TODO cache the file? or maybe make this a stateful singleton?
     *
     * @param propertyKey
     * @return property value
     */
    public static String getProperty(String propertyKey) {
        setProperties();
        return String.valueOf(properties.get(propertyKey));
    }

    private static void setProperties() {
        PropertiesReader reader = new PropertiesReader();
        properties = reader.loadProperties();
    }
}
