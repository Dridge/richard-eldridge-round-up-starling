package core;

import properties.PropertiesManager;

public abstract class PropertyAware {
    private String authKeyValue = "";
    private String baseUrlValue = "";

    protected PropertyAware() {
        authKeyValue = PropertiesManager.getAuthKeyValue();
        baseUrlValue = PropertiesManager.getBaseUrlValue();
    }

    protected String getAuthKeyValue() {
        return authKeyValue;
    }

    protected String getBaseUrlValue() {
        return baseUrlValue;
    }
}
