package study_swing.announecment;

import study_swing.Dialog_Plug_in;

import java.awt.*;

public class noBackgroundPane extends Dialog_Plug_in {
    noBackgroundPane(boolean isEditable) {
        this.setBackground(new Color(1, 1, 1));
        this.setBorder(null);
        this.setOpaque(false);
        this.setEditable(isEditable);
    }
}
