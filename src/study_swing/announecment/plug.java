package study_swing.announecment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class plug implements MouseListener, MouseMotionListener {
    private JFrame about;
    private Point point;
    private leave leave;
    plug(JFrame about, Point point) {
        this.about = about;
        this.point = point;
    }

    void setLeave(leave leave){
        this.leave = leave;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        point.x = e.getX();
        point.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = about.getLocation();
        about.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
        if (leave != null) leave.setLocation();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
