package study_swing.z_test;


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
class MyCheckBox
{

    public MyCheckBox()
    {
        JPanel pan = new JPanel();
        pan.setBorder(BorderFactory.createTitledBorder("多选："));
        pan.setLayout(new GridLayout(1, 3)); // 设置组件的排版
        // 定义一个复选框
        JCheckBox jcb1 = new JCheckBox("复选框1");
        pan.add(jcb1); // 增加组件
        // 定义一个复选框
        JCheckBox jcb2 = new JCheckBox("复选框2");
        pan.add(jcb2); // 增加组件
        // 定义一个复选框
        JCheckBox jcb3 = new JCheckBox("复选框3");
        pan.add(jcb3); // 增加组件
        // 得到窗体容器
        // 窗体
        // 定义窗体
        JFrame frame = new JFrame("使用复选框");
        Container cont = frame.getContentPane();
        cont.add(pan); // 将面板加入到容器之中
        frame.setSize(330, 80);
        frame.setVisible(true); // 设置可显示
        // 监听窗体关闭事件
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent arg)
            {
                System.exit(1);
            }
        });
    }
}

public class JCheckBoxDemo01
{
    public static void main(String args[])
    {
        new MyCheckBox();
    }
}
