package study_swing.Chat;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

//文件类 （文件的打开、新建、保存）
class FileClass {
    private DrawPad drawpad;
    private DrawArea drawarea;
    private singleChatFrame relate;
    private static int picture = 0;
    FileClass(DrawPad dp, DrawArea da, singleChatFrame relate) {
        drawpad = dp;
        drawarea = da;
        this.relate = relate;
    }

    void newFile() {
        // TODO 新建图像
        /*
        drawarea.setIndex(0);
        drawarea.setColor(Color.black);//设置颜色
        drawarea.setStroke(1.0f);//设置画笔的粗细
        */
        drawarea.createNewItem();
        drawarea.repaint();
    }

    void openFile() {
        // TODO 打开图像

        //JFileChooser 为用户选择文件提供了一种简单的机制
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		  /* FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "JPG & GIF Images", "jpg", "gif");//其中只显示 .jpg 和 .gif 图像
		   filechooser.setFileFilter(filter);*/
        int returnVal = filechooser.showOpenDialog(drawpad);

        if (returnVal == JFileChooser.CANCEL_OPTION) {//如果单击确定按钮就执行下面得程序
            return;
        }
        File fileName = filechooser.getSelectedFile();//getSelectedFile()返回选中的文件

        if (fileName == null || fileName.getName().equals("") || !fileName.canRead())//文件名不存在时 or can not be read
        {
            JOptionPane.showMessageDialog(filechooser, "文件名", "请输入文件名！", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                FileInputStream ifs = new FileInputStream(fileName);
                ObjectInputStream input = new ObjectInputStream(ifs);

                int countNumber;
                Drawing inputRecord;
                countNumber = input.readInt();
                for (int i = 0; i < countNumber; i++) {
                    drawarea.setIndex(i);
                    inputRecord = (Drawing) input.readObject();
                    drawarea.itemList[i] = inputRecord;
                }
                drawarea.createNewItem();
                input.close();
                drawarea.repaint();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(drawpad, "没有找到源文件！", "没有找到源文件", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(drawpad, "读文件是发生错误！", "读取错误", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(drawpad, "不能创建对象！", "已到文件末尾", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    //保存图像文件程序段，用到文件对象流（FileOupPutStream）
    void saveFile() {
        // TODO 保存图像
        //JFileChooser 为用户选择文件提供了一种简单的机制
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //setFileSelectionMode()设置 JFileChooser，以允许用户只选择文件、只选择目录，或者可选择文件和目录。
        int result = filechooser.showSaveDialog(drawpad);
        if (result == JFileChooser.CANCEL_OPTION) {
            return;
        }

        File fileName = filechooser.getSelectedFile();//getSelectedFile()返回选中的文件
        //测试应用程序是否可以修改此抽象路径名表示的文件
        fileName.canWrite();
        if (fileName.getName().equals(""))//文件名不存在时
        {
            JOptionPane.showMessageDialog(filechooser, "文件名", "请输入文件名！", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                fileName.delete();//删除此抽象路径名表示的文件或目录
                FileOutputStream fos = new FileOutputStream(fileName + ".xxh");//文件输出流以字节的方式输出
                //对象输出流
                ObjectOutputStream output = new ObjectOutputStream(fos);
                //Drawing record;

                output.writeInt(drawarea.getIndex());

                for (int i = 0; i < drawarea.getIndex(); i++) {
                    Drawing p = drawarea.itemList[i];
                    output.writeObject(p);
                    output.flush();
                    //刷新该流的缓冲。此操作将写入所有已缓冲的输出字节，并将它们刷新到底层流中。
                    //将所有的图形信息强制的转换成父类线性化存储到文件中
                }
                output.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    void sendDrawPad() throws IOException {
        Dimension imageSize = this.drawarea.getSize();
        BufferedImage image = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        this.drawarea.paint(graphics);
        graphics.drawImage(image, 0, 0 ,null);
        graphics.dispose();
        File f = new File(this.getClass() + "Chat" + ++picture + ".jpg");//default is jpg mode
        System.out.println(f.getPath());
        if (!f.exists()) {
            System.out.println(f.createNewFile());
            System.out.println(404);
        }
        ImageIO.write(image, "jpg", f);//write the image icon in the file
        relate.insertIcon(new ImageIcon(f.getPath()), f.getPath());
    }
}
