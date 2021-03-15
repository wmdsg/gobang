package fivechesstwo;

public interface Config {
	public static final int X0=50;
	public static final int Y0=70;//初始坐标
	public static final int SIZE=50;//格子大小
	public static final int LINE=15;
	public static final int CHESSDIA = 50;//棋子直径

}



//五子棋权值算法
/*
public void setMap() {
	//一活
	  hashmap.put("10", 30);
	  hashmap.put("10-10", 30);
	  hashmap.put("10-11", 30);
	  //一眠
	  hashmap.put("1-1", 10);
	  //二活
	  hashmap.put("110", 100);
	  hashmap.put("110-10", 100);
	  hashmap.put("110-11", 100);
	  hashmap.put("1010", 100);
	  hashmap.put("10101-1", 100);
	  hashmap.put("1010-10",100);
	  hashmap.put("1010-11", 100);
	  hashmap.put("-1-10", 150);
	  hashmap.put("-1-1010", 150);
	  hashmap.put("-1-101-1", 150);
	  hashmap.put("-10-10", 150);
	  hashmap.put("-10-10-11", 150);
	  hashmap.put("-10-1010", 150);
	  hashmap.put("-10-101-1", 150);
	  //二眠
	  hashmap.put("11-1", 80);
	  hashmap.put("101-1", 80);
	  //三活
	  hashmap.put("1110", 200);
	  hashmap.put("111010", 200);
	  hashmap.put("11101-1", 200);
	  hashmap.put("10110", 200);
	  hashmap.put("10110-11", 200);
	  hashmap.put("10110-10", 200);
	  hashmap.put("-1-1-10", 250);
	  hashmap.put("-1-1-10-10", 250);
	  hashmap.put("-1-1-10-11", 250);
	  hashmap.put("-10-1-10", 250);
	  hashmap.put("-10-1-101-1", 250);
	  hashmap.put("-10-1-1010", 250);
	  //三眠
	  hashmap.put("111-1", 90);
	  hashmap.put("1101-1", 90);
	  hashmap.put("1011-1", 90);
	  hashmap.put("-1-1-11", 95);
	  hashmap.put("-1-10-11", 95);
	  hashmap.put("-10-1-11", 95);
	  //四活
	  hashmap.put("-1-1-1-10", 2000);
	  hashmap.put("-1-1-1-1010", 2000);
	  hashmap.put("-1-1-1-10110", 2000);
	  hashmap.put("-1-1-1-101-1", 2000);
	  hashmap.put("-1-1-1-10", 2000);
	  //四眠
	  hashmap.put("1111-1", 1000);
	  hashmap.put("-1-1-1-11", 2000);
	  hashmap.put("-1-1-1-11", 2000);
}
*/
/*
public void clearweightlocation() {
	for(int i=0;i<LINE;i++) {
		for(int j=0;j<LINE;j++) {
			weightLocation[i][j]=0;
		}
	}
}
*/
/*
public void searchLocation() {
	for(int i=0;i<LINE;i++) {
		for(int j=0;j<LINE;j++) {
			if(location[i][j]==0) {
				String code="";
				int color=0;
				//向左搜索权值
				for(int k=i-1;k>=0;k--) {
					if(location[k][j]==0) {
						if(location[k][j]==location[k+1][j]) {
							break;
						}else {
							code+=location[k][j];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[k][j];
							code+=location[k][j];
						}else if(color==location[k][j]) {
							code+=location[k][j];
						}else if(color!=location[k][j]) {
							code+=location[k][j];
							break;
						}
					}
				}
				Integer value =hashmap.get(code);
				if(hashmap.get(code)!=null) {
					weightLocation[i][j]+=hashmap.get(code);
				}
				code="";
				color=0;
				
				//向右搜索权值
				for(int k=i+1;k<LINE;k++) {
					if(location[k][j]==0) {
						if(location[k][j]==location[k-1][j]) {
							break;
						}else {
							code+=location[k][j];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[k][j];
							code+=location[k][j];
						}else if(color==location[k][j]) {
							code+=location[k][j];
						}else if(color!=location[k][j]) {
							color=location[k][j];
							code+=location[k][j];
							break;
						}
					}
				}
				if(hashmap.get(code)!=null) {
					weightLocation[i][j]+=hashmap.get(code);
				}
				code="";
				color=0;
				
				//向上搜索权值
				for(int k=j-1;k>=0;k--) {
					if(location[i][k]==0) {
						if(location[i][k]==location[i][k+1]) {
							break;
						}else {
							code+=location[i][k];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[i][k];
							code+=location[i][k];
						}else if(color==location[i][k]) {
							code+=location[i][k];
						}else if(color!=location[i][k]) {
							color=location[i][k];
							code+=location[i][k];
							break;
						}
					}
				}
				value=hashmap.get(code);
				if(value!=null) {
					weightLocation[i][j]+=value;
				}
				code="";
				color=0;
				
				//向下搜索权值
				for(int k=j+1;k<LINE;k++) {
					if(location[i][k]==0) {
						if(location[i][k]==location[i][k-1]) {
							break;
						}else {
							code+=location[i][k];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[i][k];
							code+=location[i][k];
						}else if(color==location[i][k]) {
							code+=location[i][k];
						}else if(color!=location[i][k]) {
							code+=location[i][k];
							color=location[i][k];
							break;
						}
					}
				}
				value=hashmap.get(code);
				if(value!=null) {
					weightLocation[i][j]+=value;
				}
				code="";
				color=0;
				
				//向左上
				for(int m=i-1,n=j-1;m>=0&&n>=0;m--,n--) {
					if(location[m][n]==0) {
						if(location[m][n]==location[m+1][n+1]) {
							break;
						}else {
							code+=location[m][n];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[m][n];
							code+=location[m][n];
						}else if(color==location[m][n]) {
							code+=location[m][n];
						}else if(color!=location[m][n]) {
							code+=location[m][n];
							color=location[m][n];
							break;
						}
					}
				}
				value=hashmap.get(code);
				if(value!=null) {
					weightLocation[i][j]+=value;
				}
				code="";
				color=0;
				
				//向左下
				for(int m=i-1,n=j+1;m>=0&&n<LINE;m--,n++) {
					if(location[m][n]==0) {
						if(location[m][n]==location[m+1][n-1]) {
							break;
						}else {
							code+=location[m][n];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[m][n];
							code+=location[m][n];
						}else if(color==location[m][n]) {
							code+=location[m][n];
						}else if(color!=location[m][n]) {
							code+=location[m][n];
							color=location[m][n];
							break;
						}
					}
				}
				value=hashmap.get(code);
				if(value!=null) {
					weightLocation[i][j]+=value;
				}
				code="";
				color=0;
				
				//向右上
				for(int m=i+1,n=j-1;m<LINE&&n>=0;m++,n--) {
					if(location[m][n]==0) {
						if(location[m][n]==location[m-1][n+1]) {
							break;
						}else {
							code+=location[m][n];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[m][n];
							code+=location[m][n];
						}else if(color==location[m][n]) {
							code+=location[m][n];
						}else if(color!=location[m][n]) {
							code+=location[m][n];
							color=location[m][n];
							break;
						}
					}
				}
				value=hashmap.get(code);
				if(value!=null) {
					weightLocation[i][j]+=value;
				}
				code="";
				color=0;
				
				//向右下
				for(int m=i+1,n=j+1;m<LINE&&n<LINE;m++,n++) {
					if(location[m][n]==0) {
						if(location[m][n]==location[m-1][n-1]) {
							break;
						}else {
							code+=location[m][n];
							color=0;
						}
					}else {
						if(color==0) {
							color=location[m][n];
							code+=location[m][n];
						}else if(color==location[m][n]) {
							code+=location[m][n];
						}else if(color!=location[m][n]) {
							code+=location[m][n];
							color=location[m][n];
							break;
						}
					}
				}
				value=hashmap.get(code);
				if(value!=null) {
					weightLocation[i][j]+=value;
				}
				code="";
				color=0;
			}
		}
	}
	for(int i=0;i<LINE;i++) {
		for(int j=0;j<LINE;j++) {
			if(location[i][j]==0&&weightLocation[i][j]>maxScore) {
				machineX=i;
				machineY=j;
				maxScore=weightLocation[i][j];
			}
		}
	}
}
*/