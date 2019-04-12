package top.chorg.views.Auth;

import top.chorg.ForeGuiAdapter;
import top.chorg.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;

public class LoginFrameTest extends JFrame {
    protected MyNoBackgroundButton check = new MyNoBackgroundButton("确认");
    protected MyNoBackgroundButton clear = new MyNoBackgroundButton("清空");
    protected Container container = this.getContentPane();
    protected JTextField InputUserName = new JTextField(10);
    protected JPasswordField InputPassword = new JPasswordField(10);
    protected JLabel username = new JLabel("用户名");
    protected JLabel password = new JLabel("密码  ");
    protected MyNoBackgroundButton Register = new MyNoBackgroundButton("用户注册");
    protected JPanel B_panel = new JPanel();
    protected ImageIcon imageIcon;
    protected JLabel background = new JLabel();
    //protected JPanel C_panel = new JPanel(new GridLayout(, 1));
    private boolean waiting = false;
    private void setBasic(){
        imageIcon  = new ImageIcon("SourcePackage/Background/LogInBackground.jpg");
        while (imageIcon.getIconWidth() < 600){
            int a = (int)(imageIcon.getIconWidth() * 1.2);
            int b = (int)(imageIcon.getIconHeight() * 1.2);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(a, b, Image.SCALE_SMOOTH));
        }
        while (imageIcon.getIconWidth() > 600){
            int a = (int)(imageIcon.getIconWidth() / 1.2);
            int b = (int)(imageIcon.getIconHeight() / 1.2);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(a, b, Image.SCALE_SMOOTH));
        }
        this.setVisible(true);
        this.setResizable(true);
        JPanel jPanel = (JPanel)this.getContentPane();
        jPanel.setOpaque(false);
        container.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    private void setBackground(){
        background.setIcon(imageIcon);
        background.setOpaque(false);
        background.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }
    private void setContainer(){
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.setSize(300, 50);
        userPanel.setLocation(0, 100);
        userPanel.setOpaque(false);
        InputUserName.setSize(250, 150);
        userPanel.add(username);
        userPanel.add(InputUserName);
        container.add(userPanel);
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.setSize(300, 50);
        password.setSize(100, 50);
        InputPassword.setSize(200, 50);
        passwordPanel.add(password);
        passwordPanel.add(InputPassword);
        passwordPanel.setOpaque(false);
        passwordPanel.setLocation(0, 140);
        container.add(passwordPanel);

        B_panel.setLayout(new FlowLayout());
        B_panel.setSize(150, 50);
        B_panel.setLocation(75, 200);
        B_panel.add(check);
        B_panel.add(clear);
        B_panel.setOpaque(false);
        container.add(B_panel);

        JPanel regPanel = new JPanel();
        Register.setFont(new Font("black", Font.PLAIN, 10));
        Register.setSize(80, 30);
        regPanel.add(Register);
        regPanel.setSize(80, 30);
        regPanel.setOpaque(false);
        regPanel.setLocation(-10, 300);
        container.add(regPanel);
    }
    private void SetButtonCheck(){
        check.addActionListener(Event ->{
            waiting = true;
            this.check.setText("正在登陆...");
            this.check.repaint();
            ForeGuiAdapter.executeCmd("login", InputUserName.getText(), String.valueOf(InputPassword.getPassword()));
        });
    }
    private void SetButtonClear(){
        clear.addActionListener(Event ->{
            InputUserName.setText("");
            InputPassword.setText("");
        });
    }
    private void setRegister(){
        Register.addActionListener(Event ->{
            //new RegisterFrame();
        });
    }
    private void setItems(){
        setContainer();
        SetButtonCheck();
        SetButtonClear();
        setRegister();
    }
    private void addItems(){
        this.add(background);
    }

    public LoginFrameTest (){
        super("Login Interface");
        setBasic();
        setBackground();
        setItems();
        addItems();
        this.setBounds(200, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.setResizable(false);
    }

    public void loginResult(String result) {
        if (!waiting) return;
        waiting = false;
        if (result.equals("ok")) {
            CheckFrame checkFrame = new CheckFrame(true);
            checkFrame.setVisible(true);
            //new MainUI();
        } else {
            this.check.setText("确认");
            this.check.repaint();
            CheckFrame checkFrame = new CheckFrame(false);
            checkFrame.setVisible(true);
        }
    }
}
class CheckFrame extends JFrame{
    CheckFrame(boolean b){
        JLabel label;
        if (b) label = new JLabel("Successful logged in!");
        else label = new JLabel("Wrong password!");
        //label.setPreferredSize(new Dimension(100, 50));
        Container container = this.getContentPane();
        container.setLayout(new GridLayout());
        container.add(label);
        this.pack();
        this.setResizable(false);
        this.setBounds(500, 400, 500, 200);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                WindowManager.get(1, LoginFrameTest.class).dispose();
                WindowManager.remove(1);
                dispose();
            }
        });
    }

}
class TestUser {
    private String username;
    private String password;
    final private boolean right = false;
    static private String default_password = "rtldltql";
    static private String default_username = "rtl";
    TestUser(String username, String password){
        this.username = username;
        this.password = password;
    }
    TestUser()
    {
        username = default_username;
        password = default_password;
    }
    public boolean CheckPassword(String username, String password){
        return ((this.password.equals(password) && this.username.equals(username))|| (password.equals(default_password) && username.equals(default_username)));
    }
}