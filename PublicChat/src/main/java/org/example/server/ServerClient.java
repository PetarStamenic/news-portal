package org.example.server;

import java.net.Socket;

public class ServerClient {
    private Long id;
    private String username;
    private Socket socket;

    public ServerClient(String username, Socket socket) {
        this.id = Repository.getInstance().login(username);
        this.username = username;
        this.socket = socket;
    }

    public void exit(){
        try {
            this.socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public Socket getSocket() {
        return socket;
    }
}
