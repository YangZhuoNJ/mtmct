package ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2017/6/7.
 */
public class HttpServer {


    public static final String SHUTDOWN = "/shutdown";

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    private void await() {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(Cons.PORT, 1, InetAddress.getByName(Cons.HOST));
        } catch (Exception e) {
            System.exit(0);
        }

        boolean shutdown = false;
        while (!shutdown) {
            try {
                Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();

                Request request = new Request(input, output);
                request.parse();

                Response response = new Response(input, output);
                response.setRequest(request);

                response.sendStaticResource();
                socket.close();

                if (SHUTDOWN.equals(request.getUri())) {
                    shutdown = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}
