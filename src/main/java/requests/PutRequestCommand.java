package requests;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates and sends requests returning the results to the caller
 */
class PutRequestCommand extends RequestCommand implements IRequestCommand {
    private static final Logger logger = Logger.getLogger(GetRequestCommand.class.getName());

    @Override
    public void sendRequest(String accountsEndPoint) {
        // Do nothing, this is a put request, we need something to put.
    }

    @Override
    public void sendParameterisedRequest(String enableRoundUpEndpoint, String accountUid) {
        // Do nothing, this is a put request, we need something to put.
    }

    @Override
    public void sendParameterisedRequest(String endpoint, String parameter, String body) {
        logger.log(Level.INFO, "Request body is:" + body);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrlValue() + String.format(endpoint, parameter)))
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthKeyValue())
                .build();
        send(request);
    }
}
