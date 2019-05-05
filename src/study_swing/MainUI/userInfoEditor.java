package study_swing.MainUI;

import study_swing.DataGetters;
import study_swing.smallToolTip;
import top.chorg.kernel.communication.api.auth.AuthInfo;
import top.chorg.kernel.communication.auth.AuthManager;
import top.chorg.support.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;

public class userInfoEditor extends JFrame {
    private static  String path = DataGetters.downloadFile(AuthManager.getUser().getAvatar()).getPath();
    boolean avatarChanged = false;

    public userInfoEditor(){
        super("编辑您的个人信息");
        setSize(600, 720);
        setBounds(500, 200, 600, 720);
        Container container = this.getContentPane();
        container.setPreferredSize(new Dimension(620, 600));
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        JRadioButton jRadioButton0 = new JRadioButton("保密", AuthManager.getUser().getSex() == 0);
        JRadioButton jRadioButton = new JRadioButton("汉子", AuthManager.getUser().getSex() == 1);
        JRadioButton jRadioButton1 = new JRadioButton("妹子", AuthManager.getUser().getSex() == 2);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(jRadioButton0);
        buttonGroup.add(jRadioButton);
        buttonGroup.add(jRadioButton1);
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel.setPreferredSize(new Dimension(600, 40));
        JLabel sex = new JLabel("请设置您的性别:");
        sex.setPreferredSize(new Dimension(128, 30));
        jPanel.add(sex);
        jPanel.add(jRadioButton0);
        jPanel.add(jRadioButton);
        jPanel.add(jRadioButton1);
        container.add(jPanel);
        JPanel grade = new JPanel(new FlowLayout(FlowLayout.LEFT));
        grade.setPreferredSize(new Dimension(600, 40));
        JLabel jLabel = new JLabel("请设置您的年级:");
        jLabel.setPreferredSize(new Dimension(128, 30));
        ButtonGroup buttonGroup1 = new ButtonGroup();
        int thisYear = new Date().getYear();
        JRadioButton jRadioButton2 = new JRadioButton("大一", AuthManager.getUser().getGrade() - thisYear == 0);
        JRadioButton jRadioButton3 = new JRadioButton("大二", AuthManager.getUser().getGrade() - thisYear == -1);
        JRadioButton jRadioButton4 = new JRadioButton("大三", AuthManager.getUser().getGrade() - thisYear == -2);
        JRadioButton jRadioButton5 = new JRadioButton("大四", AuthManager.getUser().getGrade() - thisYear == -3);
        JRadioButton jRadioButton6 = new JRadioButton("大五", AuthManager.getUser().getGrade() - thisYear == -4);
        JRadioButton jRadioButton7 = new JRadioButton("毕业", AuthManager.getUser().getGrade() - thisYear < -4);
        JRadioButton[] jRadioButtons = new JRadioButton[6];
        jRadioButtons[0] = jRadioButton2;
        jRadioButtons[1] = jRadioButton3;
        jRadioButtons[2] = jRadioButton4;
        jRadioButtons[3] = jRadioButton5;
        jRadioButtons[4] = jRadioButton6;
        jRadioButtons[5] = jRadioButton7;

        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
        buttonGroup1.add(jRadioButton4);
        buttonGroup1.add(jRadioButton5);
        buttonGroup1.add(jRadioButton6);
        buttonGroup1.add(jRadioButton7);
        jRadioButton2.setPreferredSize(new Dimension(65, 30));
        jRadioButton3.setPreferredSize(new Dimension(65, 30));
        jRadioButton4.setPreferredSize(new Dimension(65, 30));
        jRadioButton5.setPreferredSize(new Dimension(65, 30));
        jRadioButton6.setPreferredSize(new Dimension(65, 30));
        jRadioButton7.setPreferredSize(new Dimension(65, 30));
        grade.add(jLabel);
        grade.add(jRadioButton2);
        grade.add(jRadioButton3);
        grade.add(jRadioButton4);
        grade.add(jRadioButton5);
        grade.add(jRadioButton6);
        grade.add(jRadioButton7);

        container.add(grade);
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel setIcon = new JLabel("请设置您的用户头像:");
        ImageIcon imageIcon = new ImageIcon(path);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH));
        JLabel icon = new JLabel(imageIcon);
        icon.setPreferredSize(new Dimension(128, 128));
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try{
                    JFileChooser jFileChooser = new JFileChooser();
                    jFileChooser.showOpenDialog(null);
                    File file = jFileChooser.getSelectedFile();
                    ImageIcon get = new ImageIcon(file.getPath());
                    if (get.getIconHeight() == -1){
                        get = imageIcon;
                    }else {
                        avatarChanged = true;
                        path = file.getPath();
                    }
                    get.setImage(get.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH));
                    icon.setIcon(get);
                }catch (NullPointerException ignore){}
            }
        });
        jPanel1.add(setIcon);
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(50, 100));
        jPanel1.add(jPanel2);
        jPanel1.add(icon);
        jPanel1.setPreferredSize(new Dimension(500, 130));
        container.add(jPanel1);
        String[] mes = { "姓名" ,"昵称", "电子邮箱", "电话" };
        packedSetting[] all = new packedSetting[4];
        for (int t = 0; t < 4; t++){
            all[t] = new packedSetting("请设置您的" + mes[t] + ":");
            container.add(all[t]);
        }
        all[0].message.setText(AuthManager.getUser().getRealName() == null ? "" : AuthManager.getUser().getRealName());
        all[1].message.setText(AuthManager.getUser().getNickname() == null ? "" : AuthManager.getUser().getNickname());
        all[2].message.setText(AuthManager.getUser().getEmail() == null ? "" : AuthManager.getUser().getEmail());
        all[3].message.setText(AuthManager.getUser().getPhone() == null ? "" : AuthManager.getUser().getPhone());
        JTextField birthdayField = new JTextField(AuthManager.getUser().getBirthday() == null ? "单击以修改日期" : AuthManager.getUser().getBirthday().toString());

        birthdayField.setEditable(false);
        JPanel jPanelB = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel birthLabel = new JLabel("请设置您的生日:");
        birthLabel.setPreferredSize(new Dimension(128, 30));
        DateChooser birthdayChooser = DateChooser.getInstance("yyyy-MM-dd");
        birthdayChooser.register(birthdayField);
        birthdayField.setToolTipText("单击以修改日期");
        //birthdayField.setPreferredSize(new Dimension(600, 40));
        jPanelB.add(birthLabel);
        jPanelB.add(birthdayField);
        container.add(jPanelB);
        JButton check = new JButton("确认");
        JButton cancel = new JButton("取消");
        check.addActionListener(e ->{
            int getSex;
            if (jRadioButton0.isSelected()) getSex = 0;
            else if (jRadioButton.isSelected()) getSex = 1;
            else getSex = 2;
            int getGrade = thisYear;
            for (int t = 0; t < 5; t++){
                if (jRadioButtons[t].isSelected()) getGrade -= t;
            }
            if (jRadioButtons[5].isSelected()) getGrade = AuthManager.getUser().getGrade();
            int iconID;
            if (avatarChanged) iconID = DataGetters.uploadFile(path, 0, 0);
            else iconID = AuthManager.getUser().getAvatar();
            Date date;
            try {
                date = new Date(birthdayChooser.getStrDate());
                if (date.equals(new Date())) date = null;
            } catch (IllegalArgumentException f) {
                date = null;
            }
//
//            int year, month, day;
//            try{
//                year = Integer.parseInt(date.substring(0, 4));
//                month = Integer.parseInt(date.substring(4, 6));
//                day = Integer.parseInt(date.substring(6, 8));
//            }catch (NumberFormatException n){
//                year = 2000;
//                month = 1;
//                day = 1;
//            }
//            String getDate = change(year, 4) + "-" + change(month, 2) + "-" + change(day, 2);
//            Date date1 = new Date(getDate);
            String result = DataGetters.alterUser(getSex, getGrade, iconID,
                    all[0].message.getText().length() > 0 ? all[0].message.getText() : null,
                    all[1].message.getText().length() > 0 ? all[1].message.getText() : null,
                    all[2].message.getText().length() > 0 ? all[2].message.getText() : null,
                    all[3].message.getText().length() > 0 ? all[3].message.getText() : null,
                    date
            );
            if (result.equals("OK")) {
                this.dispose();
                new smallToolTip("锵！", "阁下，你的用户信息已经成功修改啦！");
            } else {
                new smallToolTip("唔姆！", "阁下，修改失败啦...(" + result + ")");
            }
        });
        cancel.addActionListener(e -> dispose());
        JPanel jPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel3.setPreferredSize(new Dimension(600, 40));
        jPanel3.add(check);
        jPanel3.add(cancel);
        container.add(jPanel3);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        pack();
        setVisible(true);

    }
    private static String change(int x, int length){
        StringBuilder t = new StringBuilder(x + "");
        while (t.length() < length) t.insert(0, 0);
        return t.toString();
    }

    boolean newMainAlready = false;

    @Override
    public void dispose() {
        if (!newMainAlready) new MainUI(AuthManager.getUser().getId());
        newMainAlready = true;
        super.dispose();
    }

    @Override
    public void setVisible(boolean b) {
        if (!b && !newMainAlready) {
            newMainAlready = true;
            new MainUI(AuthManager.getUser().getId());
        }
        super.setVisible(b);
    }
}
class packedSetting extends JPanel{
    JLabel tip;
    JTextField message;
    packedSetting(String tip){
        this.tip = new JLabel(tip);
        message = new JTextField();
        message.setColumns(15);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.tip.setPreferredSize(new Dimension(128, 30));
        this.setPreferredSize(new Dimension(600, 40));
        this.add(this.tip);
        this.add(message);
    }
}
