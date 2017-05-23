package learnfield.rpc.provider;

import learnfield.rpc.ServiceInterface;

/**
 * Created by admin on 2017/5/23.
 */
public class ServiceImpl implements ServiceInterface {

    public String service(String name) {
        return "Hello " + name;
    }

}
