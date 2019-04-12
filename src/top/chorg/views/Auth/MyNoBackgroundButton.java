package top.chorg.views.Auth;

import javax.swing.*;
import java.awt.*;

public class MyNoBackgroundButton extends JButton {
    public MyNoBackgroundButton(){
        setBasic();
    }
    public MyNoBackgroundButton(String mes){
        super(mes);
        setBasic();
    }
    public MyNoBackgroundButton(Icon icon){
        super(icon);
        setBasic();
    }
    private void setBasic(){
        this.setBackground(new Color(0, 0, 0));
        this.setOpaque(false);
        this.setBorder(null);
    }
}