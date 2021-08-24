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
                .uri(URI.create(getBaseUrlProperty() + endpoint))
                .GET()
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthProperty())
                .build();
        send(request);
    }

    @Override
    public void sendRequest(String endpoint, String body) {
        //do nothing
    }

}
