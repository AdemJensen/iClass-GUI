package study_swing.Chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeLable extends JLabel implements ActionListener {
    // 一个显示时间的JLabel
    private Timer timer;

    TimeLable() {
        super();
        //设置Timer定时器，并启动
        timer = new Timer(500, this);
        timer.start();
        setVisible(true);
    }
    /**
     * 执行Timer要执行的部分，
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        Date date = new Date();
        this.setText(format.format(date));
    }
}