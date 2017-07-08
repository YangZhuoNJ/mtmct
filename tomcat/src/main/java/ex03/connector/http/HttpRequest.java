package ex03.connector.http;

/**
 * Created by admin on 2017/7/6.
 */
public class HttpRequest {


    private final SocketInputStream input;
    private String queryString;
    private String requestedSessionId;
    private boolean requestedSessionURL;
    private String method;
    private String protocol;
    private String uri;

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

    public void setMethod(String method) {
        this.method = method;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
