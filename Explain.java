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
import javax.swing.JLabel;

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
		
		JButton btn1 = new JButton("<<返回");

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tool.STATE=-2;
			}
		});
		btn1.setBounds(211, 195, 80, 23);
		contentPane.add(btn1);
		
		JButton btn1_1 = new JButton("自定义游戏");
		btn1_1.setBounds(23, 58, 118, 98);
		contentPane.add(btn1_1);
		
		JButton btn1_1_1 = new JButton("猜谜语游戏");
		btn1_1_1.setBounds(173, 58, 118, 98);
		contentPane.add(btn1_1_1);
		

	}
}
