package ex03.connector;

import ex03.connector.http.HttpProcessor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2017/6/11.
 */
public class HttpConnector implements Runnable {

    private boolean stoped = false;

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {

        ServerSocket server = null;
        int port = 8080;
        try {
            server = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stoped) {
            Socket socket = null;
            try {
                socket = server.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Hand this socket to an HttpProcessor
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);

        }

    }


}
