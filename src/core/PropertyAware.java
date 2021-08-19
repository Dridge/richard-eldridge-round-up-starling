package core;

import properties.PropertiesManager;

public abstract class PropertyAware {
    private String authKeyValue = "";
    private String baseUrlValue = "";
    private String accountUriValue = "";

    protected PropertyAware() {
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

    protected String getAccountUidValue() {
        return accountUriValue;
    }
}
