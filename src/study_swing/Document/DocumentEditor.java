package study_swing.Document;

import study_swing.MyNoBackgroundButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DocumentEditor extends DocumentInterface{
    public DocumentEditor(int classID){super(classID);

    this.setSize(1000, this.getHeight());}
    void setEachDocMes(DocumentMessage[] docMes){
        for (DocumentMessage me : docMes) {
            // need to show the document view
            me.setPreferredSize(new Dimension(950, 40));//leave a area for check
            ImageIcon cancel = new ImageIcon("SourcePackage" + File.separator + "Icon" + File.separator + "cancel.jpg");
            cancel.setImage(cancel.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
            MyNoBackgroundButton m = new MyNoBackgroundButton(cancel);
            documentPanel.add(me);
            documentPanel.add(m);
            m.addActionListener(e ->{

            });
        }
    }

    public static void main(String[] args) {
        try {
            new DocumentEditor(0);
        }catch (NullPointerException ignore){}
    }


}
