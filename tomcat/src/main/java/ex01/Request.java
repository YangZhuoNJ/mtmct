package ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by admin on 2017/6/7.
 */
public class Request {
    private final InputStream input;
    private final OutputStream output;

    private String uri = null;


    public Request(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public void parse() {
        byte[] buffer = new byte[Cons.BUFFER_SIZE];
        int ch = -1;
        StringBuffer sb = new StringBuffer();
        try {
            ch = input.read(buffer, 0, Cons.BUFFER_SIZE);
            if (ch != -1) {
                for(int j = 0; j < ch; j++) {
                    sb.append((char) buffer[j]);
                }
            }
            uri = parseUri(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String parseUri(String request) {
        try {
            int index1 = request.indexOf(" ");
            if (index1 != -1) {
                int index2 = request.indexOf(" ", index1 + 1);
                if (index2 > index1) {
                    return request.substring(index1 + 1, index2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
