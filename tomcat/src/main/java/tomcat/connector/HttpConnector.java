package tomcat.connector;

import tomcat.processor.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2017/5/15.
 */
public class HttpConnector implements Runnable {

    private boolean stopped = false;

    private String schema = "http";

    public String getSchema() {
        return schema;
    }

    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;

        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            HttpProcessor httpProcessor = new HttpProcessor(this);
            httpProcessor.process();
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }


}
