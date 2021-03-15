package fivechesstwo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class myframe {
	static JFrame jf = new chessboard();
	public static void main(String[] args) {
		myframe frame = new myframe();
		frame.showui();
	}
	public void showui() {
		jf.setSize(900,800);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		JPanel estPanel = new JPanel();
		estPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));//FlowLayout.CENTERΪ���뷽ʽ
		Font font = new Font("����",Font.PLAIN,18);
		//���ð�ť�е�����//����ǵ�д�ɲ���
		//���ð�ť1
		MyActionListener Action1= new MyActionListener();
		MyButton Btu1 = new MyButton("���˶�ս", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu1);
		Btu1.addActionListener(Action1);
		
		//���ð�ť2
		MyButton Btu2 = new MyButton("�˻���ս", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu2);
		Btu2.addActionListener(Action1);
		
		//���ð�ť3
		MyButton Btu3 = new MyButton("����", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu3);
		Btu3.addActionListener(Action1);
		
		//���ð�ť4
		MyButton Btu4 = new MyButton("�˳�", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu4);
		Btu4.addActionListener(Action1);
		
		//���ð�ť5
		MyButton Btu5 = new MyButton("�������", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu5);
		Btu5.addActionListener(Action1);
		
		estPanel.setPreferredSize(new Dimension(120,0));
		estPanel.setBackground(Color.WHITE);
		jf.add(estPanel,BorderLayout.EAST);
		jf.setVisible(true);
		DrawMouse mouse = new DrawMouse();
		Graphics g = jf.getGraphics();
		mouse.gr=g;
		jf.addMouseListener(mouse);
		Action1.Btu1Tmp=Btu1;
		Action1.Btu2Tmp=Btu2;
		Action1.Btu3Tmp=Btu3;
		Action1.Btu4Tmp=Btu4;
		Action1.Btu5Tmp=Btu5;
		Action1.gTmp=g;
	}

}
