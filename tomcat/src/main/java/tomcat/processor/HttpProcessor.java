package tomcat.processor;

import org.apache.catalina.connector.http.HttpRequestLine;
import tomcat.connector.HttpConnector;

import java.net.Socket;

/**
 * Created by admin on 2017/5/15.
 */
public class HttpProcessor {

    private HttpConnector connector;

    private HttpRequestLine requestLine = new HttpRequestLine();

    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process(Socket socket) {

    }
}
