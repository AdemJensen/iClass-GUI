package study_swing.announecment;

import top.chorg.kernel.communication.api.announcements.FetchListResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class myPanel extends JPanel implements MouseListener {
    private JFrame relate;
    private FetchListResult obj;

    public myPanel(JFrame relate, String text, FetchListResult obj) {
        this.relate = relate;
        this.obj = obj;
        JLabel text1 = new JLabel(text);
        this.add(text1);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //relate.setExtendedState(JFrame.ICONIFIED);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(new Color(205, 205, 225));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(new Color(255, 255, 255));
        new Announcement(obj).setVisible(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
