package ex03.connector.http;

/**
 * Created by admin on 2017/7/6.
 */
public class HttpRequest {


    private final SocketInputStream input;
    private String queryString;
    private String requestedSessionId;
    private boolean requestedSessionURL;

    public HttpRequest(SocketInputStream input) {
        this.input = input;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setRequestedSessionId(String requestedSessionId) {
        this.requestedSessionId = requestedSessionId;
    }

    public void setRequestedSessionURL(boolean requestedSessionURL) {
        this.requestedSessionURL = requestedSessionURL;
    }
}
