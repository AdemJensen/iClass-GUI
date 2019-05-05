package study_swing.Chat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//帮助菜单功能的事项类
class Help extends JFrame {
    private DrawPad drawpad;

    Help(DrawPad dp) {
        drawpad = dp;
    }

    void MainHeip() {
        JOptionPane.showMessageDialog(this, "小小绘图板帮助文档！", "小小绘图板", JOptionPane.WARNING_MESSAGE);
    }

    void AboutBook() {
        JOptionPane.showMessageDialog(drawpad, "小小绘图板" + "\n" + "    版本: 1.1.4" + "\n"
                + "    原作者:  刘  军  光" + "\n" + "\timproved by teriri \n "
                + "    时间:  2009/12/13\n    at 2019/4/23", "小小绘图板"
                + "", JOptionPane.WARNING_MESSAGE);
    }
}
