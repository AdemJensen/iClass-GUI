package study_swing.Document;

import study_swing.DataGetters;
import study_swing.MyNoBackgroundButton;
import study_swing.smallToolTip;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DocumentInterface extends JFrame {
    private Container container = this.getContentPane();
    private int numberOfDocument;
    private MyNoBackgroundButton search = new MyNoBackgroundButton();
    private MyNoBackgroundButton checkMession = new MyNoBackgroundButton();
    private MyNoBackgroundButton multipulChoose = new MyNoBackgroundButton();// this button will try to select all the document in need
    private MyNoBackgroundButton uploadFile = new MyNoBackgroundButton();// this button will up this file that be chosen
    private JLabel showNumber = new JLabel();
    private JTextField getSearchMessage = new JTextField();
    private JPanel topPanel = new JPanel(new FlowLayout());
    private JPanel secondPanel = new JPanel(new FlowLayout());
    JPanel documentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public int classid;
    private int ignore;

    public DocumentInterface(int classid) {
        container.setLayout(new FlowLayout());
        setTopPanel();
        setCheckMission();
        setMultipleChoose();
        setGetSearchMessage();
        setSearch();
        setUpFile();
        setSecondPanel();
        setDocumentPanel();
        addItems();
        this.classid = classid;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(1000, 750);
    }

    private void setShowNumber() {
        String mes = "列表中有" + numberOfDocument + "个文件";
        showNumber.setText(mes);
        showNumber.setPreferredSize(new Dimension(300, 35));
    }

    private void setGetSearchMessage() {
        getSearchMessage.setPreferredSize(new Dimension(150, 35));
        getSearchMessage.setColumns(15);
    }//default 15 columns

    private void setMultipleChoose() {
        ImageIcon mul = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "multipleMessionModel.jpg");
        mul.setImage(mul.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        multipulChoose.setIcon(mul);
        multipulChoose.setToolTipText("单击以多选");
        multipulChoose.setPreferredSize(new Dimension(35, 35));
        multipulChoose.addActionListener(Event -> {

        });
    }

    private void setCheckMission() {
        ImageIcon check = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "checkmession.jpg");
        check.setImage(check.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        checkMession.setIcon(check);
        checkMession.setPreferredSize(new Dimension(35, 35));
        checkMession.setToolTipText("click to check the mession ");
        checkMession.addActionListener(Event -> {
        });
    }

    private void setSearch() {
        ImageIcon s = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "searchLeftSide.jpg");
        s.setImage(s.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        search.setIcon(s);
        search.setToolTipText("搜索您想要的文件");
        search.setPreferredSize(new Dimension(35, 35));
        search.addActionListener(Event -> {
            setDocumentPanel();
            getSearchMessage.setText("");
        });
    }

    private void setUpFile() {
        ImageIcon upload = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "upload.jpg");
        upload.setImage(upload.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
        uploadFile.setIcon(upload);
        uploadFile.setToolTipText("上传您的文件");
        uploadFile.setPreferredSize(new Dimension(35, 35));
        uploadFile.addActionListener(Event -> {
            try {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showOpenDialog(null);
                File file = jFileChooser.getSelectedFile();
                if (file != null){
                    int result = DataGetters.uploadFile(file.getPath(), classid, 0);
                    if (result != -1){
                        this.dispose();
                        new DocumentInterface(classid).setVisible(true);
                        new smallToolTip("成功啦！", "您已上传成功文件" + file.getName() + "╭(●｀∀´●)╯" + "快去和小伙伴们分享吧！");
                    } else {
                        new smallToolTip("唔，出错啦...", result + "\n π__π");
                    }
                }
            }catch (NullPointerException ignore){}
        });
    }

    private void setTopPanel() {
        topPanel.setPreferredSize(new Dimension(950, 45));
        topPanel.setBackground(new Color(179, 180, 178, 68));
        topPanel.add(showNumber);
        var topPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel1.setBackground(new Color(179, 180, 178, 68));
        topPanel1.setBorder(null);
        topPanel1.setOpaque(false);
        topPanel1.setPreferredSize(new Dimension(640, 40));
        topPanel1.add(search);
        topPanel1.add(getSearchMessage);
        topPanel1.add(multipulChoose);
        ignore++;
        topPanel1.add(checkMession);
        topPanel1.add(uploadFile);
        ignore += 5;// no sense
        topPanel.add(topPanel1);
    }

    private void setSecondPanel() {
        secondPanel.setPreferredSize(new Dimension(950, 40));
        secondPanel.setSize(950, 40);
        JLabel doc = new JLabel("文件名");
        JLabel time = new JLabel("上传日期");
        JLabel size = new JLabel("大小");
        JLabel owner = new JLabel("上传者");
        doc.setPreferredSize(new Dimension(425, 35));
        time.setPreferredSize(new Dimension(200, 35));
        size.setPreferredSize(new Dimension(175, 35));
        secondPanel.add(size);
        owner.setPreferredSize(new Dimension(125, 35));
        secondPanel.setBackground(Color.GRAY);
        secondPanel.add(doc);
        secondPanel.add(time);
        secondPanel.add(owner);
        // the percentage is 400 : 175 : 150 : 100 : 100
        // so as is 16 : 7 : 6  : 4 : 4 added is 38
    }

    private void setDocumentPanel() {
        documentPanel.setPreferredSize(new Dimension(950, 600));
        documentPanel.setBackground(new Color(255, 255, 255));
        var mes = getMessages(getSearchMessage.getText());//search the message
        setShowNumber();
        if (mes.length == 0) {
            JLabel no = new JLabel("没有文件可以显示 π__π");
            JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            centerPanel.setPreferredSize(new Dimension(940, 500));
            no.setPreferredSize(new Dimension(350, 100));
            no.setFont(new Font("black", Font.BOLD, 30));
            centerPanel.add(no);
            documentPanel.add(centerPanel);
        } else setEachDocMes(mes);
    }

    void setEachDocMes(DocumentMessage[] docMes) {
        for (DocumentMessage me : docMes) {
            // need to show the document view
            me.setPreferredSize(new Dimension(950, 40));
            documentPanel.add(me);
        }
    }

    private void addItems() {
        container.add(topPanel);
        container.add(secondPanel);
        JScrollPane jScrollPane = new JScrollPane(documentPanel);
        container.add(jScrollPane);
    }

    private void setNumberOfDocument(int numberOfDocument) {
        this.numberOfDocument = numberOfDocument;
    }

    private DocumentMessage[] getMessages(String keyMessage) {
        //System.out.println("key mes is " + keyMessage);
        DocumentMessage[] documentMessages = DocumentMessage.getMessagesFromFileInfo(classid);
        setNumberOfDocument(documentMessages.length);
        return documentMessages;
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var a = new DocumentInterface(0);
            System.out.println(a.ignore);
        });
    }
}
/*
a fucking unknown bug: if i set a border for DocMess, i will face the null pointer explosion, however, the system told me there is sth null at set Visible...
god holy shit what the fuck! i just set A border!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
SHIT!
SHIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */