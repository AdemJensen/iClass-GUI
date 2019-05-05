package study_swing.announecment;

import study_swing.DataGetters;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/*
* in this area , the text of the ann can be changed if
* the manager logged
* and to be default there is an empty plane
*
* also can select
* */
public class AnnouncementEditor extends JFrame {
    private String text = "";
    private String title = "";
    private JTextPane eT = new JTextPane();//area of title
    private JTextPane tT = new JTextPane();//area of text
    private JLabel lableOfTitle = new JLabel("title");
    private JLabel lableOfMessage = new JLabel("text");
    private JButton create = new JButton();
    private JButton cancel = new JButton();//close this page
    private JButton deleteTitle = new JButton();
    private JButton deleteText = new JButton();
    private Container container = this.getContentPane();
    private int classID;

    private void setBasic() {
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 750);
        container.setLayout(new FlowLayout());
    }

    private void setStrings(preWroteAnnouncement preWroteAnnouncement) {
        text = preWroteAnnouncement.body;
        title = preWroteAnnouncement.title;
    }

    private void setButtons() {
        cancel = new JButton("cancel");
        create = new JButton("yes");
        cancel.setSize(40, 20);
        create.setSize(40, 20);
        setCancelListener();
        setCheckListener();
        deleteText.addActionListener(Event -> tT.setText(""));
        deleteTitle.addActionListener(Event -> eT.setText(""));
    }

    private void setCheckListener() {
        create.addActionListener(Event -> {
            title = eT.getText();
            text = tT.getText();
            if (DataGetters.addAnnounce(title, text, classID, 0).equals("OK")) {
                this.dispose();
            }
            new Announcement(title, text);
            System.out.println("set \"create\" successful!");
        });
    }

    private void setCancelListener() {
        cancel.addActionListener(Event -> {
            this.dispose();
            System.out.println("set \"cancel\" successful!");
        });
    }

    private void setTextArea() {
        seteT();
        settT();
    }

    private void seteT() {
        StyledDocument styledDocument = eT.getStyledDocument();
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_CENTER);
        styledDocument.setParagraphAttributes(0, 0, simpleAttributeSet, false);
        eT.setFont(new Font("black", Font.BOLD, 32));
        eT.setText(title);
        eT.setForeground(Color.black);
        eT.setPreferredSize(new Dimension(480, 200));
    }

    private void settT() {
        tT.setLocation(0, 200);
        tT.setFont(new Font("black", Font.BOLD, 15));
        tT.setText(text);
        tT.setPreferredSize(new Dimension(480, 500));
    }

    private void addItems() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(550, 220));
        lableOfTitle.setPreferredSize(new Dimension(50, 35));
        lableOfTitle.setFont(new Font("black", Font.BOLD, 15));
        lableOfTitle.setToolTipText("please set your title in this area");
        jPanel1.add(lableOfTitle);
        jPanel1.add(eT);
        container.add(jPanel1);

        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(550, 450));
        lableOfMessage.setPreferredSize(new Dimension(50, 35));
        jPanel2.add(lableOfMessage);
        lableOfMessage.setFont(new Font("black", Font.BOLD, 15));
        lableOfMessage.setToolTipText("please set your text in this area");
        jPanel2.add(tT);
        container.add(jPanel2);

        JPanel jPanel3 = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 20, 0);
        jPanel3.setLayout(flowLayout);
        jPanel3.add(create);
        jPanel3.add(cancel);
        jPanel3.setVisible(true);
        container.add(jPanel3);

    }

    private AnnouncementEditor(preWroteAnnouncement preWroteAnnouncement) {// an announcement that is body has been pre written;
        super("Announcement");
        setStrings(preWroteAnnouncement);
        setBasic();
        setButtons();
        setTextArea();
        addItems();
        setVisible(true);
    }

    AnnouncementEditor(int classID) {
        super("公告编辑器");
        this.classID = classID;
        setBasic();
        setButtons();
        setTextArea();
        addItems();
        setVisible(true);
    }

}
