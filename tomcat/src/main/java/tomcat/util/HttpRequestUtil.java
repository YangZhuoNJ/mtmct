package tomcat.util;

import org.apache.catalina.util.ParameterMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by admin on 2017/5/18.
 */
public class HttpRequestUtil {

    /**
     * parse http request cookie header
     * example : header = "...;xxx=XXX;yyy=YYY;...."
     * @param header http cookie header
     * @return http cookie array
     */
    public static Cookie[] parseCookieHeader(String header) throws ServletException {
        if (header == null || header.length() <= 0) {
            return new Cookie[0];
        }

        ArrayList<Cookie> cookies = new ArrayList<Cookie>();

        String[] tokens = header.split(";");
        for (int i = 0; i < tokens.length; i++) {
            try {
                int equals = tokens[i].indexOf("=");
                if (equals > 0 && equals < tokens[i].length()) {
                    String name = tokens[i].substring(0, equals);
                    String value = tokens[i].substring(equals + 1);
                    cookies.add(new Cookie(name, value));
                } else {
                    throw new ServletException("Parsing cookie header Token Exception");
                }
            } catch (Exception e) {
                e.printStackTrace();
//                throw new ServletException(e);
            }
        }

        return cookies.toArray(new Cookie[cookies.size()]);
    }

    public static void parseParameters(ParameterMap results, String queryString, String encoding) throws UnsupportedEncodingException{
        //TODO parse http request parameters
        
    }
}
