package study_swing.Log_in_interface;

import study_swing.DataGetters;
import study_swing.Dialog_Plug_in;
import study_swing.MainUI.MainUI;
import study_swing.MyNoBackgroundButton;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.auth.AuthManager;
import top.chorg.support.Timer;
import top.chorg.system.Global;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.HashMap;

import static study_swing.Log_in_interface.LoginAdapter.login;

/**
 *this frame is try to create a new user and send is message to rtldl's sever
 * */
public class RegisterFrame extends JFrame {
    protected String username;
    private String password;
    private String repeatPassword;
    protected Container container;
    private JRadioButton mustKnown;
    private MyNoBackgroundButton messageOfMustknow;
    private MyNoBackgroundButton send;
    private MyNoBackgroundButton clear_un;//clear username
    private MyNoBackgroundButton clear_pw;//clear password
    private MyNoBackgroundButton clear_rp;//clear repeat_password
    private MyNoBackgroundButton userIconSelector;
    private JTextField t_user;
    private JPasswordField f_password;
    private JPasswordField r_password;
    private myLabel UsernameLabel;
    private myLabel PasswordLabel;
    private myLabel RepeatPasswordLabel;
    private myLabel userIconLabel;// show the userIcon that been selected
    private ImageIcon imageIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "ClearIcon.jpg");
    private ImageIcon background;
    private ImageIcon userIcon;
    private ImageIcon defaultUserIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "defaultUserIcon.png");
    private String path = "";
    //this icon is used to be the users icon when there the user has not made any choice
    private boolean waiting;

    protected void setBasic() {
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(500, 500, 800, 864);
        this.setLocationRelativeTo(null);
        container = this.getContentPane();
        container.setVisible(true);
        container.setLayout(null);
        // make sure there is no background
        JPanel jPanel = (JPanel) container;
        jPanel.setOpaque(false);
    }

    private void createItems() {
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        defaultUserIcon.setImage(defaultUserIcon.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT));
        send = new MyNoBackgroundButton("注册！");
        t_user = new JTextField();
        f_password = new JPasswordField();
        r_password = new JPasswordField();
        PasswordLabel = new myLabel("设置密码:");
        UsernameLabel = new myLabel("用户名:");
        RepeatPasswordLabel = new myLabel("重复密码:");
        userIconLabel = new myLabel(defaultUserIcon);
        userIconLabel.setToolTipText("如果您不设置的话，这将是您的用户头像");
        clear_un = new MyNoBackgroundButton(imageIcon);
        clear_pw = new MyNoBackgroundButton(imageIcon);
        clear_rp = new MyNoBackgroundButton(imageIcon);
        userIconSelector = new MyNoBackgroundButton("设置头像");
        mustKnown = new JRadioButton("我已阅读");
        messageOfMustknow = new MyNoBackgroundButton("用户须知");
        background = new ImageIcon("SourcePackage" + File.separator + "Background" + File.separator + "RegisterBackGround.jpg");
    }

    protected void setBackground() {
        myLabel bg = new myLabel(background);
        bg.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        this.getLayeredPane().add(bg, Integer.valueOf(Integer.MIN_VALUE));
    }

    private void setUser() {
        //this.setBounds(500, 500, 605, 800);
        UsernameLabel.setBounds(70, 250, 120, 35);
        UsernameLabel.setFont(new Font("black", Font.PLAIN, 18));
        t_user.setBounds(190, 250, 250, 35);
        //clear_un.setBounds(500, 250, 80, 35);
    }

    private void setPassword() {
        PasswordLabel.setBounds(70, 290, 120, 35);
        PasswordLabel.setFont(new Font("black", Font.PLAIN, 18));
        f_password.setBounds(190, 290, 250, 35);
        //clear_pw.setBounds(500, 290, 80, 35);
    }

    private void setRepeatPassword() {
        RepeatPasswordLabel.setBounds(70, 330, 120, 35);
        RepeatPasswordLabel.setFont(new Font("black", Font.PLAIN, 18));
        r_password.setBounds(190, 330, 250, 35);
    }

    private void setButtons() {
        send.setBounds(250, 650, 120, 70);
        send.setFont(new Font("black", 30, 30));
        send.setBorder(null);
        send.setBackground(new Color(200, 200, 200));
        //send.setOpaque(false);
        send.setEnabled(false);
        send.addActionListener(Event -> {
            send.setText("注册中");
            send.repaint();
            username = String.valueOf(t_user.getText());
            password = String.valueOf(f_password.getPassword());
            repeatPassword = String.valueOf(r_password.getPassword());
            if (password.equals(repeatPassword) && !password.equals("")) {
                waiting = true;
                LoginAdapter.onGoingReg = true;
                Global.guiAdapter.executeCmd("register", username, password);
            } else {
                JFrame out;
                myLabel myLabel;
                if (!password.equals("")) {
                    out = new JFrame("警告");
                    myLabel = new myLabel("重复密码错误！");
                } else {
                    out = new JFrame("警告");
                    myLabel = new myLabel("密码为空！");
                }
                out.getContentPane().add(myLabel);
                out.getContentPane().setLayout(null);
                myLabel.setSize(200, 200);
                out.setDefaultCloseOperation(HIDE_ON_CLOSE);
                r_password.setText("");//the password is empty
                out.pack();
                out.setVisible(true);
                out.setLocation(800, 500);
                waiting = false;
                send.setText("注册");
                send.repaint();
            }
        });
        /*
         * this icon is used for set clear buttons
         * hope to work
         */
        clear_un.setIcon(imageIcon);
        clear_un.setBounds(440, 250, 35, 35);
        clear_un.addActionListener(Event -> t_user.setText(""));
        clear_pw.setBounds(440, 290, 35, 35);
        clear_pw.setIcon(imageIcon);
        clear_pw.addActionListener(Event -> f_password.setText(""));
        clear_rp.setBounds(440, 330, 35, 35);
        clear_rp.setIcon(imageIcon);
        clear_rp.addActionListener(Event -> r_password.setText(""));
        userIconLabel.setBounds(250, 370, 128, 128);
        userIconLabel.setBackground(new Color(255, 255, 255));
        userIconSelector.setBounds(70, 370, 125, 40);
        userIconSelector.setFont(new Font("black", Font.PLAIN, 18));
        userIconSelector.setHorizontalAlignment(SwingConstants.LEFT);
        userIconSelector.setToolTipText("点击这里设置您的头像");
        userIconSelector.addActionListener(e -> {
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File file = fileChooser.getSelectedFile();
                userIcon = new ImageIcon(file.getPath());
                userIcon.setImage(userIcon.getImage().getScaledInstance(128, 128, Image.SCALE_AREA_AVERAGING));
                userIconLabel.setIcon(userIcon);
                userIconLabel.setBounds(250, 370, 128, 128);
                path = file.getPath();
            } catch (NullPointerException ignore) {
            }
            if (!userIcon.toString().equals(defaultUserIcon.toString())) userIconLabel.setToolTipText("");
        });
        mustKnown.setBounds(180, 520, 125, 50);
        mustKnown.setBackground(new Color(10, 0, 0));
        mustKnown.setOpaque(false);
        mustKnown.addActionListener(Event -> send.setEnabled(true));
        /*
         * this code is mede for show a underline for our ser must known
         * create from internet
         */
        HashMap<TextAttribute, Object> hm = new HashMap<>();
        hm.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON); // 定义是否有下划线
        hm.put(TextAttribute.SIZE, 12); // 定义字号,
        hm.put(TextAttribute.FAMILY, "Black"); // 定义字体名
        Font font = new Font(hm); // 生成字号为12，字体为black，字形带有下划线的字体
        messageOfMustknow.setBounds(280, 528, 130, 35);
        messageOfMustknow.setBorder(null);
        messageOfMustknow.setBackground(new Color(10, 0, 0));
        messageOfMustknow.setOpaque(false);
        messageOfMustknow.setFont(font);
        messageOfMustknow.addActionListener(Event -> new User_need_know());

    }

    private void setItems() {
        setBackground();
        setUser();
        setPassword();
        setRepeatPassword();
        setButtons();
    }

    protected void addItems() {
        container.add(t_user);
        container.add(UsernameLabel);
        container.add(f_password);
        container.add(PasswordLabel);
        container.add(r_password);
        container.add(RepeatPasswordLabel);
        container.add(send);
        container.add(clear_pw);
        container.add(clear_rp);
        container.add(clear_un);
        container.add(userIconSelector);
        container.add(userIconLabel);
        container.add(mustKnown);
        container.add(messageOfMustknow);
    }

    RegisterFrame() {
        setBasic();
        createItems();
        setItems();
        addItems();
    }

    void regResult(String result) {
        if (!waiting) return;
        waiting = false;
        if (result.equals("OK")) {
            login(username, password);
            if (path.length() > 0) {
                Global.guiAdapter.executeCmd("alterUser", 0 + "", 0 + "",
                        DataGetters.uploadFile(path, 0, 0) + "",
                        null, null, null, null, null
                );
            }
            var aaa = new messageFrame("您已成功创建了一个新用户， 快去试试吧!", "恭喜", true);
            new Timer(5, (Object[] args) -> {
                ((messageFrame) args[0]).setVisible(false);
                return 0;
            }, aaa);
            this.dispose();
            WindowManager.remove(2);
            new MainUI(AuthManager.getUser().getId());
        } else {
            new messageFrame(result + "o((⊙﹏⊙))o.", "错误", false);
        }
    }

    private class User_need_know extends JFrame {
        String message = "    《用户协议》（以下简称“本协议”）是您" +
                "（或称“用户”，指注册、登录、使用、浏览本服务的个人或组织）" +
                "与晨晖" +
                "科技有限公司及其关联公司（以下简称“晨晖”）及其运营合作单位（以下简称“合作单位”）之间关于" +
                "" +
                "网站（www.danale.com，简称本网站)与云平台接入产品" +
                "、程序及服务所订立的协议。\n " +
                "    晨晖在此特别提醒用户认真阅读、充分理解---本协议中各条款，包括免除或者限制" +
                "" +
                "责任的免责条款及对用户的权利限制条款。" +
                "请您审慎阅读并选择接受或不接受本协议(未成年人应在法定监护人陪同下阅读）。除非您接受本协议所有条款，" +
                "否则您无权注册、登录或使用本协议所涉相关服务。" +
                "您的注册、登录、使用等行为将视为对本协议的接受，并同意接受本协议各项条款的约束。 " +
                "您对本协议的接受即自愿接受全部条款的约束，包括接受晨晖公司对任一服务条款随时所做的任何修改。\n" +
                "    本协议可由" +
                "" +
                "随时更新，更新后的协议条款一旦公布即代替原来的协议条款，恕不再另行通知，" +
                "用户可在本网站查阅最新版协议条款。在修改本协议相关条款之后，如果用户不接受修改后的条款，" +
                "请立即停止使用" +
                "" +
                "提供的服务，用户继续使用晨晖提供的服务将被视为已接受了修改后的协议。";
        JTextArea mes = new JTextArea(message);

        User_need_know() {
            this.setVisible(true);
            this.setBounds(600, 300, 250, 600);
            mes.setSize(800, 600);
            mes.setLineWrap(true);
            mes.setFont(new Font("Black", 30, 22));
            mes.setColumns(10);
            mes.setEditable(false);
            this.getContentPane().add(mes);
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);
            this.pack();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(RegisterFrame::new);
    }
}
class messageFrame extends JFrame {
    messageFrame(String mes, String title, boolean ok) {
        super(title);
        Dialog_Plug_in dialog_plug_in = new Dialog_Plug_in();
        dialog_plug_in.setFont(new Font("black", Font.BOLD, 12));
        dialog_plug_in.setText(mes);
        dialog_plug_in.setPreferredSize(new Dimension(200, 200));
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(dialog_plug_in);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        if (ok)
            setUndecorated(true);// this means the success pane will soon dispear and the user will face the new frame
        pack();
        this.setVisible(true);
    }
}
class myLabel extends JLabel {
    myLabel(ImageIcon imageIcon) {
        super(imageIcon);
    }

    myLabel(String s) {
        super();
        this.setFont(new Font("black", Font.BOLD, 15));
        this.setText(s);
    }
}