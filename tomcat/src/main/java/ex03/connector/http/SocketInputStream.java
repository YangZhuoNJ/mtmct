package ex03.connector.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 2017/7/6.
 */
public class SocketInputStream {

    private static final byte LF = (byte) '\r';
    private static final char CR = (byte) '\n';
    private static final byte SP = (byte) ' ';
    protected InputStream is;
    protected byte[] buf;

    protected int count;
    protected int pos;


    public SocketInputStream(InputStream inputStream, int bufferSize) {
        this.is = inputStream;
        buf = new byte[bufferSize];
    }

    public void readRequestLine(HttpRequestLine requestLine) throws IOException {

        if (requestLine.methodEnd != 0) {
            requestLine.recycle();
        }

        //skip blank line
        int chr = 0;
        do {
            try {
                chr = read();
            } catch (IOException e) {
                chr = -1;
            }
        } while (chr == CR || chr == LF);
        if (chr == -1) {
            throw new IOException("requestLine is blank line");
        }
        pos--;


        //start to read method name
        int maxRead = requestLine.method.length;   //size of the method buffer
        int readCount = 0;                         //elements counts of the method buffer

        boolean space = false;                     // space flag HTTP protocol

        while (!space) {
            //if the method buffer is fill, extends it
            if (readCount >= maxRead) {
                if (maxRead * 2 <= HttpRequestLine.MAX_METHOD_SIZE) {
                    char[] newBuffer = new char[maxRead * 2];
                    System.arraycopy(requestLine.method, 0, newBuffer, 0, maxRead);
                    requestLine.method = newBuffer;
                    maxRead = requestLine.method.length;
                } else {
                    throw new IOException("request line method is too long");
                }
            }

            //if we are at the ends of internal buffer
            if (pos >= count) {
                int val = read();
                if (val == -1) {
                    throw new IOException("requestLine method is null");
                }
                pos = 0;
            }

            if (buf[pos] == SP) {
                space = true;
            }
            requestLine.method[readCount] = (char) buf[pos];
            pos++;
            readCount++;
        }
        requestLine.methodEnd = readCount - 1;


        //start to read uri
        maxRead = requestLine.uri.length;           //requestLine uri buffer size
        readCount = 0;                              //elements counts of uri buffer

        space = false;

        boolean eol = false;

        while (!space) {
            //if the requestLine buffer is full, extends it
            if (readCount >= maxRead) {
                if (maxRead * 2 <= HttpRequestLine.MAX_URI_SIZE) {
                    char[] newBuffer = new char[maxRead * 2];
                    System.arraycopy(requestLine.uri, 0, newBuffer, 0, maxRead);
                    requestLine.uri = newBuffer;
                    maxRead = requestLine.uri.length;
                }else {
                    throw new IOException("requestLine uri is too long");
                }
            }


            //if we are at the end of internal buffer
            if (pos >= count) {
                int val = read();
                if (val == -1) {
                    throw new IOException("requestLine uri is null");
                }
                pos = 0;
            }

            if(buf[pos] == SP) {
                space = true;
            } else if (buf[pos] == LF || buf[pos] == CR) {
                //when step in here means it was a HTTP/0.9 style request
                eol = true;
                space = true;
            }
            requestLine.uri[readCount] = (char) buf[pos];
            pos++;
            readCount++;
        }
        requestLine.uriEnd = readCount - 1;

        //start to read protocol

        maxRead = requestLine.protocol.length;
        readCount = 0;

        while (!eol) {
            //if the requestLine protocol buffer is full, extends it
            if (readCount >= maxRead) {
                if (maxRead * 2 <= HttpRequestLine.MAX_PROTOCOL_SIZE) {
                    char[] newBuffer = new char[maxRead * 2];
                    System.arraycopy(requestLine.protocol, 0, newBuffer, 0, maxRead);
                    requestLine.protocol = newBuffer;
                    maxRead = requestLine.protocol.length;
                }
            }


            //if is the end of the internal buffer size
            if (pos >= count) {
                int val  = read();
                if (val == -1) {
                    throw new IOException("requestLine protocol is null error");
                }
                pos = 0;
            }

            if (buf[pos] == CR) {
                //skip CR
            } else if (buf[pos] == LF) {
                eol = true;
            }else {
                requestLine.protocol[readCount] = (char) buf[pos];
                readCount++;
            }
            pos++;
        }
        requestLine.protocolEnd = readCount;
    }

        /**
         * read byte.
         * @return
         * @throws IOException
         */

    public int read() throws IOException {
        if (pos >= count) {
            fill();
            //check after fill() , if still no new elements , means stream is over
            if (pos >= count) {
                return -1;
            }
        }
        // result & 0xff to make sure is a ASCII byte?
        return buf[pos++] & 0xff;
    }

    /**
     * fill the internal buffer use data from the underlying input stream
     *
     * @throws IOException
     */
    protected void fill() throws IOException {
        count = 0;
        pos = 0;
        int nRead = is.read(buf, 0, buf.length);
        if (nRead > 0) {
            count = nRead;
        }
    }


    public void close() throws IOException {
        if (is == null) {
            return;
        }
        is.close();
        is = null;   //release source
        buf = null;  //release internal buffer, for GC
    }
}
