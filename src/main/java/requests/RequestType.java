package requests;

public enum RequestType {
    PUT("put"),
    GET("get");

    public final String label;

    RequestType(String label) {
        this.label = label;
    }
}
