package core;

import org.mockito.Mockito;
import requests.IRequestCommand;
import requests.RequestFactory;

public abstract class RequesterFactoryTest {
    IRequestCommand requester = Mockito.mock(IRequestCommand.class);
    RequestFactory factory = Mockito.mock(RequestFactory.class);
}
