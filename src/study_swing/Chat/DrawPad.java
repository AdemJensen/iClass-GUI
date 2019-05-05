package study_swing.Chat;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import static java.awt.event.InputEvent.CTRL_MASK;

// 主界面类
public class DrawPad extends JFrame implements ActionListener {
    /**
     * @param FileName DrawPad
     * @author Liu Jun Guang s
     * @param V 1.0.0
     */
    private static final long serialVersionUID = -2551980583852173918L;
    private JToolBar buttonpanel;//定义按钮面板
    private JMenuBar bar;//定义菜单条
    private JMenu file, color, stroke, help;//定义菜单
    private JMenuItem newfile, openfile, savefile, sendImage, exit;//file 菜单中的菜单项
    private JMenuItem helpin, helpmain, colorchoice, strokeitem;//help 菜单中的菜单项
    private JLabel startbar;//状态栏
    private DrawArea drawarea;//画布类的定义
    private Help helpobject; //定义一个帮助类对象
    private FileClass fileclass;//文件对象
    private String[] fontName;
    private JButton[] buttons;//定义工具条中的按钮组
    private JCheckBox bold, italic;//工具条字体的风格（复选框）
    private JComboBox<String> stytles;//工具条中的字体的样式（下拉列表）
    DrawPad(String string, singleChatFrame relate) {
        // TODO 主界面的构造函数
        super(string);
        //菜单的初始化
        file = new JMenu("文件");
        color = new JMenu("颜色");
        stroke = new JMenu("画笔");
        help = new JMenu("帮助");
        setBar();
        //界面中添加菜单条
        setJMenuBar(bar);
        //菜单中添加快捷键
        file.setMnemonic('F');//既是ALT+“F”
        color.setMnemonic('C');//既是ALT+“C”
        stroke.setMnemonic('S');//既是ALT+“S”
        help.setMnemonic('H');//既是ALT+“H”
        //File 菜单项的初始化
        /*
        try {
            new InputStreamReader(getClass().getResourceAsStream("SourcePackage" + File.separator + "Icon"));//读取文件以类路径为基准
        } catch (Exception e) {
            // TODO 文件读取错误
            JOptionPane.showMessageDialog(this, "图片读取错误！", "错误", JOptionPane.ERROR_MESSAGE);
        }
         */
        Icon nf = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "newfile.jpg");//创建图表
        Icon sf = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "savefile.jpg");//文件菜单项的图标对象
        Icon of = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "openfile.jpg");
        Icon si = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "insertIcon.jpg");
        newfile = new JMenuItem("新建", nf);
        openfile = new JMenuItem("打开", of);
        savefile = new JMenuItem("保存", sf);
        sendImage = new JMenuItem("发送", si);
        exit = new JMenuItem("退出");

        //File 菜单中添加菜单项
        setFile();
        //File 菜单项添加快捷键
        newfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, CTRL_MASK));
        openfile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, CTRL_MASK));
        savefile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, CTRL_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, CTRL_MASK));
        //File 菜单项的注册监听
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        sendImage.addActionListener(this);
        exit.addActionListener(this);
        //Color 菜单项的初始化
        colorchoice = new JMenuItem("调色板");
        colorchoice.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, CTRL_MASK));
        colorchoice.addActionListener(this);
        color.add(colorchoice);
        //Help 菜单项的初始化
        helpmain = new JMenuItem("帮助主题");
        helpin = new JMenuItem("关于小小绘图板");
        //Help 菜单中添加菜单项
        help.add(helpmain);
        help.addSeparator();//添加分割线
        help.add(helpin);
        //Help 菜单项的注册监听
        helpin.addActionListener(this);
        helpmain.addActionListener(this);
        //Stroke 菜单项的初始化
        strokeitem = new JMenuItem("设置画笔");
        strokeitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, CTRL_MASK));
        stroke.add(strokeitem);
        strokeitem.addActionListener(this);
        //工具栏的初始化
        buttonpanel = new JToolBar(JToolBar.HORIZONTAL);
        //定义工具栏图标的名称
        String[] names = {"newfile", "openfile", "savefile", "pen", "line"
                , "rect", "frect", "oval", "foval", "circle", "fcircle"
                , "roundrect", "froundrect", "rubber", "color"
                , "stroke", "word", "cloth"};
        //定义图象数组
        Icon[] icons = new ImageIcon[names.length];
        buttons = new JButton[names.length];
        String[] tiptext = {//这里是鼠标移到相应的按钮上给出相应的提示
                "新建一个图片", "打开图片", "保存图片", "随笔画", "画直线"
                , "画空心的矩形", "填充矩形", "画空心的椭圆", "填充椭圆"
                , "画空心的圆", "填充圆", "画圆角矩形", "填充圆角矩形"
                , "橡皮擦", "颜色", "选择线条的粗细", "文字的输入", "画布"};
        for (int i = 0; i < names.length; i++) {
            icons[i] = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "" + names[i] + ".jpg");//获得图片（以类路径为基准）
            buttons[i] = new JButton(icons[i]);//创建工具条中的按钮
            buttons[i].setToolTipText(tiptext[i]);//这里是鼠标移到相应的按钮上给出相应的提示
            buttonpanel.add(buttons[i]);
            buttons[i].setBackground(Color.red);
            if (i < 3) buttons[i].addActionListener(this);
            else buttons[i].addActionListener(this);
        }
        CheckBoxHandler CHandler = new CheckBoxHandler();//字体样式处理类
        bold = new JCheckBox("粗体");
        bold.setFont(new Font(Font.DIALOG, Font.BOLD, 30));//设置字体
        bold.addItemListener(CHandler);//bold注册监听
        italic = new JCheckBox("斜体");
        italic.addItemListener(CHandler);//italic注册监听
        italic.setFont(new Font(Font.DIALOG, Font.ITALIC, 30));//设置字体
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();//计算机上字体可用的名称
        fontName = ge.getAvailableFontFamilyNames();
        stytles = new JComboBox<>(fontName);//下拉列表的初始化
        stytles.addItemListener(CHandler);//stytles注册监听
        stytles.setMaximumSize(new Dimension(400, 50));//设置下拉列表的最大尺寸
        stytles.setMinimumSize(new Dimension(250, 40));
        stytles.setFont(new Font(Font.DIALOG, Font.BOLD, 20));//设置字体
        //工具栏中添加字体式样
        setButtonPanel();
        //状态栏的初始化
        startbar = new JLabel("我的小小绘图板");
        //绘画区的初始化
        drawarea = new DrawArea(this);
        helpobject = new Help(this);
        fileclass = new FileClass(this, drawarea, relate);
        Container con = getContentPane();//得到内容面板
        con.add(buttonpanel, BorderLayout.NORTH);
        con.add(drawarea, BorderLayout.CENTER);
        con.add(startbar, BorderLayout.SOUTH);
        Toolkit tool = getToolkit();//得到一个Tolkit类的对象（主要用于得到屏幕的大小）
        Dimension dim = tool.getScreenSize();//得到屏幕的大小 （返回Dimension对象）
        setBounds(40, 40, dim.width - 70, dim.height - 100);
        setVisible(true);
        validate();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    //设置状态栏显示的字符
    void setStartBar(String s) {
        startbar.setText(s);
    }
    private  void setBar(){
        bar = new JMenuBar();//菜单条的初始化, 菜单条添加菜单
        bar.add(file);
        bar.add(color);
        bar.add(stroke);
        bar.add(help);
    }
    private void setFile(){
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        file.add(sendImage);
        file.add(exit);
    }
    private void setButtonPanel() {
        buttonpanel.add(bold);
        buttonpanel.add(italic);
        buttonpanel.add(stytles);
    }
    public void actionPerformed(ActionEvent e) {
        // TODO 事件的处理
        for (int i = 3; i <= 13; i++) {
            if (e.getSource() == buttons[i]) {
                drawarea.setCurrentChoice(i);
                drawarea.createNewItem();
                drawarea.repaint();
            }
        }
        if (e.getSource() == newfile || e.getSource() == buttons[0])//新建
        {
            fileclass.newFile();
        } else if (e.getSource() == openfile || e.getSource() == buttons[1])//打开
        {
            fileclass.openFile();
        } else if (e.getSource() == savefile || e.getSource() == buttons[2])//保存
        {
            fileclass.saveFile();
        } else if (e.getSource() == exit)//退出程序
        {
            this.dispose();
        }else if (e.getSource() == sendImage){
            try {
                fileclass.sendDrawPad();
            }catch (IOException e1){
                System.out.println("Error! " + e1.toString());
                e1.fillInStackTrace();
            }
            this.dispose();
        }
        else if (e.getSource() == colorchoice || e.getSource() == buttons[14])//弹出颜色对话框
        {
            drawarea.chooseColor();//颜色的选择
        } else if (e.getSource() == buttons[15] || e.getSource() == strokeitem)//画笔粗细
        {
            drawarea.setStroke();//画笔粗细的调整
        } else if (e.getSource() == buttons[16])//添加文字
        {
            JOptionPane.showMessageDialog(null, "请单击画板以确定输入文字的位置！", "提示"
                    , JOptionPane.INFORMATION_MESSAGE);
            drawarea.setCurrentChoice(14);
            drawarea.createNewItem();
            drawarea.repaint();
        }
        else if (e.getSource() == buttons[17]){
            drawarea.setCurrentChoice(17);
            drawarea.createNewItem();
            drawarea.repaint();
        }
            else if (e.getSource() == helpin){//帮助信息
            helpobject.AboutBook();
        } else if (e.getSource() == helpmain)//帮助主题
        {
            helpobject.MainHeip();
        }
    }
    //字体样式处理类（粗体、斜体、字体名称）
    public class CheckBoxHandler implements ItemListener {
        public void itemStateChanged(ItemEvent ie) {
            // TODO 字体样式处理类（粗体、斜体、字体名称）
            if (ie.getSource() == bold)//字体粗体
            {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    drawarea.setFont(1, Font.BOLD);
                } else
                    drawarea.setFont(1, Font.PLAIN);
            } else if (ie.getSource() == italic)//字体斜体
            {
                if (ie.getStateChange() == ItemEvent.SELECTED)
                    drawarea.setFont(2, Font.ITALIC);
                else drawarea.setFont(2, Font.PLAIN);
            } else if (ie.getSource() == stytles)//字体的名称
            {
                drawarea.stytle = fontName[stytles.getSelectedIndex()];
            }
        }
    }
}