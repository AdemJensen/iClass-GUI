package study_swing.Chat;

import study_swing.DataGetters;
import study_swing.Document.DocumentInterface;
import study_swing.MyNoBackgroundButton;
import study_swing.Vote.voteList;
import study_swing.announecment.listOfAnnouncement;
import study_swing.history;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.auth.ClassInfo;
import top.chorg.kernel.communication.api.auth.User;
import top.chorg.kernel.communication.auth.AuthManager;
import top.chorg.support.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.stream.IntStream;

public class groupChatFrame extends singleChatFrame {
    //TODO set the target as a class
    private ClassInfo classInfo;
    private int[] othersId;
    private User[] otherUsers;
    private String[] otherNames;
    private ImageIcon[] imageIcons;



    private ImageIcon[] getImageIcons(){
        ImageIcon[] res = new ImageIcon[otherUsers.length];
        for (int t = 0; t < otherUsers.length; t++){
            res[t] = new ImageIcon(DataGetters.downloadFile(otherUsers[t].getAvatar()).getPath());
            if (res[t].getIconHeight() == -1) res[t] = new ImageIcon("SourcePackage" + File.separator+ "Icon" + File.separator+ "defaultUserIcon.png");
        }
        return res;
    }

    private User[] getOtherUsers() {
        User[] res = new User[othersId.length];
        for (int t= 0; t < othersId.length; t++){
            res[t] = DataGetters.getUserInfo(othersId[t]);
        }
        return res;
    }

    private String[] getOtherNames() {
        String[] res = new String[othersId.length];
        for (int t = 0; t < othersId.length; t++){
            res[t] = DataGetters.getUserInfo(othersId[t]).getUsername();
        }
        return res;
    }

    void setPlusItem() {
        plusItem.setLayout(new FlowLayout(FlowLayout.LEFT));
        plusItem.setPreferredSize(new Dimension(600, 30));
        String[] strings = new String[]{"投票", "公告", "文件", "相册", "活动", "设置"};
        IntStream.range(0, strings.length).forEach(t -> {
            MyNoBackgroundButton myNoBackgroundButton = new MyNoBackgroundButton(strings[t]);
            myNoBackgroundButton.setBorder(BorderFactory.createEtchedBorder());
            if (t == 0) {
                myNoBackgroundButton.addActionListener(e -> new voteList(myNoBackgroundButton, classInfo.id));
            }
            if (t == 1) {
                myNoBackgroundButton.addActionListener(e -> new listOfAnnouncement(myNoBackgroundButton, targetId));
            }
            if (t == 2) {
                myNoBackgroundButton.addActionListener(e -> new DocumentInterface(classInfo.id));
            }
            if (t == 5) {
                myNoBackgroundButton.addActionListener(e -> new ConfigPane(classInfo.id).setVisible(true));
            }
            myNoBackgroundButton.setPreferredSize(new Dimension(50, 28));
            plusItem.add(myNoBackgroundButton);
        });
    }

    private int[] getOthersId(){
        if (classInfo != null) {
            return classInfo.classmates;
        }else return null;
    }
    public groupChatFrame(int userID, ClassInfo classInfo) {
        super(userID, classInfo.id, 1);
        WindowManager.add(this, 1000 + classInfo.id);
        this.target = getUsersFromClassIds(classInfo.classmates);
        this.targetName = classInfo.name;
        this.targetId = classInfo.id;
        this.iconOfTarget = new ImageIcon(DataGetters.downloadFile(classInfo.avatar).getPath());
        this.classInfo = classInfo;
        othersId = getOthersId();
        otherUsers = getOtherUsers();
        otherNames = getOtherNames();
        imageIcons = getImageIcons();
        setTitle("在" + targetName + "中聊天");
        if (DataGetters.getLevel(classInfo.id) > 0) {
            var button = new MyNoBackgroundButton("历史记录");
            button.setBorder(BorderFactory.createEtchedBorder());
            button.addActionListener(e -> new history(
                    classInfo.name + "班级历史记录", DataGetters.getLog(classInfo.id))
            );
            button.setPreferredSize(new Dimension(100, 28));
            plusItem.add(button);
        }
        this.setVisible(true);
    }
}
