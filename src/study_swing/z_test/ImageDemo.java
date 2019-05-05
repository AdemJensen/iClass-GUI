package study_swing.z_test;
import java.awt.*;
import java.io.File;
import javax.swing.*;
public class  ImageDemo
{
    public ImageDemo(){
        //加载图片
        ImageIcon icon=new ImageIcon("SourcePackage" + File.separator + "Background" + File.separator + "RegisterBackGround.jpg");
        //Image im=new Image(icon);
        //将图片放入label中" + File.separator + "
        JLabel label=new JLabel(icon);
        //设置label的大小
        label.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        JFrame frame=new JFrame();
        //获取窗口的第二层，将label放入
        frame.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
        //获取frame的顶层容器,并设置为透明
        JPanel j=(JPanel)frame.getContentPane();
        j.setOpaque(false);
        JPanel panel=new JPanel();
        JTextField jta=new JTextField(10);
        //JTextArea jta=new JTextArea(10,60);
        ImageIcon imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "AnnouncementList.jpg");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JButton jb=new JButton(imageIcon);
        jb.setBackground(new Color(0, 0, 0));
        jb.setOpaque(false);
        JButton jb2=new JButton("重置");
        panel.add(jta);
        panel.add(jb);
        panel.add(jb2);
        //必须设置为透明的。否则看不到图片
        panel.setOpaque(false);
        frame.add(panel);
        frame.setSize(icon.getIconWidth(),icon.getIconHeight());
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
    public static void main(String[] args)
    {
        new ImageDemo();
    }
}