package org.example.controler;

import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;
import org.example.myHttpComponents.Res404;
import org.example.myHttpComponents.Type;

public class Handler {

    public Res handle(Req req){
        try {
            if(req.getRoute().equals("/")&& req.getType().equals(Type.GET)){
                return (new QuoteControler(req).doGet());
            }else if(req.getRoute().equals("/quotes")&& req.getType().equals(Type.GET)){
                return (new QuoteControler(req).doGet());
            }else if (req.getRoute().equals("/save-quote") && req.getType().equals(Type.POST)){
                return (new QuoteControler(req).doPost());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Res404();
    }
}
