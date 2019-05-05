package study_swing.Chat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
//ͼ�λ����� ���ڻ��Ƹ���ͼ��
//���࣬����ͼ�ε�Ԫ���õ����еĽӿڣ�����ʹ�õ�
//���������Էŵ������У�������Ա����ظ�����

/*��ͨ��ʵ�� java.io.Serializable �ӿ������������л����ܡ�
δʵ�ִ˽ӿڵ��ཫ�޷�ʹ���κ�״̬���л������л���
�����л�������������ͱ����ǿ����л��ġ����л��ӿ�û�з������ֶΣ�
�����ڱ�ʶ�����л������塣*/


abstract class Drawing implements Serializable {

int x1,x2,y1,y2;   	    //������������
int  R,G,B;				//����ɫ������
float stroke ;			//����������ϸ������
int type;				//������������
String s1;				//��������ķ��
String s2;				//��������ķ��

abstract void draw(Graphics2D g2d );//�����ͼ����
}

class Line extends Drawing//ֱ����
{
	void draw(Graphics2D g2d) {
		g2d.setPaint(new Color(R, G, B));// Ϊ Graphics2D ���������� Paint ���ԡ�
		// ʹ��Ϊ null �� Paint ������ô˷����Դ� Graphics2D �ĵ�ǰ Paint ����û���κ�Ӱ�졣

		g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_BEVEL));
		// setStroke(Stroke s)Ϊ Graphics2D ���������� Stroke
		// BasicStroke �ඨ�����ͼ��ͼԪ�����������Ե�һ����������
		// BasicStroke.CAP_ROUNDʹ�ð뾶���ڻ��ʿ��һ���Բ��װ�ν���δ��յ���·���������߶�
		// BasicStroke.JOIN_BEVELͨ��ֱ�����ӿ�����������ǣ���·���߶�������һ��
		g2d.drawLine(x1, y1, x2, y2);// ��ֱ��
		
	}
}
class Rect extends Drawing{//������
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
	}
}

class fillRect extends Drawing{//ʵ�ľ�����
   void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
	}
}

class Oval extends Drawing{//��Բ��
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
	}
}

class fillOval extends Drawing{//ʵ����Բ��
    void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
	}
}

class Circle extends Drawing{//������
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.max(Math.abs(x1-x2),
				Math.abs(y1-y2)), Math.max(Math.abs(x1-x2), Math.abs(y1-y2)));
	}
}

class fillCircle extends Drawing{//ʵ��Բ��
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.max(Math.abs(x1-x2),
				Math.abs(y1-y2)), Math.max(Math.abs(x1-x2), Math.abs(y1-y2)));
	}
}

class RoundRect extends Drawing{//Բ�Ǿ�����
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.drawRoundRect(Math.min(x1, x2), Math.min(y1, y2),Math.abs(x1-x2), Math.abs(y1-y2),50,35);
	}
}

class fillRoundRect extends Drawing{//ʵ��Բ�Ǿ�����
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		g2d.setStroke(new BasicStroke(stroke));
		g2d.fillRoundRect(Math.min(x1, x2), Math.min(y1, y2),Math.abs(x1-x2), Math.abs(y1-y2),50,35);
	}
}
class Word extends Drawing{//����������
    void draw(Graphics2D g2d ){
        g2d.setPaint(new Color(R,G,B));
        g2d.setFont(new Font(s2,x2+y2,((int)stroke)*18));//��������
        if(s1 != null)
            g2d.drawString( s1, x1,y1);
    }
}
class Pencil extends Random{//��ʻ���
	void draw(Graphics2D g2d ){
		g2d.setPaint(new Color(R,G,B));
		super.draw(g2d);
	}
}

class Cloth extends Random{
    void draw(Graphics2D g2d ){
        g2d.setPaint(new Color(255,255,255));//��ɫ
        stroke += 4;
        g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
        super.draw(g2d);
    }
}


class Rubber extends Random{//��Ƥ����
    void draw(Graphics2D g2d ){
        g2d.setPaint(new Color(255,255,255));//��ɫ
        g2d.setStroke(new BasicStroke(stroke + 8, BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
        g2d.drawLine(x1, y1,x2, y2);
    }
}


class Random extends Drawing{
    void draw(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL));
        g2d.drawLine(x1, y1,x2, y2);
    }
}