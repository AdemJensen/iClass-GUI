package study_swing.Vote;

import study_swing.DataGetters;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.vote.FetchListResult;
import top.chorg.kernel.communication.api.vote.FetchInfoResult;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class voteList extends JFrame {
    private Container container = this.getContentPane();
    private ImageIcon imageIcon;
    private myPanelForVote[] myPanels;
    private JButton close = new JButton("关闭");
    private JButton search = new JButton("搜索投票");
    private JTextField searchField = new JTextField();
    private JPanel listPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private int classID;
    private int level;
    private JButton connect;

    public voteList(JButton connect, int classID) {
        super("您当前的投票列表");
        this.connect = connect;
        this.classID = classID;
        level = DataGetters.getLevel(classID);
        setBasic();
        createItems();
        setItems(connect);
        addItems();
        this.setIconImage(imageIcon.getImage());
        WindowManager.add(this, 7000 + classID);
        this.setVisible(true);
    }

    @Override
    public void dispose() {
        WindowManager.remove(7000 + classID);
        super.dispose();
    }

    public void onNewVote(FetchInfoResult info) {
        //listPanel.add(new myPanelForVote(this, info.title, info));
        this.dispose();
        new voteList(connect, classID);
    }

    private void setListPanel() {
        listPanel.setPreferredSize(new Dimension(590, 750));
        for (myPanelForVote myPanelForVote : myPanels) {
            listPanel.add(myPanelForVote);
        }
    }

    private void setTopPanel() {
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setPreferredSize(new Dimension(590, 40));
        if (level != 0) {
            ImageIcon imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openAListEditor.jpg");
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            JButton plus = new JButton(imageIcon);
            plus.setToolTipText("单击以新建投票");
            plus.addActionListener(e ->new VoteMaker(classID));
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
        imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "AnnouncementList.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
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
     * get, this method prevent the users
     * from opening mutiple lists
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
        FetchListResult[] get = DataGetters.getVoteList(false);
        int n = get.length;
        myPanels = new myPanelForVote[n];
        for (int t = 0; t < n; t++) {
            myPanels[t] = new myPanelForVote(this, get[t].title, DataGetters.getVoteInfo(get[t].id));
            myPanels[t].setPreferredSize(new Dimension(600, 40));
        }
    }
    
    private void search() {
        search.addActionListener(e -> searchField.setText(""));
    }

}


class myPanelForVote extends JPanel implements MouseListener {
    private FetchInfoResult obj;

    public myPanelForVote(JFrame relate, String title, FetchInfoResult obj) {
        this.obj = obj;
        JLabel text1 = new JLabel(title);
        this.add(text1);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //relate.setExtendedState(JFrame.ICONIFIED);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(new Color(185, 185, 185));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(new Color(255, 255, 255));
        if (obj.method == 0) {
            new V_Single(obj);
        } else {
            new V_multiple(obj);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(new Color(225, 225, 225));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(new Color(255, 255, 255));
    }
}