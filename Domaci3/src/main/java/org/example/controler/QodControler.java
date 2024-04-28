package org.example.controler;

import org.example.Quotes;
import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;
import org.example.myHttpComponents.Res200dto;

import java.util.HashMap;
import java.util.Random;

public class QodControler implements Controler {
    Req req;
    Res200dto res200dto;
    HashMap<String,String> quotes = new HashMap<>();

    public QodControler(Req req) {
        this.req = req;
    }

    private String qod(){
        String quote = "";
        Random random = new Random();
        quotes = Quotes.getInstance().getQuotes();
        Object[] quotesArr = quotes.keySet().toArray();
        int num = random.nextInt(quotesArr.length-1);
        String q = quotesArr[num].toString();
        return q+" ~ "+quotes.get(q);
    }

    @Override
    public Res doGet() {
        return new Res200dto(qod());
    }

    @Override
    public Res doPost() {
        return null;
    }

    @Override
    public Res doPut() {
        return null;
    }
}
