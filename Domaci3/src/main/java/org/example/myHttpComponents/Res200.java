package org.example.myHttpComponents;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Res200 implements Res{
    private String html;
    @Override
    public String respond() {
        return "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n"+html;
    }
}
