package fivechesstwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.concurrent.atomic.LongAccumulator;

import javax.swing.JOptionPane;

import org.omg.PortableInterceptor.IORInterceptor;


public class DrawMouse implements MouseListener,Config {
	
	public Graphics gr=null;
	int x1,y1;//鼠标位置
	int xx,yy;//棋盘交点值
	int machineX=-1;
	int machineY=-1;
	int maxScore=-1;
	static int[][] location = new int[LINE][LINE];
	int[][] weightLocation=new int[LINE][LINE];
	HashMap<String, Integer> hashmap = new HashMap<String,Integer>();
	static int type=1;//1:黑色，-1：白色，0：空子
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
			x1=e.getX();
			y1=e.getY();
		//计算当前棋子属于棋盘上的交点
			if((x1-X0)%SIZE>SIZE/2) {
				xx=(x1-X0)/SIZE+1;
			}else {
				xx=(x1-X0)/SIZE;
			}
		
			if((y1-Y0)%SIZE>SIZE/2) {
				yy=(y1-Y0)/SIZE+1;
			}else {
				yy=(y1-Y0)/SIZE;
			}
			if(MyActionListener.peopleCanPlay==true&&MyActionListener.machineCanPlay==false) {
				if(x1>=X0&&x1<=(LINE-1)*SIZE+X0&&y1>=Y0&&y1<=(LINE-1)*SIZE+Y0) {
					if(type==1&&location[xx][yy]==0) {
						gr.setColor(Color.BLACK);
						gr.fillOval(xx*SIZE+X0-SIZE/2, yy*SIZE+Y0-SIZE/2, SIZE, SIZE);
						MyActionListener.lastX=xx;
						MyActionListener.lastY=yy;
						location[xx][yy]=1;
						isWin(xx, yy, type);
						type=-1;
					}else if(type==-1&&location[xx][yy]==0){
						gr.setColor(Color.WHITE);
						gr.fillOval(xx*SIZE+X0-SIZE/2, yy*SIZE+Y0-SIZE/2, SIZE, SIZE);
						MyActionListener.lastX=xx;
						MyActionListener.lastY=yy;
						location[xx][yy]=-1;
						isWin(xx, yy, type);
						type=1;
					}
					else if(location[xx][yy]!=0) {
						JOptionPane.showMessageDialog(myframe.jf, "请重新落子","警告",JOptionPane.PLAIN_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(myframe.jf, "请在棋盘落子","警告",JOptionPane.PLAIN_MESSAGE);
				}
		}else if(MyActionListener.machineCanPlay==true&&MyActionListener.peopleCanPlay==false) {
			if(x1>=X0&&x1<=(LINE-1)*SIZE+X0&&y1>=Y0&&y1<=(LINE-1)*SIZE+Y0) {
				if(location[xx][yy]==0) {
					gr.setColor(Color.BLACK);
					gr.fillOval(xx*SIZE+X0-SIZE/2, yy*SIZE+Y0-SIZE/2, SIZE, SIZE);
					location[xx][yy]=1;
					isWin(xx, yy, 1);
					chessboard.locationtwo=location;
				}else if(location[xx][yy]!=0) {
					JOptionPane.showMessageDialog(myframe.jf, "请重新落子","警告",JOptionPane.PLAIN_MESSAGE);
				}		
			}else {
				JOptionPane.showMessageDialog(myframe.jf, "请在棋盘落子","警告",JOptionPane.PLAIN_MESSAGE);
			}
			MyActionListener.lastX=xx;
			MyActionListener.lastY=yy;
			MyActionListener.lastMachineX=machineX;
			MyActionListener.lastMachineY=machineY;
			gr.setColor(Color.WHITE);
			searchlocation2();
			gr.fillOval(machineX*SIZE+X0-SIZE/2, machineY*SIZE+Y0-SIZE/2, SIZE, SIZE);
			location[machineX][machineY]=-1;
			isWin(machineX, machineY, -1);
			chessboard.locationtwo=location;
		}
	}
	
	public boolean searchlocation2() {
		for(int i=0;i<LINE;i++) 
			for(int j=0;j<LINE;j++) {
				weightLocation[i][j]=0;
			}
		int machineChessNumTmp=0;
		int humanChessNumTmp=0;
		int scoreTmp=0;
		int goalx=-1;
		int goaly=-1;
		int maxScore=-1;
		
		//纵向扫描
		for(int i=0;i<LINE;i++)
			for(int j=0;j<11;j++) {
				int k=j;
				while(k<j+5) {
					if(location[k][i]==1) {
						humanChessNumTmp++;
					}else if(location[k][i]==-1){
						machineChessNumTmp++;
					}
					k++;
				}
				scoreTmp=tupleScore(humanChessNumTmp, machineChessNumTmp);
				for(k=j;k<j+5;k++) {
					weightLocation[k][i]+=scoreTmp;
				}
				humanChessNumTmp=0;
				machineChessNumTmp=0;
				scoreTmp=0;
			}
		
		//横向扫描
		for(int i=0;i<LINE;i++)
			for(int j=0;j<11;j++) {
				int k=j;
				while(k<j+5) {
					if(location[i][k]==1){
						humanChessNumTmp++;
					}else if(location[i][k]==-1){
						machineChessNumTmp++;
					}
					k++;
				}
				scoreTmp=tupleScore(humanChessNumTmp, machineChessNumTmp);
				for(k=j;k<j+5;k++) {
					weightLocation[i][k]+=scoreTmp;
				}
				humanChessNumTmp=0;
				machineChessNumTmp=0;
				scoreTmp=0;
			}
		
		//右上到左下上侧扫描
		for(int i=14;i>=4;i--)
			for(int k=i,j=0;k>=0&&j<15;k--,j++) {
				int m=k;
				int n=j;
				while(m>k-5&&k-5>=-1) {
					if(location[m][n]==1) humanChessNumTmp++;
					else if(location[m][n]==-1) machineChessNumTmp++;
					m--;
					n++;
				}
				if(m==k-5) {
					scoreTmp=tupleScore(humanChessNumTmp, machineChessNumTmp);
					for(m=k,n=j;m>k-5;m--,n++) {
						weightLocation[m][n]+=scoreTmp;
					}
				}
				machineChessNumTmp=0;
				humanChessNumTmp=0;
				scoreTmp=0;
			}
		
		//右上到左下下侧扫描
		for(int i=1;i<11;i++)
			for(int k=i,j=14;k<14&&j>=0;k++,j--) {
				int m=k;//Y
				int n=j;//X
				while(m<k+5&&k+5<=15) {
					if(location[n][m]==1) humanChessNumTmp++;
					else if(location[n][m]==-1) machineChessNumTmp++;
					m++;
					n--;
				}
				if(m==k+5) {
					scoreTmp=tupleScore(humanChessNumTmp, machineChessNumTmp);
					for(m=k,n=j;m<k+5;m++,n--) {
						weightLocation[n][m]+=scoreTmp;
					}
				}
				humanChessNumTmp=0;
				machineChessNumTmp=0;
				scoreTmp=0;
			}
		
		//左上到右下上侧扫描
		for(int i=0;i<11;i++)
			for(int k=i,j=0;k<15&&j<15;k++,j++) {
				int m=k;//X
				int n=j;//Y
				while(m<k+5&&k+5<=15) {
					if(location[m][n]==1) humanChessNumTmp++;
					else if(location[m][n]==-1) machineChessNumTmp++;
					m++;
					n++;
				}
				if(m==k+5) {
					scoreTmp=tupleScore(humanChessNumTmp, machineChessNumTmp);
					for(m=k,n=j;m<k+5;m++,n++) {
						weightLocation[m][n]+=scoreTmp;					
						}
				}
				humanChessNumTmp=0;
				machineChessNumTmp=0;
				scoreTmp=0;
			}
		
		//左上到右下下侧扫描
		for(int i=1;i<11;i++)
			for(int k=i,j=0;k<15&&j<15;k++,j++) {
				int m=k;
				int n=j;
				while(m<k+5&&k+5<=15) {
					if(location[n][m]==1) humanChessNumTmp++;
					else if(location[n][m]==-1) machineChessNumTmp++;
					m++;
					n++;
				}
				if(m==k+5) {
					scoreTmp=tupleScore(humanChessNumTmp, machineChessNumTmp);
					for(m=k,n=j;m<k+5;m++,n++) {
						weightLocation[n][m]+=scoreTmp;
					}
				}
				humanChessNumTmp=0;
				machineChessNumTmp=0;
				scoreTmp=0;
			}
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++) {
				if(location[i][j]==0&&weightLocation[i][j]>maxScore) {
					machineX=i;
					machineY=j;
					maxScore=weightLocation[i][j];
				}
			}
		if(machineX!=-1&&machineY!=-1) {
			return true;
		}
		//平局
		return false;
	}
	
	
	public int tupleScore(int humanChessNum,int machineChessNum) {
		if(humanChessNum > 0 && machineChessNum > 0){
            return 0;
        }
        //2.全部为空，没有落子，判分为7
        if(humanChessNum == 0 && machineChessNum == 0){
            return 7;
        }
        //3.机器落1子，判分为35
        if(machineChessNum == 1){
            return 35;
        }
        //4.机器落2子，判分为800
        if(machineChessNum == 2){
            return 800;
        }
        //5.机器落3子，判分为15000
        if(machineChessNum == 3){
            return 15000;
        }
        //6.机器落4子，判分为800000
        if(machineChessNum == 4){
            return 800000;
        }
        //7.人类落1子，判分为15
        if(humanChessNum == 1){
            return 15;
        }
        //8.人类落2子，判分为400
        if(humanChessNum == 2){
            return 400;
        }
        //9.人类落3子，判分为1800
        if(humanChessNum == 3){
            return 1800;
        }
        //10.人类落4子，判分为100000
        if(humanChessNum == 4){
            return 100000;
        }
        return -1;//若是其他结果肯定出错了。这行代码根本不可能执行
	}

