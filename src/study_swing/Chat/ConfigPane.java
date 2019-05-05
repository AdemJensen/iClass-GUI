package study_swing.Chat;

import study_swing.DataGetters;
import study_swing.MainUI.MainUI;
import study_swing.smallToolTip;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.auth.AuthManager;

import javax.swing.*;
import java.awt.*;

public class ConfigPane extends JFrame {
    int classId;
    JButton button = new JButton("退出该班级");

    public ConfigPane(int classId) {
        super("设置");
        this.classId = classId;
        this.setBounds(500, 250, 450, 100);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(button);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        button.addActionListener(e -> {
            String result = DataGetters.exitClass(classId);
            if (result.equals("OK")) {
                if (WindowManager.containsKey(1000 + classId)) {
                    WindowManager.get(1000 + classId, groupChatFrame.class).dispose();
                }
                WindowManager.get(0, MainUI.class).dispose();
                new MainUI(AuthManager.getUser().getId());
                this.dispose();
                new smallToolTip("再见...", "报告阁下，我们，再也不会碰到他们了...");
            } else {
                new smallToolTip("唔！", "阁下，没能离开这个班级...(" + result + ")");
            }
        });
    }

}
