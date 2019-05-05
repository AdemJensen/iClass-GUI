package study_swing.MainUI;

import study_swing.DataGetters;
import study_swing.MyNoBackgroundButton;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.auth.ClassInfo;
import top.chorg.kernel.communication.api.auth.User;
import top.chorg.kernel.communication.auth.AuthManager;
import top.chorg.system.Sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class MainUI extends JFrame {
    private JLabel backGround = new JLabel();
    private Container container = this.getContentPane();
    private JPanel friendsAndGroups = new JPanel();
    private JPanel userMessage = new JPanel();
    private JPanel lastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private leave leave;
    private JScrollPane jScrollPane;
    private static Point point = new Point();
    private User user = AuthManager.getUser();
    private ClassInfo[] classes = getClasses();
    private int[] onlineUsers;
    private ImageIcon userIcon;

    private User[] getUsersFromId(int[] ids) {
        User[] res = new User[ids.length];
        for (int t = 0; t < ids.length; t++) {
            res[t] = DataGetters.getUserInfo(ids[t]);
        }
        return res;
    }

    private User[] getUsers(int classId) {
        return getUsersFromId(DataGetters.getClassInfo(classId).classmates);
    }

    private ClassInfo[] getClasses() {
        int[] get = AuthManager.getUser().getClassId();
        ClassInfo[] res = new ClassInfo[get.length];
        for (int t = 0; t < res.length; t++) res[t] = DataGetters.getClassInfo(get[t]);
        return res;
    }

    private int[] getOnlineUsers(int classId) {
        return DataGetters.getClassOnline(classId);
    }

    private ArrayList<User> getOnlineUsers(ClassInfo[] classes) {
        ArrayList<User> res = new ArrayList<>();
        ArrayList<Integer> resJudge = new ArrayList<>();
        for (ClassInfo aClass : classes) {
            for (int classmate : aClass.classmates) {
                if (!resJudge.contains(classmate)) {
                    resJudge.add(classmate);
                }
            }
        }
        resJudge.forEach(t -> {
            res.add(DataGetters.getUserInfo(t));
        });
        return res;
    }

    private void setBasic() {
        container.setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(400, 700);
        leave = new leave(this, 25);
    }


    private void setBackGround() {
        ImageIcon imageIcon = new ImageIcon("SourcePackage" + File.separator + "Background" + File.separator + "UIBackground.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(400, 250, Image.SCALE_SMOOTH));
        backGround.setIcon(imageIcon);
        backGround.setBounds(0, 0, 400, 250);
        addMoveListener(backGround);
    }

    private void setUserMessage() {
        userMessage.setBackground(new Color(0, 0, 0, 0));
        userMessage.setLayout(new FlowLayout(FlowLayout.LEFT));
        userMessage.setPreferredSize(new Dimension(300, 150));
        userIcon.setImage(userIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        userMessage.setBounds(50, 50, 300, 150);
        JLabel iconLabel = new JLabel(userIcon);
        iconLabel.setPreferredSize(new Dimension(100, 100));
        iconLabel.setBackground(new Color(0, 0, 0, 0));
        userMessage.add(iconLabel, 0);
        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.setPreferredSize(new Dimension(180, 150));
        JLabel jLabel = new JLabel(user.getUsername());
        jLabel.setFont(new Font("black", Font.BOLD, 30));
        jLabel.setPreferredSize(new Dimension(160, 30));
        jLabel.setBackground(new Color(0, 0, 0, 0));
        jPanel.add(jLabel);
        JLabel jLabel1;
        if (user.getRealName() == null) jLabel1 = new JLabel("未设置您的真实姓名");
        else jLabel1 = new JLabel("真实姓名  " + user.getRealName());
        jLabel1.setFont(new Font("black", Font.BOLD, 12));
        jLabel1.setPreferredSize(new Dimension(160, 25));
        jLabel1.setBackground(new Color(0, 0, 0, 0));
        jPanel.add(jLabel1);

        JLabel jLabel2;
        if (user.getNickname() == null) jLabel2 = new JLabel("未设置您的昵称");
        else jLabel2 = new JLabel("昵称     " + user.getNickname());
        jLabel2.setFont(new Font("black", Font.BOLD, 12));
        jLabel2.setPreferredSize(new Dimension(160, 25));
        jLabel2.setBackground(new Color(0, 0, 0, 0));
        jPanel.add(jLabel2);

        JLabel jLabel3;
        switch (user.getSex()) {
            case 1:
                jLabel3 = new JLabel("您是条汉子");
                break;
            case 2:
                jLabel3 = new JLabel("您是个妹子");
                break;
            default:
                jLabel3 = new JLabel("性别不详");
        }
        jLabel3.setFont(new Font("black", Font.BOLD, 12));
        jLabel3.setPreferredSize(new Dimension(160, 25));
        jLabel3.setBackground(new Color(0, 0, 0, 0));
        jPanel.add(jLabel3);
        String toolTipText = "点我修改信息哟~～(^з^)-☆";
        iconLabel.setToolTipText(toolTipText);
        var listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MainUI.this.dispose();
                MainUI.this.leave.dispose();
                new userInfoEditor();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        iconLabel.addMouseListener(listener);
        jPanel.setBackground(new Color(255, 255, 255, 20));
        userMessage.add(jPanel, 0);
        addMoveListener(userMessage);
    }

    private void setFriendsAndGroups() {
        friendsAndGroups.setBounds(0, 250, 400, 620);
        friendsAndGroups.setPreferredSize(new Dimension(400, 600));
        jScrollPane = new JScrollPane(friendsAndGroups);
        jScrollPane.setBounds(0, 250, 400, 620);

        ArrayList<User> users = getOnlineUsers(classes);
        var classes = getClasses();
        for (int t = 0; t < classes.length; t++) {
            buttonPanel buttonPanel = new buttonPanel(user, classes[t], 380);
            friendsAndGroups.add(buttonPanel);
        }
        for (User value : users) {
            buttonPanel buttonPanel = new buttonPanel(user, value, 380);
            friendsAndGroups.add(buttonPanel);
        }
        addMoveListener(friendsAndGroups);
        addMoveListener(jScrollPane);
    }

    private void setLastPanel() {
        lastPanel.setBounds(0, 870, 400, 30);
        ImageIcon list = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "checkmession.jpg");
        ImageIcon music = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "music.jpg");
        ImageIcon video = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "video.jpg");
        ImageIcon game = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "game.jpg");
        System.out.println(game.getIconWidth() + "" + game.getIconHeight());
        lastPanel.add(getSetButton(list));
        lastPanel.add(getSetButton(music));
        lastPanel.add(getSetButton(game));
        lastPanel.add(getSetButton(video));
        addMoveListener(lastPanel);
    }


    private static MyNoBackgroundButton getSetButton(ImageIcon imageIcon) {
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        var a = new MyNoBackgroundButton(imageIcon);
        a.setPreferredSize(new Dimension(25, 25));
        return a;
    }

    private void addItems() {
        container.add(backGround);
        container.add(userMessage, 0);
        container.add(jScrollPane);
        container.add(lastPanel);
    }

    public MainUI(int userID) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        userIcon = new ImageIcon(DataGetters.downloadFile(user.getAvatar()).getPath());
        setBasic();
        setBackGround();
        setFriendsAndGroups();
        setUserMessage();
        setLastPanel();
        addItems();
        setLocation(500, 100);
        leave.setLocation();
        WindowManager.add(this, 0);
        setVisible(true);
    }

    @Override
    public void dispose() {
        WindowManager.remove(0);
        this.leave.dispose();
        super.dispose();
    }

    @Override
    public void setVisible(boolean b) {
        if (!b) {
            WindowManager.remove(0);
            this.leave.dispose();
        }
        super.setVisible(b);
    }

    private void addMoveListener(JComponent jComponent) {
        var p = new plug(this, point);
        p.setLeave(leave);
        jComponent.addMouseListener(new plug(this, point));
        jComponent.addMouseMotionListener(p);
    }


    public static void main(String[] args) {
        new MainUI(0);
    }
}
class leave extends JFrame implements ActionListener, MouseListener {
    private JFrame relate;
    private JPanel onShown = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private int width, height;
    private MyNoBackgroundButton close, hide, openedMail, changeBackground, myzoom;

