package study_swing;

import javax.swing.*;

public class MyLabelLikeAera extends JTextArea {
    public MyLabelLikeAera(String message){
        super(message);
        this.setEditable(false);
        this.setLineWrap(true);
    }
}
