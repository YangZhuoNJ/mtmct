package tomcat;

import tomcat.connector.HttpConnector;

/**
 * Created by admin on 2017/5/15.
 */
public class BootStrap {

    public static void main(String[] args) {

        HttpConnector connector = new HttpConnector();
        connector.start();

    }
}
