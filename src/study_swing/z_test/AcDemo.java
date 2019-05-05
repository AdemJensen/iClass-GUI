package study_swing.z_test;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AcDemo extends JFrame {
    private BackGround backGround;
    private Random random = new Random();
    private AcDemo(int x, int y){
        backGround = new BackGround(x, y, Color.BLUE);
        this.add(backGround);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }
    private class BackGround extends JPanel {
        int x;
        int y;
        int[][][] pointData;
        Color color1 = new Color(252, 255, 255, 200);//default color
        Color color2 = new Color(255, 255, 255, 200), color3, color4;
        Color[] colors = {color1, color2, color3, color4};


        BackGround(int x, int y) {
            setBasic(x, y);
        }

        BackGround(int x, int y, Color color) {
            setBasic(x, y);
            this.color1 = color;
           // this.paintComponent();
        }

        private void setBasic(int x, int y) {
            this.x = x;
            this.y = y;
            this.setOpaque(false);
            pointData = new int[x][y][3];
            this.setPreferredSize(new Dimension(x, y));
        }

        public void paintComponent(Graphics graphics) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            for (int t = 0; t < x; t++) {
                for (int p = 0; p < y; p++) {
                    PointWithColor pointWithColor = new PointWithColor(t, p);
                    graphics2D.setColor(pointWithColor.color);
                    graphics2D.fill(pointWithColor.rectangle);
                }
            }
        }

        private Rectangle pointRec(int x, int y) {
            return new Rectangle(x, y, 1, 1);
        }

        /*
        use for small point case 1 and big point case2
         */
        private double Power1(int t, int n) {
            double o_mi_ga = 4 * Math.PI / n;
            double bi_an = t + 0.375 * n;
            return (Math.sin(bi_an * o_mi_ga) + 1) / 2;
        }
        /*
        use for small point case2
         */
        private double Power2(int t, int n) {
            double o_mi_ga = 4 * Math.PI / n / 3;
            double bi_an = t + 0.125 * n;
            return (Math.sin(bi_an * o_mi_ga) + 1) / 2;
        }
        /*
        use for big point case1
         */
        private double Power3(int t, int n) {
            double o_mi_ga = 4 * Math.PI / n / 3;
            double bi_an = t - 0.375 * n;
            return (Math.sin(bi_an * o_mi_ga) + 1) / 2;
        }
        private int toRGBInt(double x){return (int)(x * 255);}
        private class PointWithColor{
            Color color;
            Rectangle rectangle;
            PointWithColor(int x_point, int y_point){
                double right_x1, right_y1, right_x2, right_y2;
                if (x_point >= 0.75 * x) {
                    right_x1 = Power2(x_point,x);
                    right_x2 = Power1(x_point, x);
                }
                else if (x_point >= 0.25 * x && x_point < 0.75 * x){
                    right_x1 = Power2(x_point, x);
                    right_x2 = Power3(x_point, x);
                }else {
                    right_x1 = Power1(x_point, x);
                    right_x2 = Power3(x_point, x);
                }
                if (y_point >= 0.75 * y) {
                    right_y1 = Power2(y_point,y);
                    right_y2 = Power1(y_point, y);
                }
                else if (y_point >= 0.25 * y && y_point < 0.75 * y){
                    right_y1 = Power2(y_point, y);
                    right_y2 = Power3(y_point, y);
                }else {
                    right_y1 = Power1(y_point, y);
                    right_y2 = Power3(y_point, y);
                }

                int rx = (toRGBInt(right_x1) + toRGBInt(right_x2)) / 2;
                int ry = (toRGBInt(right_y1) + toRGBInt(right_y2)) / 2;
                color = new Color(rx, ry, 255);
                rectangle = pointRec(x_point, y_point);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            var t = new AcDemo(720, 720);

            Thread thread = new Thread(() ->{
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    t.backGround.paintComponent(t.backGround.getGraphics());
                    try {
                        System.out.println("ok");
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

        });
    }
}
