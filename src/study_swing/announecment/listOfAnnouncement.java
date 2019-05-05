package study_swing.announecment;


import study_swing.DataGetters;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.announcements.FetchListResult;
import top.chorg.kernel.communication.api.vote.FetchInfoResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

/*
* this class is created for main user interface when user check the Announcement button
* at this time it will show the unread announcement
* */
public class listOfAnnouncement extends JFrame {
    private Container container = this.getContentPane();
    private ImageIcon imageIcon;
    private myPanel[] myPanels;
    private JButton close = new JButton();
    private JButton search = new JButton("搜索公告");
    private JTextField searchField = new JTextField();
    private JPanel listPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private int classID;
    private int level;
    private JButton connect;
    listOfAnnouncement(JButton connect) {
        this(connect, 0);
    }
    public listOfAnnouncement(JButton connect, int classID) {
        super("您当前的公告列表");
        this.connect = connect;
        this.classID = classID;
        level = DataGetters.getLevel(classID);
        setBasic();
        createItems();
        setItems(connect);
        addItems();
        this.setIconImage(imageIcon.getImage());
        WindowManager.add(this, 5000 + classID);
        this.setVisible(true);
    }

    @Override
    public void dispose() {
        WindowManager.remove(5000 + classID);
        super.dispose();
    }

    public void onNewAnnounce(FetchListResult info) {
        //listPanel.add(new myPanel(this, info.title, info));
        this.dispose();
        new listOfAnnouncement(connect, classID);
    }

    private void setListPanel() {
        listPanel.setPreferredSize(new Dimension(590, 750));
        for (myPanel jButton : myPanels) {
            listPanel.add(jButton);
        }
    }

    private void setTopPanel() {
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setPreferredSize(new Dimension(590, 40));
        if (level != 0) {
            ImageIcon imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openAListEditor.jpg");
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            JButton plus = new JButton(imageIcon);
            plus.setToolTipText("单击以编辑公告");
            plus.addActionListener(e ->new A_control(classID));
            topPanel.add(plus);
        }
        topPanel.add(search);
        topPanel.add(searchField);
    }

    private void setBasic() {
        this.setBounds(600, 100, 600, 900);
        this.setResizable(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        container.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
    }

    private void createItems() {
        container = this.getContentPane();
        imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "AnnouncementList.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        close = new JButton("close");
    }

    private void setSearch() {
        search.setPreferredSize(new Dimension(80, 35));
        search.addActionListener(Event -> {
            search();
            searchField.setText("");
        });
    }

    private void setSearchField() {
        searchField.setColumns(15);
        searchField.setPreferredSize(new Dimension(200, 35));
    }

    /**
     * this method will show the list of the
     * announcements, this method prevent the users
     * from opening mutiple lists
     *
     * @param connect
     */
    private void setClose(JButton connect) {
        close.setBounds(this.getWidth() / 2 - 35, this.getHeight() - 60, 70, 20);
        close.addActionListener(Event -> {
            connect.setEnabled(true);
            this.dispose();
        });
    }

    private void setItems(JButton connect) {
        setClose(connect);
        setSearch();
        setSearchField();
        setMyPanels();
        setTopPanel();
        setListPanel();
    }

    private void addItems() {
        container.setBackground(new Color(255, 255, 255));
        container.add(topPanel);
        container.add(listPanel);
        container.add(close);
    }

    void setMyPanels() {
        ArrayList<FetchListResult> announcements = getMessages();
        int n = announcements.size();
        myPanels = new myPanel[n];
        for (int t = 0; t < n; t++) {
            myPanels[t] = new myPanel(this, announcements.get(t).title, announcements.get(t));
            myPanels[t].setPreferredSize(new Dimension(600, 40));
        }
    }

    /**
     * this value contain the message
     * of the announcement,
     * need to change after dui jie
     *
     * @return
     */
    private ArrayList<FetchListResult> getMessages() {
//        ArrayList<Announcement> announcements = new ArrayList<>();
//        announcements.add(new Announcement());
//        announcements.add(new Announcement());
//        return announcements;
        
        ArrayList<FetchListResult> announcements = new ArrayList<>();
        var a = DataGetters.getAnnounceList(false);
        for (FetchListResult fetchListResult : a) {
            if (fetchListResult.classId == classID) announcements.add(fetchListResult);
        }
        return announcements;
    }

    private void search() {
        System.out.println("building");
    }

    //A_list(){}
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("test");
        JButton jButton = new JButton("haha");
        jButton.addActionListener(e -> {
            System.out.println("haha");
            new listOfAnnouncement(jButton);
            jButton.setEnabled(false);
        });
        jButton.setPreferredSize(new Dimension(50, 50));
        jFrame.getContentPane().setLayout(new FlowLayout());
        jFrame.getContentPane().add(jButton);
        jFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

