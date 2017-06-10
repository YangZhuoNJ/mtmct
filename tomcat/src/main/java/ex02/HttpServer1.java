package ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2017/6/10.
 */
public class HttpServer1 {


    public static void main(String[] args) {

        HttpServer1 httpServer1 = new HttpServer1();
        httpServer1.await();
    }

    private void await() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(Cons.PORT, 1, InetAddress.getByName(Cons.HOST));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        boolean shutdown = false;

        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = server.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                Request request = new Request(input, output);
                request.parse();

                Response response = new Response(output);
                response.setRequest(request);


                String uri = request.getUri();
                Processor processor = null;
                if (uri != null && Cons.SERVLET_HEAD.equals(uri)) {
                    processor = new ServletProcessor(request, response);
                } else {
                    processor = new StaticResourceProcessor(request, response);
                }
                processor.process();

                if (Cons.SHUTDOWN.equals(uri)) {
                    shutdown = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



        }

        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
