package org.example.qodServer;

import org.example.utils.Constants;

import java.net.ServerSocket;
import java.net.Socket;

public class QodServer {
    private static int port = Constants.QUOTE_SERVER_IP;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new QodThread(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
