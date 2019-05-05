package study_swing.Manager;
/**
 * this container includes two che buttons and is value
 * using when users needs check
 *but now there is sth wrong
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class My_check extends JFrame{
    private ActionListener myListener;
    private JButton button_1;
    private JButton button_2;
    private TextField textField;
    private JPanel Panel_ofButton;
    private JPanel all_seen;
    private java.awt.Container myContainer;
    private JButton[] buttons = new JButton[3];
    private boolean aBoolean;
    private boolean action_judge;
    My_check(String b1_mes, String b2_mes, String text, String title,int Fwv, int Fhv){
        //basic operation when construct
        //F*v means the value of the frame
        Panel_ofButton = new JPanel();
        textField = new TextField();
        button_1 = new JButton();
        buttons[0] = button_1;
        button_2 = new JButton();
        buttons[1] = button_2;
        JButton unseen = new JButton();
        buttons[2] = unseen;
        myContainer = getContentPane();
        all_seen = new JPanel();
        action_judge = false;
        
        //set message
        setFrameSize(Fwv, Fhv);
        textField.setText(text);
        this.setTitle(title);
        button_1.setText("Yes");
        button_1.setSize(50, 20);
        button_1.setToolTipText(b1_mes);
        button_2.setText("No");
        button_2.setSize(50, 20);
        button_2.setToolTipText(b2_mes);
        unseen.setVisible(false);
        textField.setVisible(true);
        textField.setEnabled(false);

        
        //set views
        Panel_ofButton.setLayout(new FlowLayout());
        for (int t = 0; t < 3; t++){
            Panel_ofButton.add(buttons[t]);
        }
        all_seen.setLayout(new BorderLayout());
        all_seen.add(Panel_ofButton, BorderLayout.SOUTH);
        all_seen.add(textField, BorderLayout.NORTH);
        myContainer.add(all_seen);
        this.setVisible(true);

    }
    public void setFrameSize(int wide, int height){
        this.setSize(wide, height);
    }
    public void setAction(){
        //set event and action
        button_1.addActionListener(actionEvent -> {
            System.out.println("01");
            aBoolean = true;
            action_judge = true;
            myListener.actionPerformed(null);
         ///   this.dispose();
        });

        button_2.addActionListener(actionEvent -> {
            System.out.println("02");
            aBoolean = false;
            action_judge = true;
            myListener.actionPerformed(null);
        ///    this.dispose();
        });
    }int is = 0;
    public void donothing() {
        is++;
    }

    public void setMyListener(ActionListener myListener) {
        this.myListener = myListener;
    }

    public boolean getCheckValue(){
        return aBoolean;
    }
    public void setButtonsize(int wide, int height){
        for (int t = 0 ;t < buttons.length; t++){
            buttons[t].setSize(wide, height);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()-> {
            My_check my_check = new My_check("他是范哥", "他不是范哥", "Do you want to 他  to be 范哥吗?", "是范哥吗?", 1000, 300);
            my_check.setButtonsize(100, 20);
            my_check.setAction();

            System.out.println("SS");
            System.out.println(my_check.getCheckValue());
        });

        System.out.print("");
    }
}
class D_list{}
class D_Link{
    /**
     * this class trys to send a set of message to
     * clear the main message of the documents
     *
     * */
}
