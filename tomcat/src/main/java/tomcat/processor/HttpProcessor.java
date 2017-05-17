package tomcat.processor;

import org.apache.catalina.connector.http.HttpRequestLine;
import org.apache.catalina.connector.http.SocketInputStream;
import tomcat.base.HttpRequest;
import tomcat.base.HttpResponse;
import tomcat.connector.HttpConnector;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by admin on 2017/5/15.
 */
public class HttpProcessor {

    private HttpConnector connector;

    private HttpRequestLine requestLine = new HttpRequestLine();

    private HttpRequest request = null;
    private HttpResponse response = null;

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
            request = new HttpRequest(sis);

            //create Response Object
            response = new HttpResponse(os);
            response.setRequest(request);

            response.setHeader("Server", "NJ's Servlet Container");

            parseRequest(sis, os);
            parseHeader(sis);


            SourceProcessor sourceProcessor = null;

            //check if this request for servlet or a static source
            //if a request for servlet begin with "/servlet"
            if (request.getRequestURI().startsWith("/servlet")) {
                sourceProcessor = new ServletProcessor();
            } else {
                sourceProcessor = new StaticSourceProcessor();
            }

            sourceProcessor.process(request, response);

            //close the socket release source
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void parseHeader(SocketInputStream sis) {

    }

    /**
     * parse the Http request line
     * example   requestLine: GET /myApp/ModernServlet?username=nj_yang&password=secret HTTP/1.1
     * example   URI : /myApp/ModernServlet?username=nj_yang&password=secret
     * @param sis socket's SocketInputStream
     * @param os  socket's OutputStream
     * @throws IOException ..
     * @throws ServletException ..
     */
    private void parseRequest(SocketInputStream sis, OutputStream os) throws IOException, ServletException {
        sis.readRequestLine(requestLine);

        //http request method  such as : GET POST ......
        String method = new String(requestLine.method, 0, requestLine.methodEnd);

        //uri
        String uri = null;

        //protocol
        String protocal = new String(requestLine.protocol, 0, requestLine.protocolEnd);

        //validate the incoming request line
        //check request method and URI
        // TODO: to make to a private method  2017/5/16
        if (method.length() < 1) {
            throw new ServletException("Missing Http Request Method");
        } else if (requestLine.uriEnd < 1) {
            throw new ServletException("Missing Http Request URI");
        }

        int question = requestLine.indexOf("?");
        if (question >= 0) {
            request.setQueryString(new String(requestLine.uri, question + 1, requestLine.uriEnd - question - 1));
            uri = new String(requestLine.uri, 0, question);
        } else {
            request.setQueryString(null);
            uri = new String(requestLine.uri, 0, requestLine.uriEnd);
        }

        //checking for an absolute URI (with HTTP protocol)
        if (!uri.startsWith("/")) {
            int pos = uri.indexOf("://");
            //Parsing out protocol and host name
            if (pos == -1) {
                uri = "";
            } else {
                uri = uri.substring(pos);
            }
        }

        //Parsing any requested session ID out of the request URI
        String match = ";jsessionid=";
        int semicolon = uri.indexOf(match);
        //if match
        if (semicolon > 0) {
            String rest = uri.substring(semicolon + match.length());
            int semicolon2 = rest.indexOf(";");
            if (semicolon2 >= 0) {
                request.setRequestedSessionId(rest.substring(0, semicolon2));
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

        //todo Normalize URI
        String normalizeUri = normalize(uri);
        ((HttpRequest) request).setMethod(method);
        request.setProtocol(protocal);
        //set the corresponding request properties
        if (normalizeUri != null) {
            ((HttpRequest) request).setRequestURI(normalizeUri);
        } else {
            ((HttpRequest) request).setRequestURI(uri);
        }

        if (normalizeUri == null) {
            throw new ServletException("Invalidate request uri " + uri + "'");
        }


    }

    private String normalize(String uri) {
        return null;
    }


}
