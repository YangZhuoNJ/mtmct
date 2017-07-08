package ex03.connector.http;

import ex03.connector.HttpConnector;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by admin on 2017/6/11.
 */
public class HttpProcessor {

    private final HttpConnector httpConnector;
    private HttpRequest request;
    private HttpResponse response;
    private HttpRequestLine requestLine = new HttpRequestLine();

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;

        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        request = new HttpRequest(input);
        response = new HttpResponse(output);
        response.setRequest(request);

        response.setHeader("Server", "Nginx");

        parseRequest(input, output);
//        parseHeaders(input);



    }

    private void parseRequest(SocketInputStream request, OutputStream output) {
    }

    private void parseHeaders(SocketInputStream input) throws IOException{

        input.readRequestLine(requestLine);


    }
}
