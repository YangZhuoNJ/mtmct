package ex01;

import java.io.*;

/**
 * Created by admin on 2017/6/7.
 */
public class Response {


    private final InputStream input;
    private final OutputStream output;
    private Request request;


    public Response(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {

        File file = new File(Cons.WEB_ROOT, request.getUri());
        byte[] buffer = new byte[Cons.BUFFER_SIZE];
        System.out.print(file.getPath());

        InputStream fis = null;
        if (file.isFile() && file.exists()) {
            try {
                fis = new FileInputStream(file);
                System.out.print(file.getPath());
                int ch = -1;
                ch = fis.read(buffer, 0, Cons.BUFFER_SIZE);
                while (ch != -1) {
                    output.write(buffer, 0, ch);
                    ch = fis.read(buffer, 0, Cons.BUFFER_SIZE);
                }
                output.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
