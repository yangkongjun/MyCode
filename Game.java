package p03;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;


public class Game extends MapTop {

	static int i=-1,j=-1;
	private JPanel contentPane;

	JLabel [][]lb1=new JLabel[1000][1000];
	JTextField t1;//时间表
	JButton btn1;//笑脸按钮


	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("扫雷游戏");
		setBounds(450, 350, tool.F_WIGTH, tool.F_HEIGHT);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu m2 = new JMenu("菜单");
		menuBar.add(m2);
		
		JMenuItem m2_1 = new JMenuItem("重新开始");// alt+R 快捷键实现不了啊
		m2.add(m2_1);
		m2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReGame();//初始化。
				ReBoom();//生成炸弹
				ReNum();//设置数字
				ReLoad();//新数据载入
				tool.STATE=0;
				
			}
		});
		JMenuItem m2_2 = new JMenuItem("选择难度");// alt+S
		m2.add(m2_2);
		m2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.STATE=-2;
			}
		});
		JMenuItem m2_3 = new JMenuItem("退出游戏");// alt+W
		m2.add(m2_3);
		m2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.STATE=-3;
			}
		});
		JMenu m1 = new JMenu("系统说明");
		menuBar.add(m1);
		
		JMenuItem m1_1 = new JMenuItem("版权所有");
		m1.add(m1_1);
		m1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"制作人：杨孔俊\n"
						+ "班级：计算机2011");
			}
		});
		JMenuItem m1_2 = new JMenuItem("帮助信息");
		m1.add(m1_2);
		m1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"游戏说明：\n\n"
						+ " 1.游戏核心目标：根据提示猜到谜语，即可通关。\n"
						+ " 2.点击头像，输入答案！\n"
						+ " 3.通过打开土地寻找谜语提示\n"
						+ " 4.请在最短的时间猜出这个谜语吧\n"
						+ " 5.注意：不要踩到地雷哦！" );
			}
		});
		
		
		btn1 = new JButton("");
		btn1.setBounds((tool.F_WIGTH-4*tool.BORDER)/2, 5, 35, 35);
		getContentPane().add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag=0;//防止过度刷新。
				for(int i=1;i<=tool.MAP_H;i++){
					for(int j=1;j<=tool.MAP_W;j++){
						if(tool.MAP_TOP[i][j]==-1)  flag=1;
					}
				}
				if(flag==1) {
					ReGame();//初始化。
					ReBoom();//生成炸弹
					ReNum();//设置数字
					ReLoad();//新数据载入
					tool.STATE=0;
				}
			}
		});
		
		t1 = new JTextField();
		t1.setBounds(tool.F_WIGTH-100-2*tool.BORDER, 10, 100, 30);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		//=====================创建地图--第一次会执行。===============================
		for(int i=0;i<=tool.MAP_H+10;i++){				//先预处理给他空间。
			for(int j=0;j<=tool.MAP_W+10;j++){
				//实例化一个对象。并且设置文字居中
				lb1[i][j] = new JLabel();
				lb1[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				lb1[i][j].setBackground(new Color(204, 255, 204));
				lb1[i][j].setOpaque(true);//设置背景不透明
				getContentPane().add(lb1[i][j]);
			}
		}
		
		for(int i=0;i<tool.MAP_H;i++){					// 设置相关属性。
			for(int j=0;j<tool.MAP_W;j++){
				//计算x,y位置。 分别填入方法中。
				int x=tool.BORDER+j*tool.IN_LEN;
				int y=tool.BORDER*3+i*tool.IN_LEN;
				int w=tool.IN_LEN;
				int h=tool.IN_LEN;
				lb1[i+1][j+1].setBounds(x,y,w,h);
			}
		}
		//=====================创建地图--覆盖整个局面===============================
		
		ReGame();//---数据清空。--重新选择时因为是直接退出，所以也要给他重新清空一下。----到时候在整合一下，有点乱。
		ReBoom();
		ReNum();
//		BoomMap boom=new BoomMap();//炸弹生成。
//		BoomNum numer=new BoomNum();//数字生成。
		ReLoad();//图片载入。

	}
	
	void ReGame() {
		new GameSelect().ReLevel();//根据等级初始化数据。
		URL url = getClass().getResource("../image/now.png");
		Icon icon = new ImageIcon(url);
		btn1.setIcon(icon);
//		btn1.setIcon(new ImageIcon(MapTop.class.getResource("now.png")));
		
//		ImageIcon img2 = new ImageIcon("");
		
		URL url1 = getClass().getResource("../image/11.png");
		Icon img1 = new ImageIcon(url1);
		URL url2 = getClass().getResource("");
		Icon img2 = new ImageIcon(url2);
		
		for(int i=0;i<=tool.MAP_H+1;i++) {
			for(int j=0;j<=tool.MAP_W+1;j++) {
				lb2[i][j].setIcon(img1);
				tool.MAP_TOP[i][j]=0;//覆盖。
				
				lb2[i][j].setVisible(true);//全部覆盖。
				
				tool.MAP_BOOM[i][j]=0;//格子全为空。--没有炸弹也没有数字。
				lb1[i][j].setIcon(img2);//下面全部清空。
			}
		}
		
	}
	void ReBoom() {//这样感觉就没有map类的必要了。
		int[] mpx=new int[tool.BOOM];
		int[] mpy=new int[tool.BOOM];
		for(int i=0;i<tool.BOOM;i++) {
			mpx[i]= (int) (Math.random()*tool.MAP_H)+1;
			mpy[i]= (int) (Math.random()*tool.MAP_W)+1;
			
			//防止地雷重复
			if(tool.MAP_BOOM[mpx[i]][mpy[i]]==0) {
				 tool.MAP_BOOM[mpx[i]][mpy[i]]=-1;
				URL url = getClass().getResource("../image/boom.png");
				Icon icon = new ImageIcon(url);
				lb1[mpx[i]][mpy[i]].setIcon(icon);
//				 lb1[mpx[i]][mpy[i]].setIcon(new ImageIcon(MapTop.class.getResource("boom.png")));
			}
			else i=i-1;
		}
	}
	void ReNum() {//确定数字。
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
	void ReLoad() {//加载数据。
		
		for(int i=1;i<=tool.MAP_H;i++){
			for(int j=1;j<=tool.MAP_W;j++){
				if(tool.MAP_BOOM[i][j]==-1) {
					lb1[i][j].setText("");
					URL url = getClass().getResource("../image/boom.png");
					Icon icon = new ImageIcon(url);
					lb1[i][j].setIcon(icon);
//					lb1[i][j].setIcon(new ImageIcon(GameSelect.class.getResource("boom.png")));
				}
				else
				{
					URL url = getClass().getResource(image_Load(tool.MAP_BOOM[i][j]));
					Icon icon = new ImageIcon(url);
					lb1[i][j].setIcon(icon);
					
//					lb1[i][j].setIcon(new ImageIcon(GameSelect.class.getResource(image_Load(tool.MAP_BOOM[i][j]))));
				}		
			}
		}
		//外挂。
		System.out.println("================透视表================");
		for(int i=1;i<=tool.MAP_H;i++) {
			for(int j=1;j<=tool.MAP_H;j++) {
				System.out.print(" "+tool.MAP_BOOM[i][j]);
			}
			System.out.println("");
		}
		
	}
	String image_Load(int n) {
		String str="../image/";
		switch(n) {
		case 0: str+="0.png";break;
		case 1: str+="1.png";break;
		case 2: str+="2.png";break;
		case 3: str+="3.png";break;
		case 4: str+="4.png";break;
		case 5: str+="5.png";break;
		case 6: str+="6.png";break;
		case 7: str+="7.png";break;
		case 8: str+="8.png";break;
		}
		System.out.println(str);
		return str;
	}

}
