package fivechesstwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener,Config {

	public static boolean peopleCanPlay=false;
	public static boolean machineCanPlay=false;
	static int lastX=0;
	static int lastY=0;
	static int lastMachineX=0;
	static int lastMachineY=0;
	MyButton Btu1Tmp=null;
	MyButton Btu2Tmp=null;
	MyButton Btu3Tmp=null;
	MyButton Btu4Tmp=null;
	MyButton Btu5Tmp=null;
	Graphics gTmp = null;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Btu1Tmp) {
			peopleCanPlay=true;
			machineCanPlay=false;
		}else if(e.getSource()==Btu4Tmp) {
			System.exit(0);
		}else if(e.getSource()==Btu5Tmp) {
			for(int i=0;i<LINE;i++)
				for(int j=0;j<LINE;j++) {
					DrawMouse.location[i][j]=0;
				}
			peopleCanPlay=false;
			machineCanPlay=false;
			DrawMouse.type=1;
			gTmp.setColor(Color.BLACK);
			myframe.jf.paint(gTmp);
		}else if(e.getSource()==Btu2Tmp) {
			peopleCanPlay=false;
			machineCanPlay=true;
		}else if(e.getSource()==Btu3Tmp) {
			if(peopleCanPlay==true&&machineCanPlay==false) {
				DrawMouse.location[lastX][lastY]=0;
				gTmp.setColor(Color.BLACK);
				myframe.jf.paint(gTmp);
				DrawMouse.type=-DrawMouse.type;
			}else if(peopleCanPlay==false&&machineCanPlay==true) {
				DrawMouse.location[lastX][lastY]=0;
				DrawMouse.location[lastMachineX][lastMachineY]=0;
				gTmp.setColor(Color.BLACK);
				myframe.jf.paint(gTmp);
			}
		}
	}

}
