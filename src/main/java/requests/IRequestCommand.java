package requests;

public interface IRequestCommand {
    void sendRequest(String accountsEndPoint);

    void sendParameterisedRequest(String enableRoundUpEndpoint, String accountUid);

    void sendParameterisedRequest(String enableRoundUpEndpoint, String body, String... accountUid);

    boolean responseContains(String text);

    String getResponseBody();

    int getResponseCode();
}
