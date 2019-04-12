package top.chorg.views.Auth;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame {
    int id;

    public LoginFrame() {
        setBounds(200, 100, 200, 300);
        setTitle("Login");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                dispose();
            }
        });
    }
}
