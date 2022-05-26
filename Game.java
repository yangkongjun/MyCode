package p03;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;


public class Game extends MapTop {

	static int i=-1,j=-1;
	private JPanel contentPane;

	JLabel [][]lb1=new JLabel[1000][1000];
	JTextField t1;//时间表
	JButton btn1;//笑脸按钮
//	JTextArea []txt =new JTextArea[10];//0:隐藏文本 1：输入文本。//当焦点都不在这些地方默认焦点到0.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("扫雷游戏");
		//=====================测试
//		GameSelect.xx=360;
//		GameSelect.yy=385;
		//=====================测试
		setBounds(450, 350, tool.F_WIGTH, tool.F_HEIGHT);//============窗口大小
		
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
		
		JMenuItem m1_2 = new JMenuItem("帮助信息");
		m1.add(m1_2);
		m1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"使用说明：\n" +
						"1."
						);
			}
		});
/*
 * 这是创建新Panel会覆盖之前的，所以如果要继承这里要注释掉。	 新的组件用getContentPane().add();来添加	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
*/	

//		txt[0] = new JTextArea();
//		txt[0].setBounds(0,0,0,0);//长宽都为0相当于隐藏。用于实现键盘按下操作。
//		contentPane.add(txt[0]);
		
		
		btn1 = new JButton("");
		btn1.setBounds((tool.F_WIGTH-4*tool.BORDER)/2, 5, 35, 35);
		btn1.setIcon(new ImageIcon(GameSelect.class.getResource("now.png")));
		getContentPane().add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReGame();//初始化。
				ReBoom();//生成炸弹
				ReNum();//设置数字
				ReLoad();//新数据载入
				tool.STATE=0;
			}
		});
		
		t1 = new JTextField();
		t1.setBounds(tool.F_WIGTH-100-2*tool.BORDER, 10, 100, 30);
		getContentPane().add(t1);
		t1.setColumns(10);
		//=====================创建地图--覆盖整个局面===============================
		for(int i=0;i<=tool.MAP_H+10;i++){
			for(int j=0;j<=tool.MAP_W+10;j++){
				//实例化一个对象。并且设置文字居中
				lb1[i][j] = new JLabel();
				lb1[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				lb1[i][j].setBackground(new Color(204, 255, 204));
				lb1[i][j].setOpaque(true);//设置背景不透明
			}
		}
		ReGame();//重新选择时因为是直接退出，所以也要给他重新清空一下。----到时候在整合一下，有点乱。
		map boom=new map();
		BoomNum numer=new BoomNum();
		for(int i=0;i<tool.MAP_H;i++){
			for(int j=0;j<tool.MAP_W;j++){
				//计算x,y位置。 分别填入方法中。
				int x=tool.BORDER+j*tool.IN_LEN;
				int y=tool.BORDER*3+i*tool.IN_LEN;
				int w=tool.IN_LEN;
				int h=tool.IN_LEN;
				lb1[i+1][j+1].setBounds(x,y,w,h);
		//=====================创建地图---创建雷的局面===============================
				
				//判断是不是雷
				if(tool.MAP_BOOM[i+1][j+1]==-1)
					lb1[i+1][j+1].setIcon(new ImageIcon(GameSelect.class.getResource("boom.png")));
				else{
//					lb1[i+1][j+1].setText(Integer.toString(tool.MAP_BOOM[i+1][j+1]));
//					if(tool.MAP_BOOM[i+1][j+1]==0) lb1[i+1][j+1].setText(""); //让0不显示。---continue的话。下面的代码就不能执行了。
					lb1[i+1][j+1].setIcon(new ImageIcon(GameSelect.class.getResource(image_Load(tool.MAP_BOOM[i+1][j+1]))));//根据数值，载入相对应的数字图片
				}
//					lb1[i+1][j+1].setIcon(new ImageIcon(GameSelect.class.getResource("flag.png")));
					
				getContentPane().add(lb1[i+1][j+1]);
			}
		}
		//=====================创建地图---创建数的局面===============================
		
	}
	void ReGame() {
		for(int i=0;i<=tool.MAP_H+1;i++) {
			for(int j=0;j<=tool.MAP_W+1;j++) {
				tool.MAP_TOP[i][j]=0;
				tool.MAP_BOOM[i][j]=0;
				lb2[i][j].setVisible(true);
				lb1[i][j].setIcon(new ImageIcon(MapTop.class.getResource("")));
				lb2[i][j].setIcon(new ImageIcon(MapTop.class.getResource("11.png")));
				
			}
		}
		btn1.setIcon(new ImageIcon(MapTop.class.getResource("now.png")));
		new GameSelect().ReLevel();
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
				 lb1[mpx[i]][mpy[i]].setIcon(new ImageIcon(MapTop.class.getResource("boom.png")));
			}
			else i=i-1;
			
		}
	}
	void ReNum() {
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
	void ReLoad() {
		
		for(int i=1;i<=tool.MAP_H;i++){
			for(int j=1;j<=tool.MAP_W;j++){
				if(tool.MAP_BOOM[i][j]==-1) {
					lb1[i][j].setText("");
					lb1[i][j].setIcon(new ImageIcon(GameSelect.class.getResource("boom.png")));
				}
				else
					lb1[i][j].setIcon(new ImageIcon(GameSelect.class.getResource(image_Load(tool.MAP_BOOM[i][j]))));
			}
		}
	}
	String image_Load(int n) {
		String str="";
		switch(n) {
		case 0: str="0.png";break;
		case 1: str="1.png";break;
		case 2: str="2.png";break;
		case 3: str="3.png";break;
		case 4: str="4.png";break;
		case 5: str="5.png";break;
		case 6: str="6.png";break;
		case 7: str="7.png";break;
		case 8: str="8.png";break;
		}
		return str;
	}

}
