package ex03.connector.http;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 2017/7/6.
 */
public class HttpResponse {


    private final OutputStream output;
    private HttpRequest request;

    protected HashMap header = new HashMap();
    private boolean committed = false;
    private int contentLength = -1;
    private String contentType;


    public HttpResponse(OutputStream output) {
        this.output = output;
    }

    public void setHeader(String name, String value) {
        if (isCommitted()) {
            return;
        }

        ArrayList values = new ArrayList();
        values.add(value);

        synchronized (header) {
            header.put(name, values);
            String match = name.toLowerCase();
            if ("content-length".equals(match)) {
                int contentLength = -1;
                try {
                    contentLength = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (contentLength > 0) {
                    setContentLength(contentLength);
                }
            } else if ("content-type".equals(match)) {
                setContentType(value);
            }
        }
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public boolean isCommitted() {
        return committed;
    }

    public void setContentLength(int contentLength) {
        if (isCommitted()) {
            return;
        }
        this.contentLength = contentLength;
    }

    public void setContentType(String contentType) {
        if (isCommitted()) {
            return;
        }
        this.contentType = contentType;
    }
}
