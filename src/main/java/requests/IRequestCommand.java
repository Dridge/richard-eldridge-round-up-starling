package requests;

public interface IRequestCommand {
    void sendRequest(String accountsEndPoint);

    void sendParameterisedRequest(String enableRoundUpEndpoint, String accountUid);

    void sendParameterisedRequest(String enableRoundUpEndpoint, String accountUid, String body);

    boolean responseContains(String text);

    String getResponseBody();

    int getResponseCode();
}
