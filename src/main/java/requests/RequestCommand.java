package requests;

import properties.PropertyAware;
import properties.PropertyManager;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RequestCommand extends PropertyManager implements IRequestCommand {
    private static final Logger logger = Logger.getLogger(GetRequestCommand.class.getName());
    private HttpResponse response;

    public boolean responseContains(String text) {
        return String.valueOf(response.body()).contains(text);
    }

    public String getResponseBody() {
        return String.valueOf(response.body());
    }

    public int getResponseCode() {
        return response.statusCode();
    }

    protected void send(HttpRequest request) {
        HttpClient client = HttpClient.newHttpClient();
        logger.log(Level.INFO, "Request endpoint: " + request.uri());
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if(response.statusCode() != 200 && response.statusCode() != 202 && response.statusCode() != 204) {
            logger.log(Level.SEVERE, "Error during request send! Body is:\n" + String.valueOf(response.body()));
            throw new RuntimeException("Error during request send!");
        }
        logger.log(Level.INFO, "Response is : " + response.statusCode() + ", and body:\n" + response.body());
    }
}
