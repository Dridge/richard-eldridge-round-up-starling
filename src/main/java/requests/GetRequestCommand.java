package requests;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * Creates and sends requests returning the results to the caller
 */
class GetRequestCommand extends RequestCommand implements IRequestCommand {
    @Override
    public void sendRequest(String endpoint) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrlValue() + endpoint))
                .GET()
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthKeyValue())
                .build();
        send(request);
    }

    @Override
    public void sendParameterisedRequest(String endpoint, String accountUid) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrlValue() + endpoint))
                .GET()
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthKeyValue())
                .build();
        send(request);
    }

    @Override
    public void sendParameterisedRequest(String endpoint, String accountUid, String body) {
        // Ignore body, this is a get request
        sendRequest(String.format(endpoint, accountUid));
    }
}
