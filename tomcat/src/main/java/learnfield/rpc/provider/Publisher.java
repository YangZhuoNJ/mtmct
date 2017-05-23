package learnfield.rpc.provider;

import learnfield.rpc.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 2017/5/23.
 */
public class Publisher {

    private static boolean publish = true;

    public static void publish(final Object impl) throws Exception {
        if (impl == null) {
            throw new IllegalArgumentException("Fail to publish null interface service");
        }

//        if (!interfaces.isInstance(impl)) {
//            throw new IllegalArgumentException("Impl not instanceof interfaces");
//        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(Constants.PORT, 1,  InetAddress.getByName(Constants.HOST));
        } catch (Exception e) {
            System.exit(-1);
        }

        while (publish) {
            final Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                public void run() {

                    ObjectOutputStream output = null;
                    ObjectInputStream input = null;
                    try {

                        output = new ObjectOutputStream(socket.getOutputStream());
                        input = new ObjectInputStream(socket.getInputStream());

                        //read service method
                        String methodName = input.readUTF();

                        //read parameter type array
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject();

                        //read arguments array
                        Object[] args = (Object[]) input.readObject();

                        //reflect interface to get method
                        Method method = impl.getClass().getDeclaredMethod(methodName, parameterTypes);

                        Object result = method.invoke(impl, args);
                        if (result instanceof Throwable) {
                            System.out.println("RPC invoke error");
                        }
                        output.writeObject(result);
                        output.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } finally {

                        //close socket
                        if (socket != null) {
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        //close Stream
                        if (output != null) {
                            try {
                                output.close();
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


                    }
                }
            }).start();
        }
    }
}
