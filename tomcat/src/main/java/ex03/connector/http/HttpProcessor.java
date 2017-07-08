package ex03.connector.http;

import ex03.connector.HttpConnector;

import javax.servlet.ServletException;
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

    private void parseHeaders(SocketInputStream input) throws IOException, ServletException {

        input.readRequestLine(requestLine);
        String method = new String(requestLine.method, 0, requestLine.methodEnd);
        String uri = null;
        String portocol = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        //validate the incoming request
        if (method.length() < 1) {
            throw new ServletException("Missing Http Request method");
        } else {
            if (requestLine.uriEnd < 1) {
                throw new ServletException("Missing Http Request URI");
            }
        }

        //parse any query questions out of the request uri
        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }

        //checking for a absolute URI
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            if (pos == -1) {
                pos = uri.indexOf("/");
                if (pos == -1) {
                    uri = "";
                }else {
                    uri = uri.substring(pos);
                }
            }
        }

        //parse any requested session ID out of the request uri
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        if (semicolon >= 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(";");
            if (semicolon2 >= 0) {
                String sessionId = rest.substring(0, semicolon2);
                request.setRequestedSessionId(sessionId);
                rest = rest.substring(semicolon2);
            } else {
                request.setRequestedSessionId(rest);
                rest = "";
            }
            request.setRequestedSessionURL(true);
            uri = uri.substring(0, semicolon) + rest;
        }else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }
    }
}
