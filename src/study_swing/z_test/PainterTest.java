package study_swing.z_test;

import study_swing.Chat.DrawPad;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
class MyCanvas extends JPanel implements MouseListener, MouseMotionListener{
    private int width = 300;
    private int height = 300;
    private int x = 0;
    private int y = 0;
    private ArrayList<Integer[]> myList = new ArrayList<>();
    MyCanvas()
    {
        super();
        this.setSize(width, height);
        this.setBounds(new Rectangle(0,0,450,300));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    public void paint(Graphics graphics){
        super.paint(graphics);
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(10));
        for (Integer[] integers : myList) {
            g2d.drawLine(integers[0], integers[1],
                    integers[2], integers[3]);
        }
        g2d.dispose();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        myList.add(new Integer[]{this.x,this.y,e.getX(),e.getY()});
        this.x = e.getX();
        this.y = e.getY();
        this.repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        this.x = e.getX();
        this.y = e.getY();
        //首次的点击采集点
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
class MainofFrame extends JFrame {
    private static int p = 0;
    private int width = 450;
    private int height = 450;
    private MyCanvas canvas;
    private JButton save;
    MainofFrame()
    {
        super();
        this.setTitle("paint");
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocation(400, 180);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.canvas = new MyCanvas();
        this.add(canvas);
        setSave();
        this.add(save);
        this.setVisible(true);
    }
    private void setSave(){
        this.save = new JButton();
        this.save.setText("save");
        this.save.setBounds(40, 360, 80, 30);
        this.save.addActionListener(e -> {
            try {
                savePic();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
    private void savePic() throws IOException {
        Dimension imageSize = this.canvas.getSize();
        BufferedImage image = new BufferedImage(imageSize.width,imageSize.height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        this.canvas.paint(graphics);
        graphics.dispose();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage myImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics1 = myImage.getGraphics();
        graphics1.drawImage(newImage, 0, 0, null);
        graphics1.dispose();//对图片调整大小
        File f=new File("study_swing" + File.separator + "z_test" + File.separator + "PainterTest" + ++p + ".jpg");
        if( !f.exists() )
        {
            System.out.println(f.createNewFile());
            System.out.println(123);
        }
        ImageIO.write(myImage, "jpg",f);
    }
}
class PainterTest{
    public static void main(String[] args) {
    }
}