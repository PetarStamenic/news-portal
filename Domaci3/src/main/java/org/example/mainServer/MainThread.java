package org.example.mainServer;

import org.example.controler.Handler;
import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;
import org.example.myHttpComponents.Type;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MainThread implements Runnable{
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private String Content_Length;

    public MainThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);

            String reqLn = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(reqLn);

            String type = stringTokenizer.nextToken();
            String route = stringTokenizer.nextToken();

            System.out.println("____________________");
            System.out.println("requests:");

            while (!reqLn.trim().equals("")){
                //System.out.println("____________________");
                System.out.println(reqLn);
                reqLn = bufferedReader.readLine();
                if(reqLn.startsWith("Content-Length:"))
                    Content_Length = reqLn.substring(16);
            }

            HashMap<String ,String> body = new HashMap<>();
            if(type.equals(Type.POST.toString())){
                char[] buffer = new char[Integer.parseInt(Content_Length)];
                bufferedReader.read(buffer);
                String params = new String(buffer);
                System.out.println(params);
                String[] arr = params.split("&");
                for(String key:arr){
                    String[] splited = key.split("=");
                    String first = splited[0];
                    String snd = splited[1];
                    body.put(first, URLDecoder.decode(snd, StandardCharsets.UTF_8));
                }
                System.out.println("quote : "+body.keySet()+" : "+body.values());
            }

            Req req = new Req(Type.valueOf(type),route,body);
            Handler handler = new Handler();
            System.out.println(req);
            Res res = handler.handle(req);
            System.out.println(res);
            printWriter.println(res.respond());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                printWriter.close();
                bufferedReader.close();
                socket.close();
            }catch (Exception i){
                i.printStackTrace();
            }
        }
    }
}
