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
                } else {
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
        } else {
            request.setRequestedSessionId(null);
            request.setRequestedSessionURL(false);
        }

        //TODO　normalize
        String normalizeUri = normalize(uri);

        request.setMethod(method);
        request.setProtocol(portocol);
        request.setUri(normalizeUri == null ? uri : normalizeUri);

        if (normalizeUri == null) {
            throw new ServletException("Invalid URI: " + uri );
        }
    }

    private String normalize(String path) {
        if (path == null) {
            return null;
        }

        String normalized = path;

        //normalize the "/%7E" and "/%7e" at the beginning to "/~"
        if (normalized.startsWith("/%7E") || normalized.startsWith("/%7e")) {
            normalized = "/~" + normalized.substring(4);
        }

        // Prevent encoding '%', '/', '.' and '\', which are special reserved
        // characters
        if ((normalized.indexOf("%25") >= 0)
                || (normalized.indexOf("%2F") >= 0)
                || (normalized.indexOf("%2E") >= 0)
                || (normalized.indexOf("%5C") >= 0)
                || (normalized.indexOf("%2f") >= 0)
                || (normalized.indexOf("%2e") >= 0)
                || (normalized.indexOf("%5c") >= 0)) {
            return null;
        }

        if (normalized.equals("/."))
            return "/";

        // Normalize the slashes and add leading slash if necessary
        if (normalized.indexOf('\\') >= 0)
            normalized = normalized.replace('\\', '/');
        if (!normalized.startsWith("/"))
            normalized = "/" + normalized;

        // Resolve occurrences of "//" in the normalized path
        while (true) {
            int index = normalized.indexOf("//");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 1);
        }

        // Resolve occurrences of "/./" in the normalized path
        while (true) {
            int index = normalized.indexOf("/./");
            if (index < 0)
                break;
            normalized = normalized.substring(0, index) +
                    normalized.substring(index + 2);
        }

        // Resolve occurrences of "/../" in the normalized path
        while (true) {
            int index = normalized.indexOf("/../");
            if (index < 0)
                break;
            if (index == 0)
                return (null);  // Trying to go outside our context
            int index2 = normalized.lastIndexOf('/', index - 1);
            normalized = normalized.substring(0, index2) +
                    normalized.substring(index + 3);
        }

        // Declare occurrences of "/..." (three or more dots) to be invalid
        // (on some Windows platforms this walks the directory tree!!!)
        if (normalized.indexOf("/...") >= 0)
            return (null);

        // Return the normalized path that we have completed
        return (normalized);


    }
}
