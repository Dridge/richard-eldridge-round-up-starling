package requests;

import core.PropertyAware;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Creates and sends requests returning the results to the caller
 */
public class Requester extends PropertyAware {
    private static final Logger logger = Logger.getLogger(Requester.class.getName());
    private HttpResponse<String> response;

    public void sendGetRequest(String endPoint) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrlValue() + endPoint))
                .GET()
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthKeyValue())
                .build();
        send(client, request);
    }

    public void sendParameterisedPutRequest(String endPoint, String parameter, String bodyAsString) {
        logger.log(Level.INFO, "Request body is:" + bodyAsString);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getBaseUrlValue() + String.format(endPoint, parameter)))
                .PUT(HttpRequest.BodyPublishers.ofString(bodyAsString))
                .setHeader("Content-Type","application/json")
                .setHeader("Authorization", "Bearer " + getAuthKeyValue())
                .build();
        send(client, request);
    }

    /**
     * A request with a parameter to be added to the request.
     *
     * @param endPoint
     * @param parameter
     */
    public void sendParameterisedGetRequest(String endPoint, String parameter) {
        sendGetRequest(String.format(endPoint, parameter));
    }

    private String getResponse() {
        return response.body();
    }

    private void send(HttpClient client, HttpRequest request) {
        logger.log(Level.INFO, "Next request: " + request.uri());
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "Response is : " + getResponse());
    }

    public boolean responseContains(String text) {
        return response.body().contains(text);
    }
}
