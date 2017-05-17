package tomcat.client;

import tomcat.cons.Constants;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by admin on 2017/5/17.
 */
public class Client {

    public static void main(String[] arg) {
        try {
            Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), Constants.PORT);
            String http = "GET /servlet/MyServlet?username=nj&password=secret HTTP/1.1\r\n" +
                    "Host: localhost:8080\r\n" +
                    "Connection: keep-alive\r\n" +
                    "Upgrade-Insecure-Requests: 1\r\n" +
                    "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36\r\n" +
                    "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n" +
                    "Accept-Encoding: gzip, deflate, sdch, br\r\n" +
                    "Accept-Language: zh-CN,zh;q=0.8\r\n" +
                    "\r\n";
            OutputStream os = socket.getOutputStream();
            os.write(http.getBytes());
            os.flush();
            Thread.sleep(5000);


            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
