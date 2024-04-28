package org.example.client.controler;

import org.example.client.view.MainFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EnterAction extends KeyAdapter {

    private MainFrame client_view;
    private String msg;

    public EnterAction(MainFrame client_view){
        this.client_view = client_view;
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            msg = client_view.getjTextField().getText();
            client_view.getClientMain().SendMessage(msg);
            client_view.getjTextField().setText("");
        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            client_view.getClientMain().exit();
        }
    }
}
