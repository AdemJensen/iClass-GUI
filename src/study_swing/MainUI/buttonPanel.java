package study_swing.MainUI;

import study_swing.Chat.*;
import study_swing.DataGetters;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.auth.ClassInfo;
import top.chorg.kernel.communication.api.auth.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class buttonPanel extends JPanel implements MouseListener {
    private int type = 1;
    private User target, user;
    private ClassInfo classInfo;

    private void setBasic(int width) {
        this.addMouseListener(this);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(new Color(255, 255, 255));
        this.setPreferredSize(new Dimension(width, 48));
    }

    buttonPanel(User user, User target, int width) {
        this.setToolTipText("阁下要和谁聊天呢~～(^з^)-☆");
        this.user = user;
        type = 2;
        this.target = target;
        setBasic(width);
        ImageIcon imageIcon = new ImageIcon(DataGetters.downloadFile(target.getAvatar()).getPath());
        if (imageIcon.getIconHeight() == -1)
            imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "defaultUserIcon.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        JLabel iconLabel = new JLabel(imageIcon);
        iconLabel.setPreferredSize(new Dimension(40, 40));
        this.add(iconLabel);
        JLabel nameLabel = new JLabel(target.getUsername());
        nameLabel.setFont(new Font("black", Font.PLAIN, 15));
        this.add(nameLabel);
    }

    buttonPanel(User user, ClassInfo classInfo, int width) {
        this.setToolTipText("这是一个群聊哟~(๑•̀ㅂ•́)و✧");
        this.user = user;
        this.classInfo = classInfo;
        setBasic(width);
        ImageIcon imageIcon = new ImageIcon(DataGetters.downloadFile(classInfo.avatar).getPath());
        if (imageIcon.getIconHeight() == -1)
            imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "defaultUserIcon.png");
        JLabel iconLabel = new JLabel(imageIcon);
        iconLabel.setPreferredSize(new Dimension(40, 40));
        this.add(iconLabel);
        JLabel nameLabel = new JLabel(classInfo.name);
        nameLabel.setFont(new Font("black", Font.PLAIN, 15));
        this.add(nameLabel);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(new Color(205, 205, 205));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(new Color(255, 255, 255));
        if (type == 2) {
            if (!WindowManager.containsKey(100 + target.getId())) {
                //System.out.println(100 + target.getId());
                new singleChatFrame(user.getId(), target.getId());
            }
        }
        else {
            if (!WindowManager.containsKey(1000 + classInfo.id)) {
                //System.out.println(1000 + classInfo.id);
                new groupChatFrame(user.getId(), classInfo);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
