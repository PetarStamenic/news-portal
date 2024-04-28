package org.example.myHttpComponents;

import com.google.gson.Gson;
import org.example.myHttpComponents.Res;

public class Res200dto implements Res {
    private String html;
    private Gson gson = new Gson();

    public Res200dto(String html) {
        this.html = html;
    }

    @Override
    public String respond() {
        return "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n " + "\r\n" + gson.toJson(html, String.class);
    }
}
