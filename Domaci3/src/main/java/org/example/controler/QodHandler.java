package org.example.controler;

import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;
import org.example.myHttpComponents.Res404;
import org.example.myHttpComponents.Type;

public class QodHandler {
    public Res handle(Req req){
        try {
            if(req.getRoute().equals("/would_you_please_kid_sir_get_me_the_quote_for_today") && req.getType().equals(Type.GET))
                return new QodControler(req).doGet();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Res404();
    }
}
