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
		estPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));//FlowLayout.CENTER为对齐方式
		Font font = new Font("隶书",Font.PLAIN,18);
		//设置按钮中的字体//后面记得写成博客
		//设置按钮1
		MyActionListener Action1= new MyActionListener();
		MyButton Btu1 = new MyButton("人人对战", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu1);
		Btu1.addActionListener(Action1);
		
		//设置按钮2
		MyButton Btu2 = new MyButton("人机对战", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu2);
		Btu2.addActionListener(Action1);
		
		//设置按钮3
		MyButton Btu3 = new MyButton("悔棋", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu3);
		Btu3.addActionListener(Action1);
		
		//设置按钮4
		MyButton Btu4 = new MyButton("退出", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
		estPanel.add(Btu4);
		Btu4.addActionListener(Action1);
		
		//设置按钮5
		MyButton Btu5 = new MyButton("清空棋盘", font, new Dimension(90,50),new Insets(0, 0, 0, 0));
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
