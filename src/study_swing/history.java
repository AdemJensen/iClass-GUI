package study_swing;

import javax.swing.*;
import java.awt.*;
public class history extends JFrame {
    private JTextArea jTextArea = new JTextArea();
    String text;
    public history(String title, String text) {
        super(title);
        this.text = text;
        setJTextArea();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setSize(600, 800);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setPreferredSize(new Dimension(540, 780));
        getContentPane().add(jScrollPane);
        setVisible(true);
    }

    private void setJTextArea() {
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.setText(text);
        jTextArea.setSize(540, 780);
        jTextArea.setFont(new Font("black", Font.BOLD, 15));
    }
}
