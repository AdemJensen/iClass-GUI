package study_swing.z_test;

import javax.swing.*;
import java.util.Random;
import java.awt.*;
public class Main extends JFrame{
    int Wide, Height;
    boolean if_Visible = true;//as default
    boolean if_Resziable = false;//as default
    public Main(int wide, int height){
        super("TITLE");
        System.out.println("successfully created!");
        Wide =wide;
        Height = height;
        this.setSize(Wide, Height);
        this.setVisible(if_Visible);
        java.awt.Container container = this.getContentPane();
        container.setBackground(Color.GRAY);
        super.setVisible(if_Visible);
        super.setResizable(if_Resziable);
    }
    @Override
    public void setVisible(boolean b) {
        if_Visible = b;
        super.setVisible(b);
    }
    public boolean Visicle_check(){
        return if_Visible;
    }
    public static void main(String[] args) {
        /*
        System.out.println("hello swing!");
        JFrame jFrame = new JFrame("TITLE");
        jFrame.setSize(1000, 1000);
        jFrame.setVisible(true);
        jFrame.setResizable(Random_boolean());
        */
        Main main = new Main(900, 900);
        TextField textField = new TextField("Success!", 3);
        main.add(textField);

        JButton jButton = new JButton("A NEW BUTTON!");
        jButton.setVisible(true);
        jButton.setBackground(Color.getHSBColor(0.1f, 0.2f, 0.3f));
        main.add(jButton);

        java.awt.Container container = main.getContentPane();
        Button buttons[] = new Button[9];
        container.setBackground(Color.BLUE);
        container.setLayout(new GridLayout(3, 4));
        for (int t = 0; t < 9; t++){
            buttons[t] = Build(t);
            container.add(buttons[t]);
        }
    }
    private static Button Build(int t){
        t++;
        Button award_button = new Button("Wait");
        if (boolean_for_play() && Random_boolean()){
            award_button.setLabel("A Golden Ship Has Been Built!" + "Button " + t);
        }else if (Random_boolean() && Random_boolean()) award_button.setLabel("A Blue Ship Has Been Built!"  + "Button " + t);
        else award_button.setLabel("A White Ship has Been Built!" + "Button " + t);
        return award_button;
    }

    private static boolean Random_boolean(){
        double x = new Random().nextDouble();
        return x > 0.5;
    }

    private static boolean boolean_for_play(){
       return new Random().nextDouble() <= 0.01;
    }
}
class Mycash_TextFiled extends TextField{
    private int mycash;
    private double cash_all;
    Mycash_TextFiled(double cash_all){
        this.cash_all = cash_all;
        mycash = (int)cash_all;
    }

    public int getYuan(){
        return mycash;
    }

    public void charge(String yuan){
        try {
            cash_all += Double.parseDouble(yuan);
            mycash = (int) cash_all;
        }catch (Exception e){
            System.out.println("Illegal input value! Please Check!");
        }
    }
}
class Check_Button extends Button{
    private String Default_label;
    boolean is_charge;
    Check_Button(String s){
        super();
        Default_label = s;
        this.setLabel(Default_label);
    }

    public void Charge_check(){
        is_charge = true;
    }

    public void set_Label(){
        Charge_check();
        if (is_charge) this.setLabel("Successfully charged!");
        else this.setLabel("Uncleared charge, try again!");
    }
}
