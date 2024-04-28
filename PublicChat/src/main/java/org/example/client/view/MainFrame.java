package org.example.client.view;

import org.example.client.ClientMain;
import org.example.client.controler.EnterAction;
import org.example.client.controler.SendAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private ClientMain client_main;
    private JTextArea jTextArea;
    private JTextField jTextField;
    private JButton jButton;
    private JPanel bottonPane;
    private JScrollPane jScrollPane;
    private ChangeUsernameDialog changeUsernamedialog;

    public static MainFrame getInstance(){
        return instance;
    }

    public static MainFrame getInstance(ClientMain client_main){
        if(instance == null){
            instance = new MainFrame(client_main);
            instance.init();
            instance.initDialog();
        }
        return instance;
    }
    private MainFrame(ClientMain client_main){
        this.client_main = client_main;
    }

    private void init(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored){}
        initGui();

    }

    private void initDialog(){
        changeUsernamedialog = new ChangeUsernameDialog(client_main,this);
        changeUsernamedialog.setLocationRelativeTo(this);
        changeUsernamedialog.setVisible(true);
    }

    private void initGui(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;
        setSize(screenW/2,screenH/2);
        setLocationRelativeTo(null);;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Domaci 2");

        jTextArea = new JTextArea();
        jTextArea.setEnabled(false);
        jScrollPane = new JScrollPane(jTextArea);
        jTextField = new JTextField();
        jButton = new JButton("Send");
        add(jScrollPane,BorderLayout.CENTER);
        bottonPane = new JPanel();
        bottonPane.setLayout(new BoxLayout(bottonPane, BoxLayout.LINE_AXIS));
        bottonPane.add(jTextField);
        bottonPane.add(jButton);
        add(bottonPane,BorderLayout.SOUTH);
        jButton.addActionListener(new SendAction(getInstance()));
        jTextArea.setLineWrap(true);
        jTextField.addKeyListener(new EnterAction(getInstance()));
    }



    public void scrollToBottom(JScrollPane scrollPane) {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }


    public JTextField getjTextField() {
        return jTextField;
    }

    public ClientMain getClientMain() {
        return client_main;
    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public ChangeUsernameDialog getChangeUsername() {
        return changeUsernamedialog;
    }

}
