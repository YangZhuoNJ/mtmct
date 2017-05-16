package tomcat.processor;

import org.apache.catalina.connector.http.HttpRequestLine;
import org.apache.catalina.connector.http.SocketInputStream;
import tomcat.base.HttpRequest;
import tomcat.base.HttpResponse;
import tomcat.connector.HttpConnector;

import java.io.IOException;
import java.io.OutputStream;
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
        SocketInputStream sis = null;
        OutputStream os = null;
        try {
            sis = new SocketInputStream(socket.getInputStream(), 2048);
            os = socket.getOutputStream();


            //create Request Object
            HttpRequest request = new HttpRequest(sis);

            //create Response Object
            HttpResponse response = new HttpResponse(os);
            response.setRequest(request);

            response.setHeader("Server", "NJ's Servlet Container");

            parseRequest(sis, os);
            parseHeader(sis);


            Processor processor = null;

            //check if this request for servlet or a static source
            //if a request for servlet begin with "/servlet"
            if (request.getRequestURI().startsWith("/servlet")) {
                processor = new ServletProcessor();
            } else {
                processor = new StaticSourceProcessor();
            }

            processor.process(request, response);

            //close the socket release source
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseHeader(SocketInputStream sis) {

    }

    private void parseRequest(SocketInputStream sis, OutputStream os) {

    }
}
