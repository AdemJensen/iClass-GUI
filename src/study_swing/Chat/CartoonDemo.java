package study_swing.Chat;

import javax.swing.*;
import java.awt.*;
/**
this pro gives a cartoon interface to show the main Chat
 */
public class CartoonDemo extends JFrame {
    private BackGround backGround;
    private CartoonDemo(int width, int height){
        backGround = new BackGround(width, height);
        this.add(backGround);
        this.setResizable(false);
        pack();
        this.setVisible(true);
    }
    private class BackGround extends JLabel{
        int width;
        int height;
        int power_x = 100;
        int power_y = 200;
        boolean right_x = true, upy = true;
        boolean right = true;
        boolean down = false;
        private Point point;
        private BackGround(int width, int height) {
            this.height = height;
            this.width = width;
            this.setPreferredSize(new Dimension(width, height));
            point = new Point(this.height, 0);//as a start
        }
        public void paintComponent(Graphics graphics){
            for (int t = 0; t < 100; t++ ) DrawNewLine((Graphics2D)graphics);
        }
        private void DrawNewLine(Graphics2D graphics2D){
            int b = point.y - point.x;
            movePointAndChangeColorPower();
            graphics2D.setStroke(new BasicStroke(3));
            graphics2D.setColor(new Color(power_x, power_y, 255, 255));
            if (b < 0){
                graphics2D.drawLine(-b, 0, width, b + height);
            }else {
                graphics2D.drawLine(0, b, width - b, height);
            }
        }
        private void movePointAndChangeColorPower(){
            if (point.x == width) right = false;
            else if (point.x == 0) right = true;
            if (point.y == height) down = false;
            else if (point.y == 0) down = true;
            if (right) point.x ++;
            else point.x--;
            if(down) point.y++;
            else point.y--;


            if (power_x == 0) right_x = true;
            else if (power_x == 255) right_x = false;
            if (power_y == 0) upy = true;
            else if (power_y == 255) upy = false;
            if (right_x) power_x++;
            else power_x--;
            if (upy) power_y++;
            else power_y--;
        }


    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            CartoonDemo cartoonDemo = new CartoonDemo(800, 800);
            System.out.println("ok!");
            Thread thread = new Thread(() ->{
                for (; true;){
                    for (int i = 0; i < 5; i++) cartoonDemo.backGround.DrawNewLine((Graphics2D) cartoonDemo.backGround.getGraphics());
                }
            });
            thread.start();
        });
    }
}
