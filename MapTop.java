package p03;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MapTop extends JFrame {

	private JPanel contentPane;
	JLabel [][]lb2=new JLabel[1000][1000];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapTop frame = new MapTop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 这个方法为什么要加在MapTop里而不统一整合一起来？像静态变量一样？
	 */
	public MapTop() {
		
		setBounds(450, 350, tool.F_WIGTH, tool.F_HEIGHT);//============窗口大小
		//=============================原来是这样=========================
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//========================不声明，就要用getContentPane()来添加窗体。===============
		
		//解决四个角落问题--设置外围边界。-----为了方便设置外围边界。所以直接先预处理掉,每个label的地址。
		for(int i=0;i<=tool.MAP_H+10;i++){
			for(int j=0;j<=tool.MAP_W+10;j++){
				//实例化一个对象。并且设置文字居中
				lb2[i][j] = new JLabel();
				lb2[i][j].setHorizontalAlignment(SwingConstants.CENTER);
			}
		}
		
		//对每个label进行设置
		for(int i=0;i<tool.MAP_H;i++){
			for(int j=0;j<tool.MAP_W;j++){
				//计算x,y位置。 分别填入方法中。
				int x=tool.BORDER+j*tool.IN_LEN;
				int y=tool.BORDER*3+i*tool.IN_LEN;
				int w=tool.IN_LEN;
				int h=tool.IN_LEN;
//				System.out.println((x)+","+(y));
				lb2[i+1][j+1].setBounds(x,y,w,h);
				
				lb2[i+1][j+1].setIcon(new ImageIcon(MapTop.class.getResource("11.png")));
								
				contentPane.add(lb2[i+1][j+1]);// contentPane 学习
				
			}
		}
	}
	
	
	
	
	void show(int x,int y) {//属于他的有翻开功能。---将空白一并展示---返回是否是炸弹。true:是炸弹.false:不是炸弹
		
		if(tool.MAP_TOP[x][y]!=-1) {
			lb2[x][y].setVisible(false);//至少当前选择的块一定要消失。
			tool.MAP_TOP[x][y]=-1;
			tool.TOTAL--;
		}
		
		if(tool.MAP_BOOM[x][y]==0)//
		{
			for(int i=x-1;i<=x+1;i++) {//联级删除
				for(int j=y-1;j<=y+1;j++) {
					
					if(i<=0||j<=0||i>tool.MAP_H||j>tool.MAP_W) continue;//将边界边框判掉
					
					if(tool.MAP_TOP[i][j]==0) {//显示除炸弹外的空格----我换一下，只把覆盖的删掉
						System.out.println(tool.TOTAL);
						show(i,j);
						
					}

				}
			}
		}
		
	}
	void allboom() {
		for(int i=1;i<=tool.MAP_H;i++) {
			for(int j=1;j<=tool.MAP_W;j++) {
				if(tool.MAP_BOOM[i][j]==-1) {
					lb2[i][j].setVisible(false);
					tool.MAP_TOP[i][j]=-1;
				}
			}
		}
	}
	void allopen() {
		for(int i=1;i<=tool.MAP_H;i++) {
			for(int j=1;j<=tool.MAP_W;j++) {
				if(tool.MAP_BOOM[i][j]==-1) {
					lb2[i][j].setIcon(new ImageIcon(MapTop.class.getResource("flag.png")));
					
				}
			}
		}
	}
	void look(int x,int y) {
		
		int sum=0;//记录标记个数
		
		System.out.println("================透视表================");
		for(int i=x-1;i<=x+1;i++) {
			for(int j=y-1;j<=y+1;j++) {
				
				if(i<=0||j<=0||i>tool.MAP_H||j>tool.MAP_W) continue;//将边界边框判掉
				if(tool.MAP_TOP[i][j]==1) sum++;
				System.out.print(" "+tool.MAP_BOOM[i][j]);
			}
			System.out.println("");
		}
		
		
		if(tool.MAP_BOOM[x][y]==sum) {

			for(int i=x-1;i<=x+1;i++) {//联级删除
				for(int j=y-1;j<=y+1;j++) {
					
					if(i<=0||j<=0||i>tool.MAP_H||j>tool.MAP_W) continue;//将边界边框判掉
					
					if(tool.MAP_TOP[i][j]==0) {//显示除炸弹外的空格----我换一下，只把覆盖的删掉
						System.out.println(tool.TOTAL);
						show(i,j);
					}

				}
			}
			
		}
	}
	
	boolean Check() {
		for(int i=1;i<=tool.MAP_H;i++) {
			for(int j=1;j<=tool.MAP_W;j++) {
				if(tool.MAP_BOOM[i][j]==-1&&tool.MAP_TOP[i][j]==-1) return true;
			}
		}
		return false;
	}
}
