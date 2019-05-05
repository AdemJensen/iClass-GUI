package study_swing.Chat;

import javax.swing.*;
import java.awt.*;

public class ChatPane extends JPanel {
    Container father;
    int height = 0;

    public ChatPane(Container father) {
        this.father = father;
    }

    @Override
    public void paint(Graphics g) {
        this.height = 0;
        for (Component component : getComponents()) {
            this.height += component.getHeight();
        }
        this.setPreferredSize(new Dimension(
                father.getWidth() - 30,
                height + 100
        ));
        super.paint(g);
    }

    @Override
    public void repaint(Rectangle r) {
        this.height = 0;
        for (Component component : getComponents()) {
            this.height += component.getHeight();
        }
        this.setPreferredSize(new Dimension(
                father.getWidth() - 30,
                height + 100
        ));
        super.repaint(r);
    }
}
