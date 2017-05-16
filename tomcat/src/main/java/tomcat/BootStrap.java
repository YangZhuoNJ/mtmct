package tomcat;

import tomcat.connector.HttpConnector;

/**
 * start a Servlet container, cycle to accept http request
 *
 * Created by admin on 2017/5/15.
 */
public class BootStrap {

    public static void main(String[] args) {

        HttpConnector connector = new HttpConnector();
        connector.start();

    }
}
