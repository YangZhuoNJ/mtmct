package ex03.connector.http;

/**
 * Created by admin on 2017/7/6.
 */
public class HttpRequest {


    private final SocketInputStream input;

    public HttpRequest(SocketInputStream input) {
        this.input = input;
    }
}
