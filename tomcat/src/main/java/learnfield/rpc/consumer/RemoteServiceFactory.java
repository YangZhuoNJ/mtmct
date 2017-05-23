package learnfield.rpc.consumer;

import learnfield.rpc.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by admin on 2017/5/23.
 */
public class RemoteServiceFactory {


    @SuppressWarnings("unchecked")
    public static <T> T call(Class<T> klass) {
        return (T) Proxy.newProxyInstance(klass.getClassLoader(), new Class<?>[] {klass}, new DynamicHandler());
    }

    private static class DynamicHandler implements InvocationHandler{

        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            Socket socket = null;
            ObjectOutputStream output = null;
            ObjectInputStream input = null;
            Object result = null;


            try {
                socket = new Socket(InetAddress.getByName(Constants.HOST), Constants.PORT);
                output = new ObjectOutputStream(socket.getOutputStream());
                input = new ObjectInputStream(socket.getInputStream());

                output.writeUTF(method.getName());
                output.writeObject(method.getParameterTypes());
                output.writeObject(args);
                output.flush();

                try {
                     result= input.readObject();
                    if (result instanceof Throwable) {
                        throw new Exception("RPC invoke Exception");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return result;



            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            throw new Exception("Invoke Exception");
        }
    }
}
