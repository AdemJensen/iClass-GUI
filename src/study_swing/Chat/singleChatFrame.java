package study_swing.Chat;
import study_swing.DataGetters;
import study_swing.Dialog_Plug_in;
import study_swing.MyNoBackgroundButton;
import top.chorg.gui.WindowManager;
import top.chorg.kernel.communication.api.auth.User;
import top.chorg.kernel.communication.api.chat.ChatMsg;
import top.chorg.kernel.communication.auth.AuthManager;
import top.chorg.system.Global;
import top.chorg.support.Timer;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;

import static sun.font.FontDesignMetrics.getMetrics;

public class singleChatFrame extends JFrame {
    int type = 2;
    protected String defaultIconCode = "\\pお&[icon]德莉莎\\";//including 14 chars
    protected Dialog_Plug_in addText;
    private ChatPane text;// show the dialog and 文字输入框;//extends from text pane
    private Box box, box_1, box_2; // 放输入组件的容器
    private Box toolList;
    private Container container;
    protected MyNoBackgroundButton b_insert;
    private MyNoBackgroundButton b_remove;
    private MyNoBackgroundButton b_icon;
    private MyNoBackgroundButton fontTool;
    private MyNoBackgroundButton drawPad;// 插入按钮;清除按钮;插入图片按钮; font icon, drawing in time
    private MyNoBackgroundButton[] jButtons;
    private JComboBox<String> fontName, fontSize, fontStyle, fontColor, fontBackColor; // 字体名称;字号大小;文字样式;文字颜色;文字背景颜色
    private JScrollPane scrollPane;
    private JFrame FontHelp;
    protected ArrayList<SingleDialogData> dialogDatas = new ArrayList<>();
    private Color otherMessageColor = new Color(188, 237, 245);
    private Color otherMessageBorderColor = new Color(156, 205, 213);
    private Color selfMessageColor = new Color(230, 230, 230);
    private Color selfMessageBorderColor = new Color(198, 198, 198);
    private int frameWidth = 550;
    JPanel plusItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
    protected boolean touched;// default boolean is false, no need to set
    private int selfId;
    private ImageIcon iconOfSelf;
    String selfUserName;
    int targetId;
    User[] target;
    String targetName;
    ImageIcon iconOfTarget;
    private ChatMsg[] msgs;

    static User[] getUsersFromClassIds(int[] ids) {
        User[] res = new User[ids.length];
        for (int t = 0; t < ids.length; t++) {
            res[t] = DataGetters.getUserInfo(ids[t]);
        }
        return res;
    }

    private User[] getUsers() {
        if (type == 2) {
            return new User[]{DataGetters.getUserInfo(targetId)};
        } else return getUsersFromClassIds(DataGetters.getClassInfo(targetId).classmates);
    }

