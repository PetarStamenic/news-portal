package org.example.myHttpComponents;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Res300 implements Res{

    private String adress;
    @Override
    public String respond() {
        return "HTTP/1.1 301 Ja sam svetski mega car\r\nLocation: " + this.adress + "\r\n\r\n";
    }
}
