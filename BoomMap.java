package p03;

/*
 * 用于地图上炸弹生成的类
 * */

public class BoomMap {
	int[] mpx=new int[tool.BOOM];
	int[] mpy=new int[tool.BOOM];
	
	{
		for(int i=0;i<tool.BOOM;i++) {
			mpx[i]= (int) (Math.random()*tool.MAP_H+1);
			mpy[i]= (int) (Math.random()*tool.MAP_W+1);
			
			//防止地雷重复
			if(tool.MAP_BOOM[mpx[i]][mpy[i]]==0) tool.MAP_BOOM[mpx[i]][mpy[i]]=-1;
			else i=i-1;
			
		}
	}
	
}

