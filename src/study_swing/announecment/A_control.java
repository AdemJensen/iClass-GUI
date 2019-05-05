package study_swing.announecment;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class A_control extends JFrame {
    private Container container = this.getContentPane();
    private JButton leave = new JButton();
    private JButton openAnnouncementEditor = new JButton();
    private JButton openAListEditor = new JButton();
    private JButton openPreEditor = new JButton();
    private JButton openPreChooser = new JButton();
    private int classID;
    private void setBasic() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        //setSize(75, 400);
        container.setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    private void setLeave() {
        ImageIcon exit = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "exit.jpg");
        exit.setImage(exit.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        leave.setIcon(exit);
        leave.setPreferredSize(new Dimension(50, 50));
        leave.addActionListener(Event -> {
            building();
            this.dispose();
        });
    }

    private void setOpenPreEditor() {
        openPreEditor.setPreferredSize(new Dimension(50, 50));
        ImageIcon openPreEditor = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openPreEditor.jpg");
        System.out.println(openPreEditor.getIconWidth());
        openPreEditor.setImage(openPreEditor.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.openPreEditor.setIcon(openPreEditor);
        this.openPreEditor.addActionListener(actionEvent -> {
            building();
            new preWriteEditor(classID);
        });
    }

    private void setOpenPreChooser() {
        openPreChooser.setPreferredSize(new Dimension(50, 50));
        ImageIcon openPreChooser = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openPreChooser.jpg");
        openPreChooser.setImage(openPreChooser.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.openPreChooser.setIcon(openPreChooser);
        this.openPreChooser.addActionListener(a -> {
            dispose();
            new preListOfAnnouncement(this.openPreChooser);
        });
    }

    private void setOpenAnnouncementEditor() {
        openAnnouncementEditor.setPreferredSize(new Dimension(50, 50));
        ImageIcon openAnnouncementEditor = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openAnnouncementEditor.jpg");
        openAnnouncementEditor.setImage(openAnnouncementEditor.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.openAnnouncementEditor.setIcon(openAnnouncementEditor);
        this.openAnnouncementEditor.addActionListener(Event -> {
            dispose();
            new AnnouncementEditor(classID);
        });
    }

    private void setOpenAListEditor() {
        openAListEditor.setPreferredSize(new Dimension(50, 50));
        ImageIcon openAListEditor = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openAListEditor.jpg");
        openAListEditor.setImage(openAListEditor.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        this.openAListEditor.setIcon(openAListEditor);
        this.openAListEditor.addActionListener(Event -> {
            dispose();
            new editorOfListOfAnnouncement(this.openAListEditor);
        });
    }

    private void addItems() {
        container.setLayout(new GridLayout(5, 1));
        container.add(leave);
        container.add(openAnnouncementEditor);
        container.add(openAListEditor);
        container.add(openPreEditor);
        container.add(openPreChooser);
    }

    private void building() {
    }

    public A_control(int classID) {
        this.classID = classID;
        setBasic();
        setLeave();
        setOpenAListEditor();
        setOpenAnnouncementEditor();
        setOpenPreEditor();
        setOpenPreChooser();
        addItems();

        setUndecorated(true);
        pack();
        setVisible(true);
    }

}