package ex02;

/**
 * Created by admin on 2017/6/10.
 */
public abstract class Processor {

    protected final Request request;
    protected final Response response;

    public Processor(Request request, Response response) {
        this.request = request;
        this.response = response;
    }

    abstract void process();
}
