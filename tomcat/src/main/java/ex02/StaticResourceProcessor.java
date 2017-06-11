package ex02;

import java.io.IOException;

/**
 * Created by admin on 2017/6/10.
 */
public class StaticResourceProcessor extends Processor {

    public StaticResourceProcessor(Request request, Response response) {
        super(request, response);
    }

    public void process() {
        try {
            response.process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
