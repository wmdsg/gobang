package fivechesstwo;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class MyButton extends JButton{
	public String title;
	public Font font;
	Dimension dimen;
	Insets Ins ;
	MyButton(String text,Font fonttmp,Dimension dimentmp,Insets Instmp) {
		title=text;
		font=fonttmp;
		dimen = dimentmp;
		Ins=Instmp;
		this.setText(title);
		this.setFont(font);
		this.setPreferredSize(dimen);
		this.setMargin(Ins);
	}

}

//11111111111
//�����������ͣ�8�����������������ͣ��࣬�ӿڣ����顣
//JButton Btu1 = new JButton("���˶�ս");
//Btu1.setFont(font);
//Btu1.setMargin(new Insets(0, 0, 0, 0));
///*public Insets(int top,
//      int left,
//      int bottom,
//      int right)
//   top��left��bottom��right�ֱ�Ϊ�밴ť�߽�ľ���
// */
//Btu1.setPreferredSize(new Dimension(90,50));//���ð�ť��С
//estPanel.add(Btu1);

