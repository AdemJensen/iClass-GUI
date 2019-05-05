package study_swing.Vote;

import study_swing.MyLabelLikePane;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

abstract class VoteUI extends JFrame {
    private Message titleMessage;
    MyLabelLikePane voteBody;
    MyLabelLikePane title;
    JTextArea suggestion = new JTextArea();
    private ArrayList<Message> messages;//set the choose firstly
    ArrayList<String> chooseMessage;
    Container container = this.getContentPane();
    JButton OK = new JButton("确认");
    JButton cancel = new JButton("取消");
    protected JPanel choosePanel = new JPanel();
    int n;

    VoteUI(Message titleMessage, Messages messages, ArrayList<String> chooseMessage) {
        this.titleMessage = titleMessage;
        this.messages = messages;
        this.chooseMessage = chooseMessage;
        container.setLayout(new FlowLayout());
        n = chooseMessage.size();
        setVoteBody();
        setTitle();
        setSuggestion();
        //addItems();
        setCancel();
        setOK();
        System.out.println("set title " + titleMessage);
        for (Message message : messages) System.out.println(message.message);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
    }

    protected static ArrayList<String> getArray(String[] c) {
        ArrayList<String> res = new ArrayList<>();
        Collections.addAll(res, c);
        return res;
    }

    private  void setSuggestion(){
        suggestion.setLineWrap(true);
        suggestion.setPreferredSize(new Dimension(300, 200));
    }
    private void setTitle() {
        title = new MyLabelLikePane(titleMessage.message);
        title.setFont(titleMessage.font);
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributeSet, StyleConstants.ALIGN_CENTER);
        var doc = title.getStyledDocument();
        doc.setParagraphAttributes(0, 0, attributeSet, false);
        title.setPreferredSize(new Dimension(600, 50));
    }

    private void setVoteBody() {
        //TODO show the picture
        voteBody = new MyLabelLikePane();
        voteBody.setPreferredSize(new Dimension(600, 300));
        for (Message message : messages) {
            if (message.isIcon) {
                try {
                    voteBody.insertIcon(message.imageIcon);
                    try {
                        voteBody.getStyledDocument().insertString(voteBody.getStyledDocument().getLength(), "\n",
                                new SimpleAttributeSet());
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                } catch (NullPointerException ignore) {
                }
            } else {
                try {
                    voteBody.getStyledDocument().insertString(voteBody.getStyledDocument().getLength(), message.message + '\n',
                            new SimpleAttributeSet());
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
        }
        JScrollPane jScrollPane = new JScrollPane(voteBody);
        jScrollPane.setPreferredSize(new Dimension(600, 400));
    }

    void addItems() {
        container.setPreferredSize(new Dimension(720, 600 + 45 * n));
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.add(title);
        container.add(voteBody);
    }

    abstract void setOK();

    abstract void setSelectItems();

    private void setCancel() {
        cancel.addActionListener(Event -> this.dispose());
    }

}
