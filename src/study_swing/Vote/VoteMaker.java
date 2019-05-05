package study_swing.Vote;

import study_swing.DataGetters;
import study_swing.Dialog_Plug_in;
import study_swing.MyLabelLikeAera;
import study_swing.TextSize;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class VoteMaker extends JFrame {
    private Container container = this.getContentPane();
    private String title;
    private JLabel titleLabel = new JLabel("设置投票标题");
    private JLabel textLabel = new JLabel("设置投票内容");
    private JLabel chooseLabel = new JLabel("设置选项数");
    private JLabel isSingleLabel = new JLabel("是否多选");
    private Dialog_Plug_in voteBody = new Dialog_Plug_in();
    private JTextArea titleArea = new JTextArea();
    private JScrollPane jScrollPane;
    private JButton addIcon = new JButton("插入图片");
    private JButton ok = new JButton("确认内容");
    private JButton creat = new JButton("创建投票！");
    private JButton cancel = new JButton("取消");
    private JRadioButton setIsSingleY = new JRadioButton("是");
    private JRadioButton setIsSingleN = new JRadioButton("否");
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton changeFont = new JButton("改变字体");
    private JComboBox<String> numberOfItems;
    private Font fontNow = voteBody.getFont();//get default font so that it is not null
    private ArrayList<String> chooseItems = new ArrayList<>();
    private Messages messages = new Messages();
    private int ignore;
    private int classID;
    private boolean isMultiple = true;//default is multiple
    protected static Font defaultFontForTitle = new Font("black", Font.BOLD, 25);
    private class ChooseItemsMaker extends JFrame {
        private JButton creat_choose = new JButton("OK");
        private JTextField[] textFields;
        private JLabel[] labels;
        int n;

        private ChooseItemsMaker() {
            n = Integer.parseInt(Objects.requireNonNull(numberOfItems.getSelectedItem()).toString());
            setBasic();
        }

        private void setBasic() {
            textFields = new JTextField[n];
            labels = new JLabel[n];
            for (int t = 0; t < n; t++) {
                textFields[t] = new JTextField();
                labels[t] = new JLabel("choose item" + (t + 1));
                labels[t].setPreferredSize(new Dimension(75, 35));
                textFields[t].setColumns(15);
                textFields[t].setPreferredSize(new Dimension(200, 35));
            }
            setCreat_choose();
            addItems();
            setPreferredSize(new Dimension(325, 50 * n + 75));
            setVisible(true);
            setResizable(false);
            pack();
            setLocation(300, 200);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
        }

        private void addItems() {
            JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            jPanel.setPreferredSize(new Dimension(310, 45 * n));
            for (int t = 0; t < n; t++) {
                JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                jPanel1.setPreferredSize(new Dimension(300, 40));
                jPanel1.add(labels[t]);
                jPanel1.add(textFields[t]);
                jPanel.add(jPanel1);
            }
            this.getContentPane().add(jPanel, BorderLayout.NORTH);
            JPanel jPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            jPanel2.setPreferredSize(new Dimension(250, 40));
            jPanel2.add(creat_choose);
            this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
        }

        /**
         * need to change
         */
        private void setCreat_choose() {
            creat_choose.addActionListener(Event -> {
                boolean isOk = true;
                for (int t = 0; t < labels.length; t++) {
                    String now = textFields[t].getText();
                    if (now.equals("")) {
                        isOk = false;
                        break;
                    }
                }
                if (isOk) {
                    for (int t = 0; t < labels.length; t++) {
                        chooseItems.add(textFields[t].getText());
                        System.out.println("stored item with message " + textFields[t].getText());
                    }
                    System.out.println("store message successfully!");
                    setIsSingleN.setEnabled(true);
                    setIsSingleY.setEnabled(true);
                    System.out.println("now you can creat a new vote!");
                    numberOfItems.setEnabled(false);
                    this.dispose();
                } else {
                    JFrame frame = new JFrame("Warning");
                    MyLabelLikeAera warning = new MyLabelLikeAera("warning: " +
                            "there is more than one textfield that is empty"
                            + "\n please check the items you set");
                    warning.setPreferredSize(new Dimension(300, 100));
                    frame.getContentPane().add(warning);
                    frame.setLocation(this.getX() + 100, this.getY() + 100);
                    frame.setSize(300, 100);
                    frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    frame.setVisible(true);
                }
            });
        }

    }

    private void setAddIcon() {
        addIcon.addActionListener(Event -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showOpenDialog(null);
            int y = 1;
            File file = jFileChooser.getSelectedFile();
            char i = 0;
            try {
                ImageIcon insertIcon = new ImageIcon(file.getPath());
                int newWidth = insertIcon.getIconWidth();
                int newHeight = insertIcon.getIconHeight();
                System.out.println("i is" + y + i);
                // zip the size of the icon
                while (newWidth > 400) {
                    newHeight /= 1.2;
                    newWidth /= 1.2;
                }
                insertIcon.setImage(insertIcon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
                //change the line when insert the icon;
                voteBody.insertIcon(insertIcon); // 插入图片
                voteBody.setCaretPosition(voteBody.getStyledDocument().getLength()); // 设置插入位置
                messages.add(new Message(insertIcon, file.getPath()));
                try {
                    voteBody.getDocument().insertString(voteBody.getDocument().getLength(), "\n", new SimpleAttributeSet());// 这样做可以换行
                    System.out.println("tried it...");
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            } catch (NullPointerException ignore) {
            }
        });
    }

    private void setCancel() {
        cancel.setPreferredSize(new Dimension(120, 30));
        cancel.addActionListener(Event -> this.dispose());
    }

    private void setOk() {
        ok.addActionListener(Event -> {
            if (voteBody.getText().equals("")) {
                JFrame j = new JFrame("Warning");
                JLabel jLabel = new JLabel("Vote body is empty!");
                jLabel.setFont(new Font("black", Font.BOLD, 15));
                jLabel.setPreferredSize(new Dimension(100, 60));
                j.add(jLabel);
                j.pack();
                j.setLocation(ok.getX() + 100, ok.getY() + 50);
                j.setVisible(true);
            } else {
                Scanner scanner = new Scanner(voteBody.getText());
                String s = "";
                ignore++;
                ignore++;
                while (scanner.hasNextLine()) s = scanner.nextLine();
                if (!s.equals("")) messages.add(new Message(s + "\n", fontNow));
                JFrame j = new JFrame("Sure");
                JLabel jLabel = new JLabel("If you check, you can not change");
                jLabel.setPreferredSize(new Dimension(200, 40));
                JButton understand = new JButton("understand");
                understand.setPreferredSize(new Dimension(80, 35));
                understand.addActionListener(event -> {
                    j.dispose();
                    ok.setEnabled(false);
                    titleArea.setEditable(false);
                    voteBody.setEditable(false);
                    addIcon.setEnabled(false);
                    changeFont.setEnabled(false);
                    numberOfItems.setEnabled(true);
                });
                jLabel.setFont(new Font("black", Font.BOLD, 10));
                jLabel.setPreferredSize(new Dimension(150, 30));
                j.getContentPane().setLayout(new FlowLayout());
                j.getContentPane().setPreferredSize(new Dimension(175, 100));
                j.getContentPane().add(jLabel);
                j.getContentPane().add(understand);
                j.pack();
                j.setLocation(ok.getX() + 50, ok.getY() + 50);
                j.setVisible(true);
            }
        });
    }

    private void setSetIsSingleYAndN() {
        buttonGroup.setSelected(setIsSingleY.getModel(), true);
        setIsSingleY.setEnabled(false);
        setIsSingleN.setEnabled(false);
        setIsSingleN.addActionListener(Event -> {
            isMultiple = false;
            creat.setEnabled(true);
        });
        setIsSingleY.addActionListener(Event -> {
            isMultiple = true;
            creat.setEnabled(true);
        });
    }

    private void setTextLabel() {
        textLabel.setPreferredSize(new Dimension(80, 35));
    }

    private void setTitleLabel() {
        titleLabel.setPreferredSize(new Dimension(80, 35));
    }

    private void setChooseLabel() {
        chooseLabel.setPreferredSize(new Dimension(80, 35));
    }

    private void setIsSingleLabel() {
        isSingleLabel.setPreferredSize(new Dimension(80, 35));
    }

    private void setTitleArea() {
        titleArea.setLineWrap(true);
        titleArea.setPreferredSize(new Dimension(550, 45));
        titleArea.setFont(new Font("black", Font.BOLD, 25));
    }

    private void setTextPane() {
        voteBody.setPreferredSize(new Dimension(550, 400));
        /*
        check the number of the items
         */
        voteBody.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == 10) {
                    Scanner scanner = new Scanner(voteBody.getText());
                    String s = "";
                    while (scanner.hasNextLine()) s = scanner.nextLine();
                    if (!s.equals("")) messages.add(new Message(s + "\n", fontNow));
                    if (e.isControlDown()) {
                        ok.doClick();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void setScrollPane() {
        jScrollPane = new JScrollPane(voteBody);
    }

    private void setNumberOfItems() {
        String[] data = {"2", "3", "4", "5", "6"};
        numberOfItems = new JComboBox<>(data);
        numberOfItems.setPreferredSize(new Dimension(60, 35));
        numberOfItems.addActionListener(Event -> {
            if (!Objects.requireNonNull(numberOfItems.getSelectedItem()).toString().equals("0")) {
                new ChooseItemsMaker();
            }
        });
        numberOfItems.setEnabled(false);
    }

    private void setChangeFont() {
        changeFont.addActionListener(Event -> {
            System.out.println();
            System.out.println("need to change font " + fontNow);
        });
        /*
        changeFont.addMouseListener(new MouseListener() {
            JFrame jFrame = new JFrame();
            JLabel jLabel = new JLabel("这项操作不会改变当前所编辑的文本的字体");
            JLabel jLabel1 = new JLabel("但是当容显示该投票内时，您的设置将体现");
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                jLabel.setPreferredSize(new Dimension(300, 50));
                jLabel1.setPreferredSize(new Dimension(300, 50));
                jFrame.getContentPane().add(jLabel);
                jFrame.getContentPane().add(jLabel1);
                jFrame.pack();
                jFrame.setLocationRelativeTo(changeFont);
                jFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
                jFrame.setVisible(true);
                //jFrame.setUndecorated(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        */
    }

    private void setCreat() {
        creat.setPreferredSize(new Dimension(120, 30));
        creat.setEnabled(false);
        creat.addActionListener(actionEvent -> {
            title = titleArea.getText();
            for (Message message : messages) {
                if (message.isIcon) {
                    message.ID = DataGetters.uploadFile(message.message, classID, 0);
                }
            }
            for (String s : chooseItems){
                System.out.println("store choose item " + s );
            }
            String[] temp = new String[chooseItems.size()];
            chooseItems.toArray(temp);
            String result =
                    DataGetters.addVote(title, messages.toString(), temp, isMultiple, classID, 0);
            if (result.equals("OK")) {
                this.dispose();
            } else {
                // TODO: POPUP
            }
        });
    }

    private void addItems() {
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(660, 45));
        jPanel.add(titleLabel);
        jPanel.add(titleArea);
        container.add(jPanel);
        JPanel jPanel1 = new JPanel(new FlowLayout());
        JPanel jPanel2 = new JPanel();
        jPanel2.setPreferredSize(new Dimension(80, 400));
        jPanel2.add(textLabel);
        jPanel1.setPreferredSize(new Dimension(660, 400));
        jPanel1.add(jPanel2);
        jPanel1.add(jScrollPane);
        container.add(jPanel1);
        JPanel jPanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jPanel3.setPreferredSize(new Dimension(645, 40));
        JPanel small = new JPanel(new FlowLayout(FlowLayout.LEFT));
        small.setPreferredSize(new Dimension(375, 40));
        small.add(ok);
        jPanel3.add(small);
        jPanel3.add(changeFont);
        jPanel3.add(addIcon);
        ignore++;
        container.add(jPanel3);
        JPanel jPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel4.add(chooseLabel);
        jPanel4.add(numberOfItems);
        jPanel4.setPreferredSize(new Dimension(660, 70));
        container.add(jPanel4);
        buttonGroup.add(setIsSingleN);
        buttonGroup.add(setIsSingleY);
        JPanel sui = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sui.setPreferredSize(new Dimension(660, 40));
        sui.add(isSingleLabel);
        JPanel jPanel4_1 = new JPanel();
        jPanel4_1.add(setIsSingleY);
        jPanel4_1.add(setIsSingleN);
        jPanel4_1.setPreferredSize(new Dimension(500, 40));
        sui.add(jPanel4_1);
        container.add(sui);
        JPanel jPanel5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel5.add(creat);
        jPanel5.add(cancel);
        ignore++;
        container.add(jPanel5);
    }

    VoteMaker(int classID) {
        ignore = 0;
        this.classID = classID;
        container.setLayout(new FlowLayout());
        setCreat();
        setCancel();
        setSetIsSingleYAndN();
        setTextLabel();
        setTitleArea();
        setTextPane();
        setChooseLabel();
        setIsSingleLabel();
        setScrollPane();
        setOk();
        setChangeFont();
        setNumberOfItems();
        setTitleLabel();
        setAddIcon();
        this.setPreferredSize(new Dimension(720, 800));
        addItems();
        pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);

    }

}

class Message {
    String message;
    ImageIcon imageIcon;
    Font font;
    boolean isIcon;
    int ID;

    Message(String message, Font font) {
        this.font = font;
        System.out.println("set font " + font);
        this.message = message;
        isIcon = false;
    }

    Message(ImageIcon imageIcon, String path) {
        this.message = path;
        this.imageIcon = imageIcon;
        isIcon = true;
    }
}
class Messages extends ArrayList<Message> {
    int height;
    int weight;
    private static String defaultIconCode = "\\pお&[icon]德莉莎\\";

    Messages(int classID) {
        height = 0;
        weight = 0;
    }
Messages(){height = 0;
    weight = 0;}
    public boolean add(Message message) {
        if (message.isIcon) {
            height += message.imageIcon.getIconHeight();
            weight = max(weight, message.imageIcon.getIconWidth());
        } else {
            TextSize textSize = new TextSize(message.font);
            weight = max(weight, textSize.getStringSize(message.message)[0]);
            height += textSize.getStringSize(message.message)[1];
        }
        return super.add(message);
    }
//TODO this method up load the file and get its id to download it
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Message message : this) {
            if (message.isIcon) {
                str.append(defaultIconCode).append(message.ID).append("\n");
            } else str.append(message.message).append("\n");
        }
        return str.toString();
    }
    private static int max(int a, int b) {
        if (a > b) return a;
        else return b;
    }
    protected static Messages getMessagesFromString(String messages){
        Scanner scanner = new Scanner(messages);
        String now;
        Messages res = new Messages();
        while (scanner.hasNextLine()){
            now =  scanner.nextLine();
            if (now.length() <= defaultIconCode.length() || !now.substring(0, defaultIconCode.length()).equals(defaultIconCode)) {
                res.add(new Message(now, new Dialog_Plug_in().getFont()));
            } else {
                String path;
                try {
                    path = DataGetters.downloadFile(Integer.parseInt(now.substring(defaultIconCode.length()))).getPath();
                } catch (NullPointerException e){
                    path = "SourcePackage" + File.separator + "Icon"+ File.separator + "ClearIcon.jpg";
                }
                res.add(new Message(new ImageIcon(path), path));
            }

        }
        return res;
    }
}