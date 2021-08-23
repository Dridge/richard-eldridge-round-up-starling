package requests;

public class RequestFactory {
    public static IRequestCommand getRequestCommand(RequestType type) {
        return type.label.equals("put") ? new PutRequestCommand() : new GetRequestCommand();
    }
}
