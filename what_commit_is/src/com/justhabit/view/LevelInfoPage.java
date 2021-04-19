package com.justhabit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.justhabit.model.dto.UserLevelDTO;

public class LevelInfoPage extends JFrame {

	public JFrame level = null;
	public JFrame my = this;
	
	
	public LevelInfoPage(MyPage myPage, String userName) {
		this.level = this;
		my = myPage;
		
		this.setLayout(null);
		this.setSize(600,500);
		this.setVisible(true);
		this.setBackground(Color.white);
		this.setLocationRelativeTo(null);
		
		UserLevelDTO myUserLevel = new TopPanel().userInfo(FirstFrame.loggedUserID);
		
		for(int i = 1; i <= 5; i++) {
			
			Image level = new ImageIcon("image/" + i + "렙계란.PNG").getImage().getScaledInstance(65+(i*13), 70+(i*11), 0);
			
			JLabel levelImg = new JLabel(new ImageIcon(level));
			levelImg.setBounds((30 * i + (i-1) * 80),40,100,100);
			this.add(levelImg);
			
			JLabel levelName;
			if (i == 1) {
				levelName = new JLabel("<html>" + i + " Level<br><br>" 
			                              +"<i>회원가입</i></html>");
				levelName.setBounds((50 * i),165,100,100);

			} else {
				
				levelName = new JLabel("<html>" + i + " Level<br><br>" 
                        +"습관 수 "+ i + "<br>" 
						+"성공 수 "+ (i+2)
                        +"</html>");
				levelName.setBounds((50 * i + (i-1) * 55),70,300,300);

			}
			if(i == myUserLevel.getUserLevel()) {
				levelName.setForeground(Color.BLUE);
			}
			levelName.setFont(new Font("a디딤돌",Font.BOLD, 8 + i*2));
			this.add(levelName);

			
		}
		
		
		
		
		JLabel level_info = new JLabel("");
		
		level_info.setText("<html><font color=blue>" + userName.toUpperCase() + "</font>님의<br>총 습관 수 <font color=green> " + myUserLevel.getNumOfHabits() + "</font><br>"
				            +  "총 성공 수  <font color=green>" + (myUserLevel.getSuccessOfCheck() + myUserLevel.getSuccessOfTimer()) + "</font> "
				            +  "(카운트 습관 <font color=green>" + myUserLevel.getSuccessOfCheck() + "</font>"
				            +  ",타이머 습관<font color=green>" + myUserLevel.getSuccessOfTimer() + ")</font>");
		
		
		level_info.setFont(new Font("a디딤돌",Font.BOLD,15));
		level_info.setBounds(100, 250 , 500, 300);
		level_info.setVisible(true);
		this.add(level_info);
		
		
		JButton confirm = new JButton("확인");
		confirm.setSize(180,40);
		confirm.setLocation(390, 390);
		this.add(confirm);
		
		confirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	level.setVisible(false);
            	new MyPage();
            }
         });
		
		
	}
	
	
}
