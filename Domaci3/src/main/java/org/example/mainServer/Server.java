package org.example.mainServer;

import org.example.utils.Constants;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int port = Constants.MAIN_SERVER_IP;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new MainThread(socket)).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
