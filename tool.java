package p03;
/*
 * 工具类
 * 存放静态参数
 * 工具方法
 * 
 * 
 * 
 * 计数操作是，每次碰到雷，我对周围一圈数值+1.---这样到时候翻开的时候直接把数值打印上去就好。---非常妙。
 * */
public class tool {
	static int STATE=-1;//-1：游戏结束。-2：回到选择界面。-3：退出程序。0：游戏界面。1：游戏说明界面 
	static int LEVEL=-1;//(设置初始) 1:初级 2：中级 3：高级 10：游戏说明
	static int MAP_W=9;
	static int MAP_H=9;
	static int BORDER=15;//格子边长---偏移量
	static int IN_LEN=30;//小正方体边长
	static int F_WIGTH=3*BORDER+MAP_W*IN_LEN;//窗体宽度
	static int F_HEIGHT=4*BORDER+MAP_H*IN_LEN+60;//窗体高度---其中60是菜单栏的高度。
	
	static int BOOM=5;//炸弹个数
	static int TOTAL=81;
	//因为炸弹位置相对固定。所以也是静态参数--用int到时候还可以表示其他信息--注意，new只能new一次。所以尽量开大一点。
	static int MAP_BOOM[][]=new int[1000][1000];//-1:炸弹 0~8：周围炸弹个数
	static int MAP_TOP[][]=new int[1000][1000];//-1:已被翻开。0:覆盖 1:标志 
	
	static int MOUSE_X=0;//鼠标点击后的X坐标
	static int MOUSE_Y=0;//鼠标点击后的坐标
	static boolean LEFT=false;//是否按下鼠标左键
	static boolean RIGHT=false;//是否按下鼠标右键
	static boolean MIDDLE=false;//是否按下鼠标中键
	
	static int dirx[]= {0,1,0,-1,1,1,-1,-1};
	static int diry[]= {1,0,-1,0,1,-1,1,-1};
	
	static int HOUR=0,MINUTE=0,SECOND=0;//小时 分钟 秒
	
	
	

}

