package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ServerMain {
    private Socket socket;
    private String message;
    private ArrayList<ServerClient> onlineClients = new ArrayList<>();
    private int proxy = 9000;
    private ServerSocket serverSocket = new ServerSocket(proxy);

    public ServerMain()throws IOException{
        while (true){
            Socket socket1 = serverSocket.accept();
            BufferedReader initial = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            String username =  initial.readLine();
            if(username == null || username == ""){
                username = "Guest"+Repository.getInstance().addGuest();
            }
            ServerClient sc = new ServerClient(username,socket1);
            onlineClients.add(sc);
            ThreadMain threadMain = new ThreadMain(this,sc);
        }
    }

    public static void main(String[] args) {
        try {
            new ServerMain();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<ServerClient> getOnlineClients() {
        return onlineClients;
    }

    public boolean disconect(String username){
        ServerClient removable = null;
        for(ServerClient client:onlineClients){
            if(client.getUsername().equalsIgnoreCase(username)){
                removable = client;
            }
        }
        return onlineClients.remove(removable);
    }
}
