package learnfield.rpc.provider;

import learnfield.rpc.ServiceInterface;

/**
 * Created by admin on 2017/5/23.
 */
public class Provider {

    public static void main(String[] args) {

        ServiceInterface service = new ServiceImpl();
        try {
            Publisher.publish(service);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
