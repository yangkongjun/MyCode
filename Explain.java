package p03;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Explain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Explain frame = new Explain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Explain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("扫雷游戏");
		setBounds(400, 200, 333, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txt1 = new JTextArea();
		txt1.setEditable(false);
		txt1.setText("\t游戏说明：\n\n"
				+ " 1.游戏核心目标：根据提示猜到谜语，即可通关。\n"
				+ " 2.点击头像，输入答案！\n"
				+ " 3.通过打开土地寻找谜语提示\n"
				+ " 4.请在最短的时间猜出这个谜语吧\n"
				+ " 5.注意：不要踩到地雷哦！");
		txt1.setBounds(10, 10, 294, 190);
		contentPane.add(txt1);
		
		JButton btn1 = new JButton("<<返回");

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.STATE=-2;
			}
		});
		btn1.setBounds(214, 205, 80, 23);
		contentPane.add(btn1);
		

	}
}
