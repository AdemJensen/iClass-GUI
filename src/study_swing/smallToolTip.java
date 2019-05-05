package study_swing;

import javax.swing.*;
import java.awt.*;

public class smallToolTip extends JFrame {
    private JTextArea jTextArea = new JTextArea();
    String text;
    public smallToolTip(String title, String text) {
        super(title);
        this.text = text;
        setJTextArea();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setSize(400, 80);
        setBounds(500, 300, 400, 80);
        setBackground(new Color(255, 255, 255));
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(400, 80));
        getContentPane().add(jScrollPane);
        setVisible(true);
        new top.chorg.support.Timer(4000, (Object[] arg) -> {
            this.dispose();
            return 0;
        });
    }

    private void setJTextArea() {
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.setText(text);
        jTextArea.setSize(400, 80);
        jTextArea.setFont(new Font("black", Font.BOLD, 15));
    }
}
