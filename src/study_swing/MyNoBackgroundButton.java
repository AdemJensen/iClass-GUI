package study_swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MyNoBackgroundButton extends JButton {
    public MyNoBackgroundButton(){
        setBasic();
    }
    public MyNoBackgroundButton(String mes){
        super(mes);
        setBasic();
    }
    public MyNoBackgroundButton(Icon icon){
        super(icon);
        setBasic();
    }
    private void setBasic(){
        Graphics graphics = this.getGraphics();
        Graphics2D graphics2D = (Graphics2D)graphics;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int r = 235, g = 235, b = 235, o = 40;
                Random random = new Random();
                MyNoBackgroundButton.this.setBackground(new Color(r, g, b, o));
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setBackground(new Color(0, 0, 0));
        this.setOpaque(false);
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
    }
}
