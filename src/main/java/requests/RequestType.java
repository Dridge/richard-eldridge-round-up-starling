package requests;

public enum RequestType {
    PUT("put"),
    GET("get");

    public final String label;

    private RequestType(String label) {
        this.label = label;
    }
}
