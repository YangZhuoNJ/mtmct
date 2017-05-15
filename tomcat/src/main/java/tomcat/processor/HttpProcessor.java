package tomcat.processor;

import tomcat.connector.HttpConnector;

/**
 * Created by admin on 2017/5/15.
 */
public class HttpProcessor {

    private HttpConnector connector;
    public HttpProcessor(HttpConnector connector) {
        this.connector = connector;
    }

    public void process() {
    }
}
