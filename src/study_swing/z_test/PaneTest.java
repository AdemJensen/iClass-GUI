package study_swing.z_test;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class PaneTest extends JFrame {
    PaneTest(){
        JTextPane jTextPane = new JTextPane();
        JButton jButton = new JButton("ok");
        JButton icon = new JButton("icon");
        icon.addActionListener(Event ->{
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                jTextPane.insertIcon(new ImageIcon(file.getPath()));
                try {
                    jTextPane.getStyledDocument().insertString(jTextPane.getStyledDocument().getLength(), "\n", new SimpleAttributeSet());
                }catch (BadLocationException e){e.printStackTrace();}
                jTextPane.setCaretPosition(jTextPane.getStyledDocument().getLength());
            }
            catch (NullPointerException ignore){}
        });
        jTextPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                System.out.println(code);
                if (code == 8) System.out.println("delete!");
                if (code == 10 ) System.out.println("enter!");
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        jButton.addActionListener(Event -> System.out.println(jTextPane.getText()));
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(400, 600));
        //jTextPane.setPreferredSize(new Dimension(350, 300));
        jPanel.add(jTextPane);
        jPanel.add(jButton);
        jPanel.add(icon);
        this.getContentPane().add(jPanel);
        pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new PaneTest();
    }
}
