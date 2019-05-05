
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.util.ArrayList;

class VoteUI extends JFrame {
    private Message titleMessage;
    private JTextPane voteBody = new JTextPane();
    private JTextArea title = new JTextArea();
    private ArrayList<Message> messages;
    private JScrollPane jScrollPane;
    private JButton OK = new JButton("OK");
    private JButton cancel = new JButton("cancel");
    private int n;
    private Container container = this.getContentPane();
    private VoteUI(Message titleMessage, ArrayList<Message> messages, ArrayList<String> chooseMessage) {
        this.titleMessage = titleMessage;
        this.messages = messages;
        n = chooseMessage.size();
        setVoteBody();
        setTitle();

        setCancel();
        System.out.println("set title " + titleMessage.message);
        for (Message message : this.messages) System.out.println("get message " + message.message);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(720, 600 + 50 * n));
        addItems();
        pack();
        setVisible(true);
    }

    private void setTitle() {
        title.setFont(titleMessage.font);
        title.setText(titleMessage.message);
        title.setPreferredSize(new Dimension(400, 80));
    }

    private void setVoteBody() {
        for (Message message : messages) {
            if (message.isIcon) {
                voteBody.insertIcon(message.imageIcon);
                try {
                    voteBody.getStyledDocument().insertString(voteBody.getStyledDocument().getLength(), "\n",
                            new SimpleAttributeSet());
                } catch (BadLocationException e) {
                    e.printStackTrace();
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
        jScrollPane = new JScrollPane(voteBody);
        jScrollPane.setPreferredSize(new Dimension(550, 400));
    }

    private void addItems() {
        System.out.println("adding!");
        container.setLayout(new FlowLayout());
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel.setPreferredSize(new Dimension(660, 100));
        jPanel.add(title);
        container.add(jPanel);
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel1.setPreferredSize(new Dimension(660, 350));
        jPanel1.add(jScrollPane);
        container.add(jPanel1);
        JPanel jPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel2.setPreferredSize(new Dimension(660, 40 * n + 50));
        container.add(jPanel2);
        JPanel jPanel3  =new JPanel(new FlowLayout(FlowLayout.CENTER));
        jPanel3.setPreferredSize(new Dimension(660, 40));
        jPanel3.add(OK);
        jPanel3.add(cancel);
        container.add(jPanel3);
        //container.add(title);
        //container.add(voteBody);
    }
    private void setCancel(){
        cancel.addActionListener(Event -> this.dispose());
    }

    public static void main(String[] args) {
        new VoteUI(Message.defaultMessage, Message.messages(), Message.chooseItems());
    }
}
class Message{
    String message;
    Font font;
    boolean isIcon = false;
    ImageIcon imageIcon = null;
    static ArrayList <Message> messages(){
        ArrayList <Message> messages = new ArrayList<>();
        messages.add(new Message("ha ha", new JTextPane().getFont()));
        messages.add(new Message("ha", new JTextPane().getFont()));
        messages.add(new Message("hh", new JTextPane().getFont()));
        messages.add(new Message("ha", new JTextPane().getFont()));
        return messages;
    }
    static Message defaultMessage = new Message("default", new JTextPane().getFont());
    static ArrayList<String> chooseItems(){
        ArrayList<String> chooseItems = new ArrayList<>();
        chooseItems.add("h");
        chooseItems.add("no");
        return chooseItems;
    }
    private Message(String message, Font font){
        this.message = message;
        this.font = font;
    }
}