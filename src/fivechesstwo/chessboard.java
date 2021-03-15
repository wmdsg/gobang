package fivechesstwo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class chessboard extends JFrame implements Config {

	public static int[][] locationtwo=new int[LINE][LINE];
	public void paint(Graphics g) {
		super.paint(g);
		for(int i=0;i<LINE;i++) {
			g.drawLine(X0, Y0+SIZE*i,X0+SIZE*(LINE-1),Y0+SIZE*i);
			g.drawLine(X0+SIZE*i, Y0, X0+SIZE*i, Y0+SIZE*(LINE-1));
		}
		for(int i=0;i<LINE;i++) {
			for(int j=0;j<LINE;j++) {
				if(locationtwo[i][j]==1) {
					g.setColor(Color.BLACK);
					g.fillOval(i*SIZE+X0-SIZE/2, j*SIZE+Y0-SIZE/2, SIZE, SIZE);
				}
				if(locationtwo[i][j]==-1) {
					g.setColor(Color.WHITE);
					g.fillOval(i*SIZE+X0-SIZE/2, j*SIZE+Y0-SIZE/2, SIZE, SIZE);
				}
			}
		}
		
	}
	
	
}
