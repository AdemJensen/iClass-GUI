package study_swing;
import javax.swing.JPanel;
import java.awt.*;
/**
 * 图片面板,窗体需要加背景图片时用
 * @author waitatlee@163.com
 */
public class BackgroundPanel extends JPanel {
    private int width = 0;
    private int height = 0;
    private String imgPath = "";
    /**
     * @param _width   整型,窗口的宽度
     * @param _height  整型,窗口的高度
     * @param _imgPath 图片的URL,可用相对路径
     */
    public BackgroundPanel(int _width, int _height, String _imgPath) {
        width = _width;
        height = _height;
        imgPath = _imgPath;
        setSize(width, height);
        setVisible(true);
    }
    /**
     * @param _width   浮点型,窗口的宽度
     * @param _height  浮点型,窗口的高度
     * @param _imgPath 字符串,图片的URL,可用相对
     */
    public BackgroundPanel(double _width, double _height, String _imgPath) {
        width = (int) _width;
        height = (int) _height;
        imgPath = _imgPath;
        setSize(width, height);
        setVisible(true);
    }
    @Override
    public void paintComponent(Graphics gs) {
        Graphics2D g = (Graphics2D) gs;
        super.paintComponent(g);
        //画背景图片
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imgPath));
        g.drawImage(image, 0, 0, width, height, this);
    }
    /**
     * 监听最外层窗口的resize事件,并根据新的窗口大小来调整背景图片的尺寸
     * @param evt
     */
}