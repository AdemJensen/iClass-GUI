package study_swing;


import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
/**
 * 下面开始分享一下如何用Java Swing实现的仿QQ气泡消息聊天窗口效果。
 * 针对这个截图的测试程序窗口，我把它分成上下两个部分，采用JSplitter，
 * 下面是输入消息的区域，上面是显示消息的区域。这两个区域都是采用JTextPane的派生子类实现的。
 * \我的逻辑是，先定义输入消息区域JTextPane子类，类名为JIMSendTextPane，
 * 然后再基于JIMSendTextPane派生一个新的子类JIMHistoryTextPane作为显示消息区域。
 * 两个JTextPane的派生子类都采用JScrollPane放置，并将各自的JScrollPane设置为水平滚动条永远禁止，
 * 垂直滚动条按需出现。为什么要创建JTextPane的派生子类，
 * 主要是考虑到Java自带的JTextPane存在一个问题。我们知道默认情况下，
 * 如果JTextPane所在的JScrollPane禁止水平滚动条出现时，
 * JTextPane是具备基于单词（Word）为最小单位的自动换行功能的。
 * 对于中文而言，最小单位就是一个汉字；
 * 对于英文或拉丁语言而言，就是以空格为单位的字母组合。
 * 但是有一个情况，如果你一直输入英文字母，中间不空格，
 * 那么JTextPane就会认为这是一个很长的单词，
 * 一直没有结束，那么它是不会自动换行的。
 * 这显然是不符合我们要求的。
 * 为了能够一劳永逸地解决这个问题，
 * 使JTextPane能够在任何情况下都能对超出宽度的内容进行自动换行，
 * 就需要对JTextPane进行派生子类定义，对其内部负责换行的地方做些修改。具体代码如下：

/**
 * 该类是真正实现超长单词都能自动换行的 JTextPane 的子类
 * Java 7 以下版本的 JTextPane 本身都能实现自动换行，对
 * 超长单词都能有效，但从 Java 7 开始读超长单词就不能自动
 * 换行，导致 JTextPane 的实际宽度变大，使得滚动条出现。
 * 下面的方法是对这个 bug 的较好修复。
 *
 * Created by dolphin on 15-2-3.
 */
public class Dialog_Plug_in extends JTextPane {
    //-----------------------------------------------------------------
    // 内部类
    // 以下内部类全都用于实现自动强制折行
    //-----------------------------------------------------------------
    private class WarpEditorKit extends StyledEditorKit {
        private ViewFactory defaultFactory = new WarpColumnFactory();
        @Override
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }
    }

    private class WarpColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                switch (kind) {
                    case AbstractDocument.ContentElementName:
                        return new WarpLabelView(elem);
                    case AbstractDocument.ParagraphElementName:
                        return new ParagraphView(elem);
                    case AbstractDocument.SectionElementName:
                        return new BoxView(elem, View.Y_AXIS);
                    case StyleConstants.ComponentElementName:
                        return new ComponentView(elem);
                    case StyleConstants.IconElementName:
                        return new IconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }
    }

    private class WarpLabelView extends LabelView {

        WarpLabelView(Element elem) {
            super(elem);
        }

        @Override
        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }
    }

    // 本类

    // 构造函数
    public Dialog_Plug_in() {
        super();
        this.setEditorKit(new WarpEditorKit());
    }
}