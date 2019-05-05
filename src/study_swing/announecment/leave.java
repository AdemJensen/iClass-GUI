package study_swing.announecment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class leave extends JFrame implements ActionListener, MouseListener {
    private JFrame relate;
    private JLabel onShown;

    leave(JFrame relate, ImageIcon imageIcon) {
        this.onShown = new JLabel(imageIcon);
        System.out.println(imageIcon.getIconWidth() + "." + imageIcon.getIconHeight());
        this.relate = relate;
        onShown.setPreferredSize(new Dimension(50, 50));
        setLocation();
        setBasic();
        //System.out.println(this.size());
        //System.out.println(this.getLocation());
    }

    void setLocation() {
        int x = relate.getX() + relate.getSize().width - 50;
        int y = relate.getY() - 50;
        this.setLocation(x, y);
    }

    private void setBasic() {
        this.getContentPane().add(onShown);
        setSize(onShown.getSize());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        addMouseListener(this);
        setUndecorated(true);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        relate.dispose();
        this.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        relate.dispose();
        this.dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
