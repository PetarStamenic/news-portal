package org.example.client.controler;

import org.example.client.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SendAction extends AbstractAction {

    private MainFrame client_view;
    private String msg;


    public SendAction(MainFrame client_view){
        this.client_view = client_view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        msg = client_view.getjTextField().getText();
        client_view.getClientMain().SendMessage(msg);
        client_view.getjTextField().setText("");
    }
}
