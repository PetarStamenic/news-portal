package org.example.client.view;

import org.example.client.ClientMain;

import javax.swing.*;
import java.awt.*;

public class ChangeUsernameDialog extends JDialog {
    ClientMain client_main;
    public ChangeUsernameDialog(ClientMain client_main, MainFrame client_view){
        super(client_view);
        this.client_main = client_main;
        initialise();
    }

    public void initialise(){

        this.setTitle("Choose username");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        this.setSize(300,80);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setUndecorated(false);

        JTextField jTextField = new JTextField("",25);

        JCheckBox profanityFilter = new JCheckBox("Profanity filter");
        JButton button = new JButton("OK");

        jTextField.setMaximumSize(new Dimension(250,75));



        button.setSize(30,10);
        button.addActionListener(e -> client_main.setup(jTextField.getText(),profanityFilter.isSelected()));

        this.add(jTextField,BorderLayout.CENTER);
        this.add(button,BorderLayout.EAST);
        this.add(profanityFilter,BorderLayout.SOUTH);

    }
}
