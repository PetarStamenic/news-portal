package org.example.controler;

import org.example.myHttpComponents.Req;
import org.example.myHttpComponents.Res;

public interface Controler {
    public Res doGet();
    public Res doPost();
    public Res doPut();
}
