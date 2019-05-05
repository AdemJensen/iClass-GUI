package study_swing.Document;

import study_swing.DataGetters;
import study_swing.Dialog_Plug_in;
import study_swing.smallToolTip;
import top.chorg.kernel.communication.api.file.FileInfo;
import top.chorg.support.Timer;
import top.chorg.system.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

class DocumentMessage extends JPanel implements MouseListener {
    //remember the precent : so as is 16 : 7 : 6  : 4 : 4 added is 38
    private int id;
    private String size;
    private String name;
    private String upLoaderName;
    private String upLoadTime;

    private void setBasic() {
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.addMouseListener(this);
    }

    private DocumentMessage(FileInfo fileInfo) {
        setBasic();
        this.name = fileInfo.name;
        String[] username = DataGetters.getUserName(new int[]{id});
        this.upLoaderName = username[0];
        this.upLoadTime = fileInfo.date.toString();
        long length = fileInfo.size.longValue();
        if (length > 1024) {
            double size = (length * 1.0 / 1024);
            if (size > 1024) {
                size /= 1024;
                this.size = String.format("%.2fMB", size);
            } else this.size = String.format("%.2fKB", size);
        } else this.size = size + "B";
        this.id = fileInfo.id;

    }

    public void setPreferredSize(Dimension dimension) {
        super.setPreferredSize(dimension);
        int width = dimension.width;
        int height = dimension.height;
        JLabel s = new JLabel(size + "");
        s.setPreferredSize(new Dimension(width * 7 / 38, height));
        this.add(s);
        JLabel n = new JLabel(name);
        n.setPreferredSize(new Dimension(width * 17 / 38, height));
        this.add(n);
        JLabel uT = new JLabel(upLoadTime);
        uT.setPreferredSize(new Dimension(width * 8 / 38, height));
        this.add(uT);
        JLabel uN = new JLabel(upLoaderName);
        uN.setPreferredSize(new Dimension(width * 5 / 38, height));
        this.add(uN);
    }

    private void downLoad() {
        File file = DataGetters.downloadFile(id);
        if (file != null) {
            new smallToolTip("成功下载！", "文件已下载至" + file.getPath() + " (●´∀｀●)" + "快去看看吧！");
        } else {
            // 弹窗，信息是result
            new smallToolTip("下载失败！", "Σ(っ °Д °;)っ");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        downLoad();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setBackground(new Color(200, 200, 200));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.setBackground(new Color(255, 255, 255));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    static DocumentMessage[] getMessagesFromFileInfo(int classID) {
        FileInfo[] fileInfos = DataGetters.getFileList(classID);
        DocumentMessage[] documentMessages = new DocumentMessage[fileInfos.length];
        for (int t = 0; t < fileInfos.length; t++) {
            documentMessages[t] = new DocumentMessage(fileInfos[t]);
        }
        return documentMessages;
    }
}