package org.example.server;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ThreadMain {
    private ServerMain serverMain;
    private ServerClient serverClient;
    private String message;
    private int numberOfMessagesFromHistory = 100;
    private Thread input;

    public ThreadMain(ServerMain serverMain,ServerClient serverClient){
        this.serverMain = serverMain;
        this.serverClient = serverClient;
        initialConnect(numberOfMessagesFromHistory);
        io();
    }

    private void initialConnect(int numberOfMessages){
        ArrayList<String> history = new ArrayList<>();
        File logFile;
        Scanner scanner = null;
        BufferedReader inputSocket = null;
        PrintWriter outputSocket = null;
        try {
            logFile = new File("src\\main\\resources\\log.txt");
            scanner = new Scanner(logFile);
            while (scanner.hasNextLine()){
                history.add(scanner.nextLine());
            }

            if(history.isEmpty())
                return;
            int nom = Math.min(history.size(), numberOfMessages);
            for(int i = 0; i< nom ; i++) {
                String message = history.get(history.size()-nom+i);
                outputSocket = new PrintWriter(new OutputStreamWriter(serverClient.getSocket().getOutputStream()),true);
                outputSocket.println(message);
            }
            String welcomeMessage = "["+getTime()+"] "+serverClient.getUsername()+" has joined the chat";
            for(ServerClient client: serverMain.getOnlineClients()){
                System.out.println(serverMain.getOnlineClients());
                outputSocket = new PrintWriter(new OutputStreamWriter(client.getSocket().getOutputStream()),true);
                outputSocket.println(welcomeMessage);
                consolelog(welcomeMessage);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                scanner.close();
            } catch (Exception i){
                i.printStackTrace();
            }


        }
    }

    private void io(){
        input = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader inputSocket = null;
                PrintWriter outputSocket = null;
                try {
                    while (true){
                        inputSocket = new BufferedReader(new InputStreamReader(serverClient.getSocket().getInputStream()));
                        message = inputSocket.readLine();
                        if(message != null){
                            System.out.println(message);
                            String formatedMessage = formatMessage(message);
                            for(ServerClient client: serverMain.getOnlineClients()){
                                System.out.println(serverMain.getOnlineClients());
                                //if(!(client.getUsername().equals(serverClient.getUsername()))){
                                    outputSocket = new PrintWriter(new OutputStreamWriter(client.getSocket().getOutputStream()),true);

                                    outputSocket.println(formatedMessage);
                                //}
                            }
                            consolelog(formatedMessage);
                            if(message.equalsIgnoreCase("/exit")){
                                serverMain.disconect(serverClient.getUsername());
                                input.stop();
                            }
                        }
                    }
                }catch (IOException e){
                    e.printStackTrace();
                } finally {
                    try {
                        inputSocket.close();
                        outputSocket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        input.start();
    }

    private void consolelog(String message){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;

        try {
            File logFile = new File("src\\main\\resources\\log.txt");
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            fileWriter = new FileWriter("src\\main\\resources\\log.txt",true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.println(message);
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private String getTime(){
        Calendar cal = Calendar.getInstance();
        Format f = new SimpleDateFormat("hh:mm:ss a");
        return f.format(new Date());
    }
    private String formatMessage(String message){
        String msg = "/exit";
        return (message.equalsIgnoreCase(msg))?"["+getTime()+"] "+serverClient.getUsername()+" has left the chat":"["+getTime()+"] "+serverClient.getUsername()+": "+message;
    }

}
