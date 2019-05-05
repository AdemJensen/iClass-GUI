package study_swing.MainUI;

import study_swing.DataGetters;
import study_swing.smallToolTip;
import top.chorg.kernel.communication.auth.AuthManager;

import javax.swing.*;
import java.awt.*;

public class JoinClassPane extends JFrame {
    JLabel label = new JLabel("请阁下输入班级编号：  ");
    JTextField id = new JTextField();
    JButton button = new JButton("加入");
    private boolean newMainAlready = false;

    public JoinClassPane() {
        super("加入新的大家庭");
        this.setBounds(500, 250, 450, 60);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(label);
        this.add(id);
        id.setPreferredSize(new Dimension(150, 20));
        this.add(button);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        button.addActionListener(e -> {
            int classId;
            try {
                classId = Integer.parseInt(id.getText());
            } catch(NumberFormatException f) {
                new smallToolTip("唔姆！", "阁下，这好像不是数字哦？");
                return;
            }
            String result = DataGetters.joinClass(classId);
            if (result.equals("OK")) {
                this.dispose();
                new smallToolTip("乌拉！", "恭喜阁下！成功加入了一个新班级！");
            } else {
                new smallToolTip("唔！", "阁下，对不起...加入班级失败了...(" + result + ")");
            }
        });
    }

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
