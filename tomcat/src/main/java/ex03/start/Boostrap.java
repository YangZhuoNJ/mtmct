package ex03.start;


import ex03.connector.HttpConnector;

/**
 * Created by admin on 2017/6/11.
 */
public class Boostrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