    leave(JFrame relate, int height) {
        width = relate.getWidth();//same width as the main frame
        this.height = height;
        this.setSize(width, this.height);
        onShown.setSize(new Dimension(width, this.height));
        onShown.setBackground(new Color(255, 255, 255, 0));
        JPanel jPanel = (JPanel) this.getContentPane();
        jPanel.setOpaque(false);
        setBasic(relate);
        System.out.println(this.getSize());
        setVisible(true);
    }

    void setLocation() {
        int x = relate.getX() + relate.getWidth() - width;
        int y = relate.getY() - height;
        this.setLocation(x, y);
    }

    private void setBasic(JFrame relate) {
        this.relate = relate;
        this.getContentPane().setLayout(null);
        setLocation();
        onShown.setLocation(0, 0);
        ImageIcon close = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "closeIcon.jpg");
        close.setImage(close.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.close = new MyNoBackgroundButton(close);
        this.close.setPreferredSize(new Dimension(25, 25));
        ImageIcon hide = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "hide.jpg");
        hide.setImage(hide.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.hide = new MyNoBackgroundButton(hide);
        this.hide.setPreferredSize(new Dimension(25, 25));
        ImageIcon openedMail = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openedMail.jpg");
        openedMail.setImage(openedMail.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.openedMail = new MyNoBackgroundButton(openedMail);
        this.openedMail.setPreferredSize(new Dimension(25, 25));
        ImageIcon myzoom = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "myzoom.jpg");
        myzoom.setImage(myzoom.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.myzoom = new MyNoBackgroundButton(myzoom);
        this.myzoom.setPreferredSize(new Dimension(25, 25));
        ImageIcon changeBackground = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "changeBackground.jpg");
        changeBackground.setImage(changeBackground.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        this.changeBackground = new MyNoBackgroundButton(changeBackground);
        this.changeBackground.setPreferredSize(new Dimension(25, 25));
        onShown.add(this.changeBackground);
        onShown.add(this.openedMail);
        onShown.add(this.myzoom);
        onShown.add(this.hide);

        onShown.add(this.close);

        this.getContentPane().add(onShown);
        setButtons();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);
        setUndecorated(true);

    }

    private void setButtons() {
        close.addActionListener(e -> {
            this.dispose();
            this.relate.dispose();
            Sys.exit(0);
        });
        hide.addActionListener(e -> {
            this.setExtendedState(JFrame.ICONIFIED);
            this.relate.setExtendedState(JFrame.ICONIFIED);
        });
        openedMail.addActionListener(e -> {
        });
        changeBackground.addActionListener(e -> {
        });
        myzoom.addActionListener(e -> {
            this.dispose();
            this.relate.dispose();
            new JoinClassPane().setVisible(true);
        });
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
//        relate.dispose();
//        this.dispose();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
class plug implements MouseListener, MouseMotionListener {
    private JFrame about;
    private Point point;
    private leave leave;

    plug(JFrame about, Point point) {
        this.about = about;
        this.point = point;
    }

    void setLeave(leave leave) {
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

    public static void main(String[] args) {
        new MainUI(0);
    }
}