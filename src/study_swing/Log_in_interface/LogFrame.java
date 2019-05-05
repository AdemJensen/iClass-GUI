package study_swing.Log_in_interface;

import study_swing.MainUI.MainUI;
import study_swing.MyNoBackgroundButton;
import study_swing.smallToolTip;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.auth.AuthManager;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LogFrame extends JFrame {
    private MyNoBackgroundButton check = new MyNoBackgroundButton("确认");
    private MyNoBackgroundButton clear = new MyNoBackgroundButton("清空");
    private MyNoBackgroundButton register = new MyNoBackgroundButton("用户注册");
    protected Container container = this.getContentPane();
    private JTextField InputUserName = new JTextField(10);
    private JPasswordField InputPassword = new JPasswordField(10);
    private JLabel username = new JLabel("用户名");
    private JLabel password = new JLabel("密码  ");
    private JPanel B_panel = new JPanel();
    private ImageIcon imageIcon;
    private JLabel background = new JLabel();
    private boolean waiting;

    //protected JPanel C_panel = new JPanel(new GridLayout(, 1));
    private void setBasic() {
        imageIcon = new ImageIcon("SourcePackage" + File.separator + "Background" + File.separator + "LogInBackground.jpg");
        while (imageIcon.getIconWidth() < 600) {
            int a = (int) (imageIcon.getIconWidth() * 1.2);
            int b = (int) (imageIcon.getIconHeight() * 1.2);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(a, b, Image.SCALE_SMOOTH));
        }
        this.setVisible(true);
        this.setResizable(true);
        JPanel jPanel = (JPanel) this.getContentPane();
        jPanel.setOpaque(false);
        container.setLayout(null);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

    }

    private void setBackground() {
        background.setIcon(imageIcon);
        background.setOpaque(false);
        background.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    private void setContainer() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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

        new BoxLayout(B_panel, BoxLayout.X_AXIS);

        B_panel.setSize(180, 50);
        B_panel.setLocation(60, 200);
        B_panel.add(check);
        B_panel.add(clear);
        B_panel.setOpaque(false);
        container.add(B_panel);

        JPanel regPanel = new JPanel();
        register.setFont(new Font("black", Font.PLAIN, 10));
        register.setSize(80, 30);
        regPanel.add(register);
        regPanel.setSize(80, 30);
        regPanel.setOpaque(false);
        regPanel.setLocation(-10, 300);
        container.add(regPanel);
    }

    private void SetButtonCheck() {
        check.addActionListener(Event -> {
            waiting = true;
            check.setText("登陆中");
            check.repaint();
            //Global.guiAdapter.executeCmd("login", "Normal", InputUserName.getText(), String.valueOf(InputPassword.getPassword()));
            String userNameText = InputUserName.getText();
            String password = String.valueOf(InputPassword.getPassword());
            var result = LoginAdapter.login(userNameText, password);
            LoginResult(result);
        });
    }

    private void LoginResult(String result) {
        if (!waiting) return;
        waiting = false;
        if (result.equals("OK")) {
            new MainUI(AuthManager.getUser().getId());
            this.dispose();
            WindowManager.remove(1);
            new smallToolTip("锵！", "登录成功啦！快和小伙伴们快乐吧！");
            return;
        } else if (result.equals("Username or password incorrect")) {
            InputPassword.setText("");
            InputUserName.setText("");
            new smallToolTip("唔姆！", "用户名密码好像错了呢...");
        } else {
            new smallToolTip("噔噔咚！", result);
        }
        check.setText("确认");
        check.repaint();
    }

    private void SetButtonClear() {
        clear.addActionListener(Event -> {
            InputUserName.setText("");
            InputPassword.setText("");
        });
    }

    private void setregister() {
        register.addActionListener(Event -> {
            if (WindowManager.isIdAvail(2)) {
                WindowManager.add(new RegisterFrame(), 2);
                WindowManager.remove(1);
                this.dispose();
            }
        });
    }

    private void setItems() {
        setContainer();
        SetButtonCheck();
        SetButtonClear();
        setregister();
    }

    private void addItems() {
        this.add(background);
    }

    LogFrame() {
        super("iClass 登陆");
        setBasic();
        setBackground();
        setItems();
        addItems();
        this.setBounds(200, 50, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new LogFrame();
    }
}