package ex02;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by admin on 2017/6/10.
 */
public class ServletProcessor extends Processor {

    public ServletProcessor(Request request, Response response) {
        super(request, response);
    }

    public void process() {

        String uri = request.getUri();
        String servletName = uri.substring(uri.indexOf("/") + 1);

        // create Servlet loader
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            URLStreamHandler handler = null;
            File classpath = new File(Cons.WEB_ROOT);

            String repository = (new URL("file", null, classpath.getCanonicalPath() + File.separator)).toString();

            urls[0] = new URL(null, repository, handler);
            loader = new URLClassLoader(urls);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Class servletKlass = null;
        Servlet servlet = null;

        try {
            servletKlass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            servlet = (Servlet) servletKlass.newInstance();
            servlet.service((ServletRequest) request, (ServletResponse) response);
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