    // the message when it need to be sent, refresh when the insert button pressed down
    private void creatItems() {
        container = this.getContentPane();
        ImageIcon insertIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "insertIcon.png");
        insertIcon.setImage(insertIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        ImageIcon pictureIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "pictureIcon2.png");
        pictureIcon.setImage(pictureIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        ImageIcon removeIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "removeIcon.png");
        removeIcon.setImage(removeIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        ImageIcon drawPadIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "myDrawPad.jpg");// the icons about them
        ImageIcon fontIcon = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "FontIcon.png");
        fontIcon.setImage(fontIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        iconOfSelf.setImage(iconOfSelf.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        iconOfTarget.setImage(iconOfTarget.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
        b_insert = new MyNoBackgroundButton(insertIcon); // 插入
        b_icon = new MyNoBackgroundButton(pictureIcon); // 插入图片
        b_remove = new MyNoBackgroundButton(removeIcon); // 清除
        fontTool = new MyNoBackgroundButton(fontIcon); // font
        drawPad = new MyNoBackgroundButton(drawPadIcon);//draw pad in time
        addText = new Dialog_Plug_in();
        text = new ChatPane(container);
        toolList = Box.createHorizontalBox();
        new BoxLayout(text, BoxLayout.Y_AXIS);
        jButtons = new MyNoBackgroundButton[4];
        jButtons[0] = b_insert;
        jButtons[1] = b_icon;
        jButtons[2] = b_remove;
        jButtons[3] = fontTool;
        box = Box.createVerticalBox(); // 竖结构
        box_1 = Box.createHorizontalBox(); // 横结构
        box_2 = Box.createHorizontalBox(); // 横结构
        dialogDatas.add(new SingleDialogData());
        plusItem = new JPanel();

    }


    private void setToolList() {
        toolList.add(fontTool);
        toolList.add(Box.createHorizontalStrut(8));
        toolList.add(b_icon);
        toolList.add(Box.createHorizontalStrut(8));
        toolList.add(drawPad);
        toolList.add(Box.createHorizontalGlue());
    }

    private void setB_insert() {
        b_insert.addActionListener((ActionEvent e) -> {
            SingleDialogData to_paint = dialogDatas.get(dialogDatas.size() - 1);
            to_paint.setFrom(AuthManager.getUser());
            if (!addText.getText().equals("") || to_paint.size() != 0) {
                //text.setPreferredSize(new Dimension(text.getWidth(), to_paint.height + 100));
                if (!touched) {
                    to_paint.add(addText.getText(), addText.getFont());
                }
                dialogDatas.add(new SingleDialogData());
                touched = false;
                for (OneSentenceData oneSentenceData : to_paint) {
                    if (oneSentenceData.message.length() < defaultIconCode.length()) continue;
                    if (oneSentenceData.message.substring(0, defaultIconCode.length()).equals(defaultIconCode)) {
                        int apID = DataGetters.uploadFile(oneSentenceData.message.substring(defaultIconCode.length()), 0, 0);
                        oneSentenceData.setIconNumber(apID);
                    }
                }
                DialogPainter dialogPainter = new DialogPainter(to_paint, selfId);
                String result = DataGetters.sendChat(type, targetId, to_paint.toString());
                // TODO: Send message result.
                if (!result.equals("OK")) {
                    dialogPainter.isOK = false;
                    dialogPainter.repaint();
                }
                text.add(dialogPainter);
                text.repaint();
                addText.setText("");
                new Timer(
                        500,
                        (Object[] args) -> {
                            scrollPane.getVerticalScrollBar().setValue(-5);
                            scrollPane.getVerticalScrollBar().setValue(199999999);
                            return 0;
                        }
                );
            }
        });
    }

    //    private ChatMsg[] msgs = Global.getVarCon("GET_CHAT_HISTORY", ChatMsg[].class);
    private void paintHistory() {
        for (ChatMsg chatMsg : msgs) {
            var data = changeStringToSingleDialogData(chatMsg.content);
            data.setFrom(DataGetters.getUserInfo(chatMsg.fromId));
            DialogPainter dialogPainter = new DialogPainter(data, chatMsg.fromId);
            this.text.add(dialogPainter);
        }
    }

    private SingleDialogData changeStringToSingleDialogData(String content) {
        Scanner scanner = new Scanner(content);
        SingleDialogData data = new SingleDialogData();
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            //System.out.printf("GOT:%s[TAG]%s\n", s.substring(0, defaultIconCode.length()), defaultIconCode);
            if (s.length() <= defaultIconCode.length() || !s.substring(0, defaultIconCode.length()).equals(defaultIconCode)) {
                var data2 = new OneSentenceData(s);
                data.add(data2);
            } else {
                File file = DataGetters.downloadFile(Integer.parseInt(s.substring(defaultIconCode.length())));
                //System.out.println(file);
                data.add(new OneSentenceData(file.getPath(), 500));//TODO:Maybe need download
            }
        }
        return data;
    }

    void insertIncomingMessage(ChatMsg msg) {
        // TODO: Paint target
        if (msg.fromId == selfId) return;
        SingleDialogData to_paint = changeStringToSingleDialogData(msg.content);
        to_paint.setFrom(DataGetters.getUserInfo(msg.fromId));
        DialogPainter dialogPainter = new DialogPainter(to_paint, msg.fromId);
        text.add(dialogPainter);
        text.repaint();
        new Timer(
                500,
                (Object[] args) -> {
                    text.repaint();
                    scrollPane.repaint();
                    scrollPane.getVerticalScrollBar().setValue(-5);
                    scrollPane.getVerticalScrollBar().setValue(199999999);
                    return 0;
                }
        );
    }


    private void setB_remove() {
        // 清除事件
        b_remove.addActionListener(e ->
        {
            addText.setText("");
            dialogDatas.get(dialogDatas.size() - 1).clear();
        });
    }

    private void setB_icon() {
        // 插入图片事件
        b_icon.addActionListener(arg0 -> {
            try {
                JFileChooser f = new JFileChooser(); // 查找文件
                f.showOpenDialog(null);
                File file = f.getSelectedFile();
                ImageIcon insertIcon = new ImageIcon(file.getPath());
                // zip the size of the icon
                insertIcon(insertIcon, file.getPath());
            } catch (NullPointerException ignore) {
            }
        });
    }

    void insertIcon(ImageIcon insertIcon, String path) {
        int newWidth = insertIcon.getIconWidth();
        int newHeight = insertIcon.getIconHeight();
        // zip the size of the icon
        while (newWidth > 500) {
            newHeight /= 1.1;
            newWidth /= 1.1;
        }
        insertIcon.setImage(insertIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
        //this method seems re use the method in TextPane
        addText.insertIcon(insertIcon); // 插入图片
        try {
            addText.getDocument().insertString(addText.getDocument().getLength(), "\n", getFontAttrib().getAttrSet());// 这样做可以换行
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        addText.setCaretPosition(addText.getStyledDocument().getLength()); // 设置插入位置
        //addText.getDocument().insertString("\n");
        dialogDatas.get(dialogDatas.size() - 1).add(new OneSentenceData(path, newWidth));
    }

    private void setDrawPad() {
        drawPad.setPreferredSize(new Dimension(35, 35));
        drawPad.addActionListener(a -> new DrawPad("my drawPad", this));
    }

    /**
     * this method change the background of the buttons,
     * shapes to suit the dialog
     */

    private void setjButtonsShape() {
        for (MyNoBackgroundButton myNoBackgroundButton : jButtons) {
            myNoBackgroundButton.setBackground(new Color(1, 1, 1));
            myNoBackgroundButton.setOpaque(false);
            myNoBackgroundButton.setBorder(null);
        }
    }

    private void setFontTool() {
        fontTool.addActionListener(Event -> {
            FontHelp = new JFrame();
            FontHelp.setVisible(true);
            FontHelp.setBounds(800, 400, 570, 65);
            FontHelp.setDefaultCloseOperation(HIDE_ON_CLOSE);
            FontHelp.setResizable(false);
            FontHelp.getContentPane().add(box_1);
            FontHelp.pack();
        });
    }

    private void setScrollPane() {
        scrollPane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(600, 400));
    }

    private void setFonts() {
        String[] str_name = {"宋体", "黑体", "Dialog", "Gulim"};
        String[] str_Size = {"12", "14", "18", "22", "30", "40"};
        String[] str_Style = {"常规", "斜体", "粗体", "粗斜体"};
        String[] str_Color = {"黑色", "红色", "蓝色", "黄色", "绿色"};
        String[] str_BackColor = {"无色", "灰色", "淡红", "淡蓝", "淡黄", "淡绿"};
        fontName = new JComboBox<>(str_name); // 字体名称
        fontSize = new JComboBox<>(str_Size); // 字号
        fontStyle = new JComboBox<>(str_Style); // 样式
        fontColor = new JComboBox<>(str_Color); // 颜色
        fontBackColor = new JComboBox<>(str_BackColor); // 背景颜色
        fontName.addActionListener(Event -> {
            //doc.setCharacterAttributes(0, 0, getFontAttrib().getAttrSet(), true);
        });
    }

    private void setBox() {
        box.add(toolList);
        box.add(Box.createVerticalStrut(8)); // 两行的间距
        box.add(box_2);
        box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // 8个的边距
    }

    private void setBox_1() {
        // 开始将所需组件加入容器
         /*these code just added some buttons,
         do not so important
          */
        box_1.add(new JLabel("字体：")); // 加入标签
        box_1.add(fontName); // 加入组件
        box_1.add(Box.createHorizontalStrut(8)); // 间距
        box_1.add(new JLabel("样式："));
        box_1.add(fontStyle);
        box_1.add(Box.createHorizontalStrut(8));
        box_1.add(new JLabel("字号："));
        box_1.add(fontSize);
        box_1.add(Box.createHorizontalStrut(8));
        box_1.add(new JLabel("颜色："));
        box_1.add(fontColor);
        box_1.add(Box.createHorizontalStrut(8));
        box_1.add(new JLabel("背景："));
        box_1.add(fontBackColor);
    }

    private void setBox_2() {
         /*these code just added some buttons,
         do not so important
          */
        box_2.add(addText);
        box_2.add(Box.createHorizontalStrut(18));
        box_2.add(b_insert);
        box_2.add(Box.createHorizontalStrut(18));
        box_2.add(b_remove);
    }

    private void setText() {
        text.setOpaque(false);
        //text.setPreferredSize(new Dimension(600, 400));
        //text.setSize(600, 400);
    }

    private void setAddText() {
        addText.requestFocus();
        addText.setSize(400, 40);
        addText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            /**
             * still on building
             * @param keyEvent
             */
            @Override
            public void keyPressed(KeyEvent keyEvent) {

                char now = keyEvent.getKeyChar();
                if (now == '\n') {
                    touched = true;
                    String messageAll = addText.getText();
                    SingleDialogData to_send = dialogDatas.get(dialogDatas.size() - 1);//get the last dialog
                    String s = "";//clear the newest , message
                    Scanner scanner = new Scanner(messageAll);
                    while (scanner.hasNext()) {
                        s = scanner.nextLine();
                    }
                    //is ths string is too long, this will change it
                    //and store them in some sets strings in the same
                    //font
                    FontMetrics fontMetrics = getMetrics(addText.getFont());
                    String toAdd = "";
                    int i = 0;
                    while (i < s.length()) {
                        if (fontMetrics.stringWidth(toAdd) < frameWidth - 50) toAdd += s.charAt(i);
                        else {
                            to_send.add(new OneSentenceData(toAdd, addText.getFont()));
                            toAdd = "";
                        }
                        i++;
                    }
                    //add the last sentence
                    to_send.add(new OneSentenceData(toAdd, addText.getFont()));
                    if (keyEvent.isControlDown()) b_insert.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
            }
        });
    }

    private void setFontButtons() {
        this.fontName.addActionListener(Event -> {
            if (fontStyle.getSelectedItem() != null || fontSize.getSelectedItem() != null) {
                if (fontStyle.getSelectedItem() != null && fontSize.getSelectedItem() != null)
                    this.addText.setFont(new Font((String) fontName.getSelectedItem(), getFontStyle((String) fontStyle.getSelectedItem()),
                            Integer.parseInt((String) fontSize.getSelectedItem())));
                else if (fontStyle.getSelectedItem() == null)
                    this.addText.setFont(new Font((String) fontName.getSelectedItem(), 0,
                            Integer.parseInt((String) Objects.requireNonNull(fontSize.getSelectedItem()))));
                else
                    this.addText.setFont(new Font((String) fontName.getSelectedItem(), getFontStyle((String) fontStyle.getSelectedItem()),
                            0));
            } else this.addText.setFont(new Font((String) fontName.getSelectedItem(), 0, 10));
        });
        fontSize.addActionListener(Event -> {
            if (fontStyle.getSelectedItem() != null && fontName.getSelectedItem() != null)
                this.addText.setFont(new Font((String) fontName.getSelectedItem(), getFontStyle((String) fontStyle.getSelectedItem()),
                        Integer.parseInt((String) Objects.requireNonNull(fontSize.getSelectedItem()))));
            else if (fontStyle.getSelectedItem() != null && fontName.getSelectedItem() == null) {
                this.addText.setFont(new Font("black", getFontStyle((String) fontStyle.getSelectedItem()),
                        Integer.parseInt((String) Objects.requireNonNull(fontSize.getSelectedItem()))));
            } else if (fontStyle.getSelectedItem() == null && fontName.getSelectedItem() != null) {
                this.addText.setFont(new Font((String) fontName.getSelectedItem(), 0,
                        Integer.parseInt((String) Objects.requireNonNull(fontSize.getSelectedItem()))));
            } else this.addText.setFont(new Font((String) fontName.getSelectedItem(), 0, 10));
        });
        fontStyle.addActionListener(Event -> {
            if (fontName.getSelectedItem() != null && fontSize.getSelectedItem() != null) {
                this.addText.setFont(new Font((String) fontName.getSelectedItem(), getFontStyle((String) Objects.requireNonNull(fontStyle.getSelectedItem())),
                        Integer.parseInt((String) fontSize.getSelectedItem())));
            } else if ((fontName.getSelectedItem() != null && fontSize.getSelectedItem() == null)) {
                this.addText.setFont(new Font((String) fontName.getSelectedItem(), getFontStyle((String) Objects.requireNonNull(fontStyle.getSelectedItem())), 10));
            } else if (fontName.getSelectedItem() == null && fontSize.getSelectedItem() != null) {
                this.addText.setFont(new Font((String) fontName.getSelectedItem(), getFontStyle((String) Objects.requireNonNull(fontStyle.getSelectedItem())),
                        Integer.parseInt((String) fontSize.getSelectedItem())));
            } else this.addText.setFont(new Font((String) fontName.getSelectedItem(), 0, 10));
        });
    }

    void setPlusItem() {
        plusItem.setLayout(new FlowLayout(FlowLayout.LEFT));
        plusItem.setPreferredSize(new Dimension(600, 30));
        String[] strings = {"语音", "视频", "相册", "活动", "设置"};
        for (String string : strings) {
            MyNoBackgroundButton myNoBackgroundButton = new MyNoBackgroundButton(string);
            myNoBackgroundButton.setBorder(BorderFactory.createEtchedBorder());
            myNoBackgroundButton.setPreferredSize(new Dimension(50, 28));
            plusItem.add(myNoBackgroundButton);
        }
    }

    private int getFontStyle(String temp_style) {
        switch (temp_style) {
            case "粗体":
                return (FontAttrib.BOLD);
            case "斜体":
                return (FontAttrib.ITALIC);
            case "粗斜体":
                return (FontAttrib.BOLD_ITALIC);
            default:
                return (FontAttrib.GENERAL);
        }
    }

    /**
     * need to change
     */
    private void addItems() {
        container.add(plusItem, BorderLayout.NORTH);
        container.add(scrollPane);
        container.add(box, BorderLayout.SOUTH);
    }

    void setBasic() {
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setTitle(selfUserName + "和" + targetName + "的对话");
        this.setLocation(400, 100);
        pack();
    }

    private FontAttrib getFontAttrib() {
        FontAttrib att = new FontAttrib();
        att.setText(addText.getText());
        att.setName((String) fontName.getSelectedItem());
        att.setSize(Integer.parseInt((String) Objects.requireNonNull(fontSize.getSelectedItem())));
        String temp_style = (String) fontStyle.getSelectedItem();
        assert temp_style != null;
        switch (temp_style) {
            case "常规":
                att.setStyle(FontAttrib.GENERAL);
                break;
            case "粗体":
                att.setStyle(FontAttrib.BOLD);
                break;
            case "斜体":
                att.setStyle(FontAttrib.ITALIC);
                break;
            case "粗斜体":
                att.setStyle(FontAttrib.BOLD_ITALIC);
                break;
        }
        String temp_color = (String) fontColor.getSelectedItem();
        assert temp_color != null;//what is it means?
        switch (temp_color) {
            case "黑色":
                att.setColor(new Color(0, 0, 0));
                break;
            case "红色":
                att.setColor(new Color(255, 0, 0));
                break;
            case "蓝色":
                att.setColor(new Color(0, 0, 255));
                break;
            case "黄色":
                att.setColor(new Color(255, 255, 0));
                break;
            case "绿色":
                att.setColor(new Color(0, 255, 0));
                break;
        }
        String temp_backColor = (String) fontBackColor.getSelectedItem();
        assert temp_backColor != null;
        if (!temp_backColor.equals("无色")) {
            switch (temp_backColor) {
                case "灰色":
                    att.setBackColor(new Color(200, 200, 200));
                    break;
                case "淡红":
                    att.setBackColor(new Color(255, 200, 200));
                    break;
                case "淡蓝":
                    att.setBackColor(new Color(200, 200, 255));
                    break;
                case "淡黄":
                    att.setBackColor(new Color(255, 255, 200));
                    break;
                case "淡绿":
                    att.setBackColor(new Color(200, 255, 200));
                    break;
            }
        }
        return att;
    }

    private class FontAttrib {
        static final int GENERAL = 0; // 常规
        static final int BOLD = 1; // 粗体
        static final int ITALIC = 2; // 斜体
        static final int BOLD_ITALIC = 3; // 粗斜体
        private String text, name; // 要输入的文本和字体名称
        private int style = 0, size = 0; // 样式和字号
        private Color color, backColor; // 文字颜色和背景颜色

        FontAttrib() {
        }

        SimpleAttributeSet getAttrSet() {
            // 属性集
            SimpleAttributeSet attrSet = new SimpleAttributeSet();
            if (name != null) {
                StyleConstants.setFontFamily(attrSet, name);
            }
            if (style == FontAttrib.GENERAL) {
                StyleConstants.setBold(attrSet, false);
                StyleConstants.setItalic(attrSet, false);
            } else if (style == FontAttrib.BOLD) {
                StyleConstants.setBold(attrSet, true);
                StyleConstants.setItalic(attrSet, false);
            } else if (style == FontAttrib.ITALIC) {
                StyleConstants.setBold(attrSet, false);
                StyleConstants.setItalic(attrSet, true);
            } else if (style == FontAttrib.BOLD_ITALIC) {
                StyleConstants.setBold(attrSet, true);
                StyleConstants.setItalic(attrSet, true);
            }
            StyleConstants.setFontSize(attrSet, size);
            if (color != null) {
                StyleConstants.setForeground(attrSet, color);
            }
            if (backColor != null) {
                StyleConstants.setBackground(attrSet, backColor);
            }
            return attrSet;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        void setBackColor(Color backColor) {
            this.backColor = backColor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        void setStyle(int style) {
            this.style = style;
        }
    }

    private class DialogPainter extends Dialog_Plug_in {
        private SingleDialogData singledialogData;
        private int maxMessageWeight;
        private int maxMessageHeight;
        private boolean isRight;
        private boolean isOK = true;
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        private User target;

        /**
         * this method override the method of the textPane, aiming to show
         * a pair of things, including userIcon, dialog pao, pictures...
         * to paint it needs two different methods, a lot of works in need
         */
        DialogPainter(SingleDialogData singledialogData, int id) {
            isRight = id == singleChatFrame.this.selfId;//judge if is the self user
            this.target = DataGetters.getUserInfo(id);// make the target to a user
            this.singledialogData = singledialogData;
            this.setOpaque(false);
            this.setEditable(false);
            maxMessageWeight = singledialogData.weight + 14;
            maxMessageHeight = singledialogData.height + 14;
            this.setPreferredSize(new Dimension(container.getWidth(), maxMessageHeight + 20));
        }

        public void paint(Graphics g) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (!isRight) {
                paintLeft(graphics2D);
            } else {
                paintRight(graphics2D);
            }
            //System.out.println(text.getHeight());
        }

        private void paintError(Graphics graphics) {
            ImageIcon warning = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "warning.jpg");
            warning.setImage(warning.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT));
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(warning.getImage(), container.getWidth() - maxMessageWeight - 35, maxMessageHeight / 2, warning.getImageObserver());
        }

        private void paintRight(Graphics2D graphics2D) {
            // scan every dialog to get the max dialog weight all calculate the height
            //check to paint the warning
            int t;
            if (container.getWidth() == 0) t = 550;
            else t = container.getWidth();
            if (!isOK) paintError(graphics2D);

            int x = t - maxMessageWeight - 42;
            int y = 0;
            Point point = new Point(x, y);
            graphics2D.setFont(new Font("black", Font.BOLD, 8));

            //paint self icon and name
            graphics2D.drawImage(iconOfSelf.getImage(), container.getWidth() - 42, 0, iconOfSelf.getImageObserver());
            // 绘制自己消息圆角消息气泡矩形
            graphics2D.setColor(selfMessageColor);
            graphics2D.fillRoundRect(point.x - 7, point.y + 2, maxMessageWeight, maxMessageHeight, 10, 10);
            // 绘制圆角消息气泡边框
            graphics2D.setColor(selfMessageBorderColor);
            graphics2D.drawRoundRect(point.x - 7, point.y + 2, maxMessageWeight, maxMessageHeight, 10, 10);
            // 消息发出者是自己，则头像靠右显示
            xPoints[0] = (point.x - 7) + (maxMessageWeight);
            yPoints[0] = point.y + 9;
            xPoints[1] = xPoints[0] + 9;
            yPoints[1] = point.y + 7;
            xPoints[2] = xPoints[0];
            yPoints[2] = point.y + 4;
            graphics2D.setColor(selfMessageColor);
            graphics2D.fillPolygon(xPoints, yPoints, 3);
            graphics2D.setColor(selfMessageBorderColor);
            //int i = 0;
            graphics2D.drawPolyline(xPoints, yPoints, 3);
            graphics2D.setColor(selfMessageColor);
            //i++;
            //System.out.println("这是为了防止重复报错，请忽略" + i + "(ノへ￣、)");
            graphics2D.drawLine(xPoints[0], yPoints[0] + 1, xPoints[2], yPoints[2] - 1);
            painting(graphics2D, x);
        }

        private void paintLeft(Graphics2D graphics2D) {
            if (type == 2) {
                iconOfTarget.setImage(iconOfTarget.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                graphics2D.drawImage(iconOfTarget.getImage(), 0, 0, iconOfTarget.getImageObserver());
                graphics2D.setFont(new Font("black", Font.BOLD, 8));
                graphics2D.setColor(selfMessageColor);
                //graphics2D.drawString(targetName, 42, 0);
            } else {
                System.out.println("PAINTING:"+ Global.gson.toJson(singledialogData.from));
                ImageIcon imageIcon = new ImageIcon(DataGetters.downloadFile(singledialogData.from.getAvatar()).getPath());
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
                graphics2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
                graphics2D.setFont(new Font("black", Font.BOLD, 8));
                graphics2D.setColor(selfMessageColor);
                //graphics2D.drawString(target.getUsername(), 42, 0);
            }
            graphics2D.setColor(otherMessageColor);
            int x = 42, y = 0;
            Point point = new Point(x, y);
            graphics2D.setFont(new Font("black", Font.BOLD, 8));
            graphics2D.fillRoundRect(point.x - 7, point.y + 2, maxMessageWeight, maxMessageHeight, 10, 10);
            // 绘制圆角消息气泡边框
            graphics2D.setColor(otherMessageBorderColor);
            graphics2D.drawRoundRect(point.x - 7, point.y + 2, maxMessageWeight, maxMessageHeight, 10, 10);
            // 消息发出者是别人，则头像靠左显示
            xPoints[0] = point.x - 7;
            yPoints[0] = point.y + 9;
            xPoints[1] = xPoints[0] - 7;
            yPoints[1] = point.y + 7;
            xPoints[2] = xPoints[0];
            yPoints[2] = point.y + 4;
            graphics2D.setColor(otherMessageColor);
            graphics2D.fillPolygon(xPoints, yPoints, 3);
            graphics2D.setColor(otherMessageBorderColor);
            graphics2D.drawPolyline(xPoints, yPoints, 3);
            graphics2D.setColor(otherMessageColor);
            graphics2D.drawLine(xPoints[0], yPoints[0] + 1, xPoints[2], yPoints[2] - 1);
            painting(graphics2D, x);
        }

        private void painting(Graphics2D graphics2D, int x) {
            int now;
            if (singledialogData.size() != 0) {
                if (singledialogData.get(0).message.length() < defaultIconCode.length() ||
                        !singledialogData.get(0).message.substring(0, defaultIconCode.length()).equals(defaultIconCode))
                    now = singledialogData.get(0).height + 5;
                else now = 7;
            } else now = 7;
            graphics2D.setColor(Color.black);
            for (int t = 0; t < singledialogData.size(); t++) {
                OneSentenceData oneSentenceData = singledialogData.get(t);
                String s = oneSentenceData.message;
                //if(s.length() == 0) continue;
                if (s.length() < defaultIconCode.length() || !s.substring(0, defaultIconCode.length()).equals(defaultIconCode)) {
                    //refresh the place of painting
                    graphics2D.setFont(oneSentenceData.font);
                    graphics2D.drawString(s, x, now);
                    FontMetrics fontMetrics = getMetrics(oneSentenceData.font);
                    now += fontMetrics.getHeight();
                } else {
                    graphics2D.drawImage(oneSentenceData.imageIcon.getImage(), x, now, oneSentenceData.imageIcon.getImageObserver());
                    now += oneSentenceData.height;
                    if (t < singledialogData.size() - 1) {//has string after
                        now += singledialogData.get(t + 1).height;
                    }
                }
            }
        }
    }

    protected class OneSentenceData {
        protected String message;
        private Font font;
        private int height;
        private int weight;
        private ImageIcon imageIcon;
        int iconNumber;

        /**
         * this method require a string and its font
         * to download in its data
         *
         * @E-mail 2450321189@qq.com
         */
        OneSentenceData(String message, Font font) {
            this.message = message + '\n';
            this.font = font;
            imageIcon = null;
            FontMetrics fontMetrics = getFontMetrics(font);
            height = fontMetrics.getHeight();
            weight = fontMetrics.stringWidth(message);
        }

        /**
         * this method require the path of the
         * dialog and store it
         *
         * @param path
         */
        OneSentenceData(String path, int maxWeight) {
            // just to notice it is a icon
            message = defaultIconCode + path + '\n';
            imageIcon = new ImageIcon(path);
            weight = imageIcon.getIconWidth();
            height = imageIcon.getIconHeight();
            while (weight > maxWeight) {
                weight /= 1.1;
                height /= 1.1;
            }
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(weight, height, Image.SCALE_SMOOTH));
        }

        OneSentenceData(String message) {
            this(message, new JTabbedPane().getFont());
        }

        void setIconNumber(int iconNumber) {
            this.iconNumber = iconNumber;
        }

        int getIconNumber() {
            return iconNumber;
        }

    }

    protected class SingleDialogData extends ArrayList<OneSentenceData> {
        private User from;
        private int weight;
        private int height;

        public void setFrom(User from) {
            this.from = from;
        }

        public User getFrom() {
            return from;
        }

        protected void add(String string, Font font) {
            String toAdd = "";
            for (int t = 0; t < string.length(); t++) {
                if (!isOverLength(toAdd, font)) toAdd += string.charAt(t);
                else {
                    System.out.println("download a new Line " + toAdd);
                    this.add(new OneSentenceData(toAdd, font));
                    toAdd = "";
                    t--;
                }
            }
            add(new OneSentenceData(toAdd, font));
        }

        public boolean add(OneSentenceData oneSentenceData) {
            height += oneSentenceData.height;
            if (weight < oneSentenceData.weight) weight = oneSentenceData.weight;
            return super.add(oneSentenceData);
        }

        private boolean isOverLength(String message, Font font) {
            FontMetrics fontMetrics = getFontMetrics(font);
            return (fontMetrics.stringWidth(message) > 500);
        }

        /*
         * get the length of the hole dialog
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (OneSentenceData oneSentenceData : this) {
                if (oneSentenceData.imageIcon == null) s.append(oneSentenceData.message);
                else {
                    s.append(defaultIconCode);
                    //TODO
                    s.append(oneSentenceData.getIconNumber());
                    s.append("\n");
                }
            }
            return s.toString();
        }
    }

    public singleChatFrame(int selfId, int targetId) {
        this(selfId, targetId, 2);
        this.setVisible(true);
        WindowManager.add(this, 100 + targetId);
        scrollPane.repaint();
        scrollPane.getVerticalScrollBar().setValue(199999999);
    }

    public singleChatFrame(int selfId, int targetId, int type) {
        this.selfId = selfId;
        this.targetId = targetId;
        this.type = type;
        try { // 使用Windows的界面风格
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        selfUserName = DataGetters.getUserName(new int[]{selfId})[0];
        iconOfSelf = new ImageIcon(DataGetters.downloadFile(AuthManager.getUser().getAvatar()).getPath());
        target = getUsers();
        targetName = target[0].getUsername();
        iconOfTarget = new ImageIcon(DataGetters.downloadFile(target[0].getAvatar()).getPath());
        msgs = DataGetters.getChatHistory(type, targetId);
        creatItems();
        setPlusItem();
        setToolList();
        setText();
        setAddText();
        setFonts();
        setB_insert();
        setB_remove();
        setB_icon();
        setDrawPad();
        setjButtonsShape();
        setFontTool();
        setScrollPane();
        setFontButtons();
        setBox();
        setBox_1();
        setBox_2();
        addItems();
        setBasic();
        //i don not know what it means
        paintHistory();
        scrollPane.repaint();
        scrollPane.getVerticalScrollBar().setValue(199999999);
        new Timer(
                500,
                (Object[] args) -> {
                    scrollPane.repaint();
                    scrollPane.getVerticalScrollBar().setValue(199999999);
                    return 0;
                }
        );
    }

    @Override
    public void dispose() {
        if (type == 2) {
            WindowManager.remove(100 + targetId);
        }
        else {
            WindowManager.remove(1000 + targetId);
        }
        super.dispose();
    }

    @Override
    public void setVisible(boolean b) {
        if (!b) {
            this.dispose();
        }
        super.setVisible(b);
        scrollPane.repaint();
        scrollPane.getVerticalScrollBar().setValue(199999999);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new singleChatFrame(1, 2));
    }
}
//E:\jdk\jre\lib\rt.jar!\sun\font\FontDesignMetrics.class
//貴方今何処で何もしえますが、この空
// need to add set the enable of the addText, repaint the window when the sha　PUYGYHSCBUCUBSC石川
