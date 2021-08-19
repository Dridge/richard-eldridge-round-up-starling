import properties.PropertiesManager;

public abstract class PropertyAware {
    private String authKeyValue = "";
    private String baseUrlValue = "";
    private String accountUriValue = "";

    PropertyAware() {
        authKeyValue = PropertiesManager.getAuthKeyValue();
        baseUrlValue = PropertiesManager.getBaseUrlValue();
        accountUriValue = PropertiesManager.getAccountUidValue();
    }

    protected String getAuthKeyValue() {
        return authKeyValue;
    }

    protected String getBaseUrlValue() {
        return baseUrlValue;
    }
}
