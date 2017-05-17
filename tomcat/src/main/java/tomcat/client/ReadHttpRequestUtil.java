package tomcat.client;

import java.io.InputStream;
import java.net.Socket;

/**
 * Created by admin on 2017/5/17.
 */
public class ReadHttpRequestUtil {


    public static String getHttpDatagram(Socket socket) {

        try {
            InputStream is = socket.getInputStream();
            byte[] bytes = new byte[2048];

            int ch = is.read(bytes, 0, 2048);
            StringBuffer sb = new StringBuffer();
            if (ch != -1) {
                for(int i = 0; i <ch ; i++) {
                    sb.append((char) bytes[i]);
                }
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
