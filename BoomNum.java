package p03;
/*
 * 好好想想为什么要类。如果不用类呢？---这些并没有实际对界面做出影响。 唯有MapTop会。所以MapTop才是最大问题
 * */
public class BoomNum {
	{
		for(int i=1;i<=tool.MAP_H;i++) {
			for(int j=1;j<=tool.MAP_W;j++) {
				if(tool.MAP_BOOM[i][j]==-1) {
					for(int k=0;k<8;k++) {
						int x=i+tool.dirx[k],y=j+tool.diry[k];
						if(tool.MAP_BOOM[x][y]!=-1) tool.MAP_BOOM[x][y]++;
					}
				}
			}
		}
	}
}
