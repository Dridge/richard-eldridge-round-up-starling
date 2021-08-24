package requests;

public interface IRequestCommand {
    void sendRequest(String endpoint);

    void sendRequest(String endpoint, String body);

    boolean responseContains(String text);

    String getResponseBody();

    int getResponseCode();
}
