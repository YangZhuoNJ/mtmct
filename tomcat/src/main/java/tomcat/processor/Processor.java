package tomcat.processor;

import tomcat.base.HttpRequest;
import tomcat.base.HttpResponse;

/**
 * Created by admin on 2017/5/16.
 */
public interface Processor {

    void process(HttpRequest request, HttpResponse response);

}
