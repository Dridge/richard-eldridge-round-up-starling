package requests;

public class RequestFactory {
    public IRequestCommand getRequestCommand(RequestType type) {
        return type.label.equals("put") ? new PutRequestCommand() : new GetRequestCommand();
    }
}
