package org.example.qodServer;

import org.example.controler.QodHandler;
import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;
import org.example.myHttpComponents.Type;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;

public class QodThread implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public QodThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            String reqLn = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(reqLn);
            String type = stringTokenizer.nextToken();
            String route = stringTokenizer.nextToken();

            while (!reqLn.trim().equals("")){
                System.out.println("Line :"+reqLn);
                reqLn = bufferedReader.readLine();
            }

            HashMap<String ,String> body = new HashMap<>();
            Req req = new Req(Type.valueOf(type),route,body);

            QodHandler handler = new QodHandler();
            Res res = handler.handle(req);
            System.out.println("response: "+res.respond());

            printWriter.println(res.respond());
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
