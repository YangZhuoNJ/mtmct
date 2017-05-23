package learnfield.rpc.consumer;

import learnfield.rpc.ServiceInterface;

/**
 * Created by admin on 2017/5/23.
 */
public class Consumer {

    public static void main(String[] args) {

        ServiceInterface remoteService = RemoteServiceFactory.call(ServiceInterface.class);

        System.out.println(remoteService.service("NJ_yang"));
    }
}
