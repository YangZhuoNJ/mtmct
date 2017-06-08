package sniffer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by admin on 2017/6/7.
 */
public class HttpSniffer extends JFrame {

    private JTextArea port = new JTextArea();
    private JTextArea host = new JTextArea();
    private JTextField request = new JTextField();

    private JLabel portLable = new JLabel();
    private JLabel hostLable = new JLabel();

    private JButton send = new JButton();

    private JTextArea response = new JTextArea();

    public HttpSniffer() {
        try {
            jInit();
        } catch (Exception e) {

        }
    }

    private void jInit() {
        this.getContentPane().setLayout(null);

        hostLable.setText("Host");
        hostLable.setBounds(new Rectangle(10, 15, 30, 20));
        host.setText("127.0.0.1");
        host.setBounds(new Rectangle(50,15, 60, 20));
        this.getContentPane().add(host, null);
        this.getContentPane().add(hostLable, null);

        portLable.setText("Port");
        portLable.setBounds(new Rectangle(120, 15, 30, 20));
        port.setText("8080");
        port.setBounds(new Rectangle(160, 15, 60, 20));
        this.getContentPane().add(portLable, null);
        this.getContentPane().add(port, null);

        request.setText("GET /index.html HTTP/1.1");
        request.setBounds(new Rectangle(10, 40, 210, 20));
        this.getContentPane().add(request, null);

        send.setText("Send");
        send.setBounds(new Rectangle(240, 15, 80, 45));
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send_action_performed();
            }
        });
        this.getContentPane().add(send, null);
    }

    private void send_action_performed() {

        response.setText("");

        String portNumber = null;
        String hostName = null;
        String command = null;

        boolean ok = true;

        try {
            portNumber = port.getText();
            hostName = host.getText();
            command = request.getText();
        } catch (Exception e) {
            ok = false;
        }
        if (ok) {
            try {
                Socket socket = new Socket(hostName, Integer.parseInt(portNumber));
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter out = new PrintWriter(outputStream, true);
                out.println(command);
                out.println("Host: localhost:8080");
                out.println("Connection: Close");
                out.println();

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                boolean loop = true;

                StringBuffer sb = new StringBuffer(8096);

                while (loop) {
                    if (reader.ready()) {
                        int i = 0;
                        while (i != -1) {
                            i = reader.read();
                            sb.append((char) i);
                        }
                        loop = false;
                    }
                    Thread.currentThread().sleep(50);
                }
                response.setText(sb.toString());
                socket.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        HttpSniffer sniffer = new HttpSniffer();
        sniffer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sniffer.setBounds(new Rectangle(0, 0, 400, 600));
        sniffer.setVisible(true);
    }


}
