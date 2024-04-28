package org.example.client;

import com.google.protobuf.Message;
import org.example.client.view.MainFrame;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientMain {
    private String username;
    private String message;
    private int proxy = 9000;
    private boolean profanityFilter;
    private Socket socket = new Socket("localhost",proxy);
    private BufferedReader inputSocket = null;
    private PrintWriter outputSocket = null;
    private ArrayList<String> filter = new ArrayList<>();
    MainFrame frame = MainFrame.getInstance(this);
    private Thread input;

    Thread shutdownHook = new Thread(()->exit());

    public ClientMain()throws IOException{
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        loadFilter();
        inputSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outputSocket = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       io();
    }

    public static void main(String[] args) {
        try {
            new ClientMain();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void io(){
        input = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        String msg = inputSocket.readLine();
                        ReciveMessage(msg);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        inputSocket.close();
                        outputSocket.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });
        input.start();
    }

    public void ReciveMessage(String message){
        if(message != null && !message.equals("")){
            System.out.println("ovde ulazi"+message);
            if(profanityFilter){
                frame.getjTextArea().append("\n"+filter(message));
            }else {
                frame.getjTextArea().append("\n"+message);
            }
            frame.scrollToBottom(frame.getjScrollPane());
        }
    }

    public void SendMessage(String message){
        System.out.println(message);
        outputSocket.println(message);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        frame.getChangeUsername().dispose();
        outputSocket.println(username);
        MainFrame.getInstance().setTitle(username);
    }

    public void exit(){
        outputSocket.println("/exit");
        input.stop();
        try {
            this.socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setup(String username,boolean isProfanityFilter){
        this.profanityFilter = isProfanityFilter;
        setUsername(username);
    }

    private void loadFilter(){
        Scanner scanner = null;
        try {
            File forbiden = new File("src\\main\\resources\\forbiden.csv");
            scanner = new Scanner(forbiden);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] split = line.split(",");
                for (String f:split){
                    filter.add(f);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }
    private String filter(String message){
        for(String str: filter){
            if(message.toLowerCase().contains(str.toLowerCase())){
                StringBuilder stringBuilder = new StringBuilder();
                String[] words = message.split(" ");
                for(String w:words){
                    if(w.equalsIgnoreCase(str)) {
                        stringBuilder.append(censore(w));
                        stringBuilder.append(" ");
                    }else {
                        stringBuilder.append(w);
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                return stringBuilder.toString();
            }
        }
        return message;
    }

    private String censore(String message){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<message.length();i++){
            if(i == 0 || i == message.length()-1){
                sb.append(message.charAt(i));
            } else {
                sb.append("*");
            }
        }
        return sb.toString();
    }
}
