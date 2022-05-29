package p03;
/*
 * 比如小变量level等都可以直接用静态来表示了。
 * */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameSelect extends JFrame {

	private JPanel contentPane;

	public GameSelect() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("扫雷游戏");
		setBounds(400, 200, 265, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lb1 = new JLabel("扫雷游戏");//谜语版扫雷游戏
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		lb1.setBounds(0, 10, 249, 50);
		contentPane.add(lb1);
		
		JButton btn1 = new JButton("初级");
		btn1.setBounds(52, 80, 150, 50);
		contentPane.add(btn1);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tool.LEVEL=1;
				tool.STATE=0;
			}
		});

		
		JButton btn2 = new JButton("中级");
		btn2.setBounds(52, 140, 150, 50);
		contentPane.add(btn2);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tool.LEVEL=2;
				tool.STATE=0;
			}
		});

		
		JButton btn3 = new JButton("高级");
		btn3.setBounds(52, 200, 150, 50);
		contentPane.add(btn3);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tool.LEVEL=3;
				tool.STATE=0;
			}
		});
		JButton btn4 = new JButton("拓展功能");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.LEVEL=10;
				tool.STATE=1;
			}
		});
		btn4.setBounds(52, 260, 150, 50);
		contentPane.add(btn4);
		



	}
	//初始化等级以及相关设置。
	void ReLevel() {
		if(tool.LEVEL==1) {
			tool.BOOM=5;
			tool.MAP_H=9;
			tool.MAP_W=9;
			tool.TOTAL=tool.MAP_H*tool.MAP_W;
			tool.F_WIGTH=3*tool.BORDER+tool.MAP_W*tool.IN_LEN;
			tool.F_HEIGHT=4*tool.BORDER+tool.MAP_H*tool.IN_LEN+60;
			tool.HOUR=0;tool.MINUTE=0;tool.SECOND=0;

		}else if(tool.LEVEL==2) {
			tool.BOOM=40;
			tool.MAP_H=16;
			tool.MAP_W=16;
			tool.TOTAL=tool.MAP_H*tool.MAP_W;
			tool.F_WIGTH=3*tool.BORDER+tool.MAP_W*tool.IN_LEN;
			tool.F_HEIGHT=4*tool.BORDER+tool.MAP_H*tool.IN_LEN+60;
			tool.HOUR=0;tool.MINUTE=0;tool.SECOND=0;
		}else if(tool.LEVEL==3) {
			tool.BOOM=99;
			tool.MAP_H=16;
			tool.MAP_W=30;
			tool.TOTAL=tool.MAP_H*tool.MAP_W;
			tool.F_WIGTH=3*tool.BORDER+tool.MAP_W*tool.IN_LEN;
			tool.F_HEIGHT=4*tool.BORDER+tool.MAP_H*tool.IN_LEN+60;
			tool.HOUR=0;tool.MINUTE=0;tool.SECOND=0;
		}else if(tool.LEVEL==10) {
			
		}

	}
}
