package cn.Yogaguo.nettychat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends Frame {
    private TextArea ta = new TextArea();
    private TextField tf = new TextField();
    public static final ClientFrame INSTACE = new ClientFrame();
    private Client c = null;
    private ClientFrame()  {
        this.setSize(300,400);
        this.setLocation(400,20);
        this.add(ta,BorderLayout.CENTER);
        this.add(tf,BorderLayout.SOUTH);
        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.send(tf.getText());
                //send to server
                tf.setText("");
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                 c.closeConnection();
                 System.exit(0);
            }
        });

    }
    public void connecToServer(){
        c = new Client();
        c.connect();
    }

    public static void main(String[] args) {
        ClientFrame f =  ClientFrame.INSTACE;
        f.setVisible(true);
        f.connecToServer();
    }

    public void updataText(String str) {
         ta.setText(ta.getText()+  str+"\r\n");
    }
}
