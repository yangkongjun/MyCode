package p03;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/*
 * 3个空行：基本模块相互之间各不搭边
 * 2个空行：程序一个模块的子小功能
 * 1个空行：又并列情况
 *我来测试一下可不可以用了。
 * */

public class runing {


	public static void main(String[] args) {
		
		while(true) {												//一直循环知道结束====第一层循环。
			GameSelect gs=new GameSelect();
			gs.setVisible(true);
			
			
			while(tool.STATE==-1){System.out.println("等待选项");}	//暂停，等选择。====真是奇怪。在老机房不需要一直输出。他新的就要一直输出。
			gs.ReLevel();
			gs.dispose();//删除窗口
			
			
			if(tool.STATE==1) {//游戏说明界面
				Explain ex=new Explain();
				ex.setVisible(true);
				Thread t1=new Thread();
				t1.start();
		
				//************游戏说明界面的死循环运行---************
				while(true) {
					try {
						if(tool.STATE==-2) {ex.dispose();tool.STATE=-1;break;}
						t1.sleep(50);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			
			
			else if(tool.STATE==0) {//游戏界面
				Game ga=new Game();
				ga.setVisible(true);
				Thread t1=new Thread();//控制
				t1.start();
				Thread t2=new Thread();//时间设置
				t2.start();
				
				
				//************鼠标监听点击事件。---场外判断，无需放在while里************
				ga.addMouseListener(new MouseAdapter() {
					int tmpx,tmpy;
					boolean flag=false;//检测鼠标位置是否在里面
					@Override

//					public void mousePressed(MouseEvent e) {//实现按下效果。---还可以去实现鼠标经过效果+加线程去显示[线程类]。---好像有问题比如我不左击，我右击。
//						tool.MOUSE_X=e.getX();
//						tool.MOUSE_Y=e.getY();
//						tool.LEFT=true;
//						//注意：在笛卡尔坐标系中我的y增加。相当于我的x增加。所以一下设置要反一下。
//						tmpx=(tool.MOUSE_Y-tool.BORDER*3-60)/tool.IN_LEN+1;//60是菜单栏高度
//						tmpy=(tool.MOUSE_X-tool.BORDER-10)/tool.IN_LEN+1;//误差10应该是窗口边框占10
//						System.out.println(tmpx+","+tmpy);
//						if(flag)ga.lb2[tmpx][tmpy].setIcon(new ImageIcon(MapTop.class.getResource("112.png")));
//					}
					
					public void mouseReleased(MouseEvent e) {

						if(tool.STATE==-1)return;//													游戏结束，无法继续鼠标点击，只能重新开始游戏。
						
						
						
			//================解决点击按钮以外也会把首行首列展示的情况。--实现：判断是否鼠标位置在我们的偏移量里面。======
						
						flag=false;
						tool.MOUSE_X=e.getX();
						tool.MOUSE_Y=e.getY();
						//																			注意：在笛卡尔坐标系中我的y增加。相当于我的x增加。所以一下设置要反一下。
						tmpx=(tool.MOUSE_Y-tool.BORDER*3-60)/tool.IN_LEN+1;//						60是菜单栏高度
						tmpy=(tool.MOUSE_X-tool.BORDER-10)/tool.IN_LEN+1;//							误差10应该是窗口边框占10
						tool.LEFT=true;
						
//						System.out.println(tmpx+","+tmpy);
						if(tool.MOUSE_X>tool.BORDER+10&&tool.MOUSE_Y>tool.BORDER*3+60) flag=true;
						if(!flag) return;//															不符合条件直接退出
						
			//================解决点击按钮以外也会把首行首列展示的情况。--实现：判断是否鼠标位置在我们的偏移量里面。======				
						
						
						
						
						if(e.getButton()==1) {//													左键按下
							
							if(tmpx>=1&&tmpx<=tool.MAP_H &&tmpy>=1&&tmpy<=tool.MAP_W) {
								if(tool.MAP_TOP[tmpx][tmpy]==0) {
									ga.show(tmpx,tmpy);
//									System.out.println(tool.TOTAL);
									//可以整合成一个失败函数
									if(ga.Check()) {//是否踩到地雷？
										ga.allboom();
										JOptionPane.showMessageDialog(null, "你踩雷了！");
										ga.btn1.setIcon(new ImageIcon(Game.class.getResource("bad.png")));
										tool.STATE=-1;
									}
									
									else if(tool.TOTAL==tool.BOOM){//
										ga.allopen();
										JOptionPane.showMessageDialog(null, "你成功了！");
										ga.btn1.setIcon(new ImageIcon(Game.class.getResource("happy.png")));
										tool.STATE=-1;
									}
									//可以整合成一个成功函数
									
								}
														
							}
							
							tool.LEFT=false;
//							System.out.println(e.getX()+","+e.getY()); 					
						}
						else if(e.getButton()==2) {//												中键按下
							tool.MOUSE_X=e.getX();
							tool.MOUSE_Y=e.getY();
							tool.MIDDLE=true;
							System.out.println(e.getX()+","+e.getY());					
						}
						else if(e.getButton()==3) {//												右键按下
							tool.RIGHT=true;
//							System.out.println(e.getX()+","+e.getY());
							
							if(tool.MAP_TOP[tmpx][tmpy]==0) {
								tool.MAP_TOP[tmpx][tmpy]=1;
								ga.lb2[tmpx][tmpy].setIcon(new ImageIcon(GameSelect.class.getResource("TopFlag.png")));
							}
							else if(tool.MAP_TOP[tmpx][tmpy]==1) {
								tool.MAP_TOP[tmpx][tmpy]=0;
								ga.lb2[tmpx][tmpy].setIcon(new ImageIcon(GameSelect.class.getResource("11.png")));
							}
							else if(tool.MAP_TOP[tmpx][tmpy]==-1) {
								ga.look(tmpx,tmpy);
								if(ga.Check()) {
									ga.allboom();
									JOptionPane.showMessageDialog(null, "你踩雷了！");
									ga.btn1.setIcon(new ImageIcon(Game.class.getResource("bad.png")));
									tool.STATE=-1;
								}
								if(tool.TOTAL==tool.BOOM){
									ga.allopen();
									JOptionPane.showMessageDialog(null, "你成功了！");
									ga.btn1.setIcon(new ImageIcon(Game.class.getResource("happy.png")));
									tool.STATE=-1;
								}
							}
									
							tool.RIGHT=false;
						}
					}
				});
				//如果监控放下面，那就一直先执行while死循环，监听代码都没运行
				//************游戏界面的死循环运行---鼠标监听在while循环外************
				int time=0;//因为无法实现在while中即实现1秒增加1.又要实现0.05秒检测一下是否退出。所以用了这个办法来解决。
				while(true) {											//退出游戏后进入选择界面
					
					try {
						if(tool.STATE==-2) {ga.dispose();tool.STATE=-1;break;}
						if(tool.STATE==-3) {System.exit(0);}
						t1.sleep(50);//50
						if(tool.STATE==-1)continue;
						time+=50;
						if(time>=1000) {
							//记录时间
							time=0;
							tool.SECOND++;
							if(tool.SECOND==60) {tool.SECOND=0;tool.MINUTE++;}
							if(tool.MINUTE==60) {tool.MINUTE=0;tool.HOUR++;}
							ga.t1.setHorizontalAlignment(SwingConstants.CENTER);
							ga.t1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
							ga.t1.setText(tool.HOUR+" : "+tool.MINUTE+" : "+tool.SECOND);
						}
		
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}//用来检测是否要重新设置游戏。
				}
			}
			

			
			

		}
		
	}

}
