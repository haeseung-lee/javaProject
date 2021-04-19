package com.justhabit.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.justhabit.model.controller.PanelChangeControl;

/**
 * @author user
 * 기본 틀
 */
public class MainView extends JFrame{

	private JFrame mf;
	private JPanel cenMain;
	public MainView() {
		
		super();
		
		
		//메인프레임
		this.mf = new JFrame();
		PanelChangeControl.mainFrame = this.mf;
		PanelChangeControl.mainFrame.setVisible(false); 
		
		mf.setLayout(null);
		this.mf.setVisible(false);
		
		//메인패널
		JPanel pan = new JPanel();
		pan.setLayout(null);
		pan.setSize(900, 700);
		pan.setBackground(Color.WHITE);
		
		//상단바
		JPanel topPan = new JPanel();
		pan.add(topPan);
		topPan.setLocation(0, 0);
		topPan.setSize(900, 100);
		topPan.setLayout(null);
		topPan.setBackground(Color.GREEN);
		
		//상단바 좌측
		JPanel topPanLeft = new JPanel();
		topPanLeft.setSize(100, 100);
//		topPanLeft.setBackground(Color.W);
		
		//좌측이미지
		Image leftImage = new ImageIcon("image/turtle1.jpeg").getImage().getScaledInstance(100, 100, 0);
		
		JLabel leftLabel = new JLabel(new ImageIcon(leftImage));
		
		leftLabel.setBounds(0, 0, 80, 80);
		topPanLeft.add(leftLabel);
		topPan.add(topPanLeft);
		
		
		//하단바
		JPanel botPan = new JPanel();
		botPan.setLayout(new GridLayout(1,6));
		botPan.setSize(900, 100);
		botPan.setLocation(0, 562);
		botPan.setBackground(Color.GREEN);
		
		pan.add(botPan);

		
		//메인센터(메인패널 클래스)
//		this.cenMain = new TodayWord();
//	    mf.add(cenMain);

		
		
//		//메뉴목록
//		String[] menu = {"Main", "습관등록", "mypage", "menu3","menu4", "test"};
//		
//		//버튼추가
//		JButton[] menuButton = new JButton[menu.length];
//		for(int i =0; i <menu.length; i++) {
//			
//			menuButton[i] = new JButton(menu[i]);
//			menuButton[i].setBackground(Color.pink);
//			botPan.add(menuButton[i]);
//		}
//		
//		
//		menuButton[0].addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				PanelChangeControl.changePanel(cenMain, new MainPage());
//			}
//			
//		});
//		
//	     menuButton[1].addActionListener(new ActionListener() {
//
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	               
//	            	PanelChangeControl.changePanel(cenMain, new HabbitAdd());
//	            }
//	            
//	        });
//	     
//	     menuButton[2].addActionListener(new ActionListener() {
//
//	            @Override
//	            public void actionPerformed(ActionEvent e) {
//	               
//
//	             
//
//	            	PanelChangeControl.changePanel(cenMain, new MyPage());
//	            }
//	            
//
//	        });
//	     //테스트 삭제예정
//	     menuButton[3].addActionListener(new ActionListener() {
//	    	 
//	    	 @Override
//	    	 public void actionPerformed(ActionEvent e) {
//	    		 
//	    		 
//	    		 
//	    		 
//	    		 PanelChangeControl.changePanel(cenMain, new TimeRecordView());
//	    	 }
//	    	 
//	    	 
//	     });
//	     
//	     //테스트 삭제예정
//	     menuButton[4].addActionListener(new ActionListener() {
//	    	 
//	    	 @Override
//	    	 public void actionPerformed(ActionEvent e) {
//	    		 
//	    		 
////	    		 
//	    		 
//	    		 PanelChangeControl.changePanel(cenMain, new CheckRecordView());
//	    	 }
//	    	 
//	    	 
//	     });
	     
	     
		
		
		
		
		mf.setSize(900, 700);
		mf.add(pan);
		mf.setVisible(true);
		mf.setResizable(false);
		mf.setLocationRelativeTo(null); // 화면 가운데로
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	class ChangePageAdapter extends MouseAdapter {

//		@Override
//		public void mouseClicked(MouseEvent e) {
//			ChangePanel.changePanel(mf, cenMain, new Menu1Panel());
//		}
//	}
}
