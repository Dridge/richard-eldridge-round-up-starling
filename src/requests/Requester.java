package requests;

import core.PropertyAware;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

/**
 * Creates and sends requests returning the results to the caller
 */
public class Requester extends PropertyAware {
    private HttpResponse<String> response;

    public void sendRequest(String endPoint) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrlValue() + endPoint))
                .GET()
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthKeyValue())
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * A request with a list of parameters to be added to the request.
     *
     * Expecting parameters as String array, each value is added in order to the end point parameters.
     *
     * If no Strings provided essentially the same as {@link #sendRequest(String)}
     * @param endPoint
     * @param params
     */
    public void sendParameterisedRequest(String endPoint, String... params) {
        if(Arrays.asList(params).isEmpty()) {
            sendRequest(endPoint);
        }
        for(String parameter : params) {
            sendRequest(String.format(endPoint, parameter));
        }
    }

    public String getResponse() {
        return response.body();
    }
}
