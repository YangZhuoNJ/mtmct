package ex03.connector.http;

/**
 * Created by admin on 2017/7/6.
 */
public class HttpRequest {


    private final SocketInputStream input;
    private String queryString;

    public HttpRequest(SocketInputStream input) {
        this.input = input;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
}