	//判断输赢
	public void isWin(int x,int y,int type) {
		int sum=0;
		//判断水平
			//判断水平左
		for(int i=x-1;i>=0;i--) {
			if(location[i][y]==type) {
				sum++;
			}else {
				break;
			}
		}
			//判断水平右
		for(int i=x+1;i<LINE;i++) {
			if(location[i][y]==type) {
				sum++;
			}else {
				break;
			}
		}
		if(sum>=4) {
			if(type==1) {
				JOptionPane.showMessageDialog(myframe.jf,"黑棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}else if(type==-1){
				JOptionPane.showMessageDialog(myframe.jf, "白棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}
		}
		sum=0;
		//判断垂直
			//判断垂直上
		for(int i=y-1;i>=0;i--) {
			if(location[x][i]==type) {
				sum++;
			}else {
				break;
			}
		}
			//判断垂直下
		for(int i=y+1;i<LINE;i++) {
			if(location[x][i]==type) {
				sum++;
			}else {
				break;
			}
		}
		if(sum>=4) {
			if(type==1) {
				JOptionPane.showMessageDialog(myframe.jf,"黑棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}else if(type==-1){
				JOptionPane.showMessageDialog(myframe.jf, "白棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}
		}
		sum=0;
		
		//判断左斜
			//判断左上
		for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--) {
			if(location[i][j]==type) {
				sum++;
			}else {
				break;
			}
		}
			//判断右下
		for(int i=x+1,j=y+1;i<LINE&&j<LINE;i++,j++) {
			if(location[i][j]==type) {
				sum++;
			}else {
				break;
			}
		}
		if(sum>=4) {
			if(type==1) {
				JOptionPane.showMessageDialog(myframe.jf,"黑棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}else if(type==-1){
				JOptionPane.showMessageDialog(myframe.jf, "白棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}
		}
		sum=0;
		
		//判断右斜
			//判断左下
		for(int i=x-1,j=y+1;i>=0&&j<LINE;i--,j++) {
			if(location[i][j]==type) {
				sum++;
			}else {
				break;
			}
		}
			//判断右上
		for(int i=x+1,j=y-1;i<LINE&&j>=0;i++,j--) {
			if(location[i][j]==type) {
				sum++;
			}else {
				break;
			}
		}
		if(sum>=4) {
			if(type==1) {
				JOptionPane.showMessageDialog(myframe.jf,"黑棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}else if(type==-1){
				JOptionPane.showMessageDialog(myframe.jf, "白棋赢","游戏结束",JOptionPane.PLAIN_MESSAGE);
				return ;
			}
		}
		return ;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
