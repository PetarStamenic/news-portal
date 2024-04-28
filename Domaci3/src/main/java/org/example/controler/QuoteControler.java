package org.example.controler;

import com.google.gson.Gson;
import org.example.Quotes;
import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;
import org.example.myHttpComponents.Res200;
import org.example.myHttpComponents.Res300;
import org.example.utils.Constants;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QuoteControler implements Controler{
    private Req req;
    private HashMap<String ,String> quotes;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Gson gson = new Gson();
    private String reqLn;

    public QuoteControler(Req request){
        this.req = request;
        quotes = Quotes.getInstance().getQuotes();
    }

    @Override
    public Res doGet() {
        StringBuilder html = new StringBuilder("<form method=\"POST\" action = \"/save-quote\"><label>Author: </label><input name=\"author\" type=\"text\"><br><br><label>Quote: </label><input name=\"quote\" type=\"text\"><br><br><button type = \"submit\">Add quote</button></form><form><h1>Saved quotes</h1>");
        for(Map.Entry<String,String> map : quotes.entrySet()){
            html.append("<br><br>").append('"').append(map.getKey()).append('"').append(" ~ ").append(map.getValue()).append("<br>");
        }
        html.append("</form>");
        try {
            Socket socket = new Socket("localhost", Constants.QUOTE_SERVER_IP);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            printWriter.println("GET /would_you_please_kid_sir_get_me_the_quote_for_today HTTP/1.1\n" + "Accept: application/json\r\n\r\n");
            reqLn = bufferedReader.readLine();
            while (!reqLn.trim().equals("")){
                System.out.println("Request "+reqLn);
                reqLn = bufferedReader.readLine();
            }

            String theKindGentelmanSentMeThisQuote = gson.fromJson(bufferedReader.readLine(),String.class);
            html.append("<h1>Quote for the Day</h1>\n").append("<h3>").append(theKindGentelmanSentMeThisQuote).append("</h3>");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Res200(html.toString());
    }

    @Override
    public Res doPost() {
        String author = req.getBody().get("author");
        String quote = req.getBody().get("quote");
        quotes.put(quote,author);
        return new Res300("/quotes");
    }

    @Override
    public Res doPut() {
        //TODO - nekad mozda
        return null;
    }
}
