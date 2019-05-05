package study_swing;


import javax.swing.*;
import java.awt.*;

import static sun.font.FontDesignMetrics.getMetrics;

public class TextSize {
    private FontMetrics fontMetrics;
    public TextSize(Font f ){
        this.fontMetrics = getMetrics(f);
    }
    public int[] getStringSize(String s){
        int[] ans = new int[2];
        ans[0] = fontMetrics.stringWidth(s);
        ans[1] = fontMetrics.getHeight();
        return ans;
    }
}
