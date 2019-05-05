package study_swing.announecment;
import study_swing.DataGetters;
import study_swing.Dialog_Plug_in;
import top.chorg.kernel.communication.api.announcements.FetchListResult;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Announcement extends JFrame{
    /*
    public String title, content;...
    public DateTime validity;
    public int id, classId, level, status;
     */
    private String title, message;
    private noBackgroundPane titlePane = new noBackgroundPane(false), messagePane = new noBackgroundPane(false), topMessagePane = new noBackgroundPane(false);
    private JScrollPane jScrollPane;
    private static Point point = new Point();
    private leave leave;
    private FetchListResult fetchListResult;
    Announcement() {
        fetchListResult = null;
        title = "TITLE";
        message = "MESSAGE";
        setBasic();
        System.out.println("set messages: " + title + message);
    }
    Announcement(FetchListResult fetchListResult){
        this.fetchListResult= fetchListResult;
        this.title = fetchListResult.title;
        this.message = fetchListResult.content;
        setBasic();
    }

    Announcement(String title, String message) {
        fetchListResult = null;
        this.title = title;
        this.message = message;
        setBasic();
        System.out.println("set messages: " + title + message);
    }
    private void addMoveListener(JComponent jComponent){
        jComponent.addMouseListener(new plug(this, point));
        var l = new plug(this, point);
        l.setLeave(leave);
        jComponent.addMouseMotionListener(l);
    }
    private void setTopMessagePaneDefault(){
        topMessagePane.setPreferredSize(new Dimension(550, 50));
        topMessagePane.setFont(new Font("black", Font.ITALIC, 10));
        topMessagePane.setText("2019-5-1 00:00:00 in class 0, the promulgator is rtl");
        addMoveListener(topMessagePane);
    }
    private void setTopMessagePane(){
        topMessagePane.setPreferredSize(new Dimension(550, 50));
        topMessagePane.setFont(new Font("black", Font.ITALIC, 10));
        topMessagePane.setText(fetchListResult.date.toString() + "于" + DataGetters.getClassInfo(fetchListResult.classId).name + "班发布");
        addMoveListener(topMessagePane);
    }

    private void setTitlePane() {
        titlePane.setFont(new Font("black", Font.BOLD, 40));
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_CENTER);
        var doc = titlePane.getStyledDocument();
        doc.setParagraphAttributes(0, 0, simpleAttributeSet, false);
        titlePane.setPreferredSize(new Dimension(550, 250));
        addMoveListener(titlePane);
    }

    private void setMessagePane() {
        messagePane.setPreferredSize(new Dimension(550, 450));
        addMoveListener(messagePane);
    }

    private void setLeave() {
        ImageIcon l = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "exit.jpg");
        l.setImage(l.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        System.out.println(l.getIconWidth() + "." + l.getIconHeight());
        leave = new leave(this, l);
    }

    private void setBasic() {
        if (fetchListResult != null) setTopMessagePane();
        else setTopMessagePaneDefault();
        this.setLocation(100, 100);
        this.setSize(600, 800);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        titlePane.setText(title);
        messagePane.setText(message);
        jScrollPane = new JScrollPane(messagePane);
        addItems();
        setLeave();
        setTitlePane();
        setMessagePane();
        //this.setVisible(true);

    }

    private void addItems() {
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        JPanel jPanel = (JPanel) container;
        jPanel.setOpaque(false);
        jPanel.setBackground(new Color(255, 255, 255, 0));
        container.add(topMessagePane);
        container.add(titlePane);
        container.add(messagePane);
        container.add(jScrollPane);
    }
    public String getTitle(){return title;}


    public static void main(String[] args) {
        EventQueue.invokeLater(Announcement::new);
    }
}

