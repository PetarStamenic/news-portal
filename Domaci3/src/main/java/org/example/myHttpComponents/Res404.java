package org.example.myHttpComponents;

public class Res404 implements Res{
    @Override
    public String respond() {
        return "HTTP/1.1 404 Not Found";
    }
}
