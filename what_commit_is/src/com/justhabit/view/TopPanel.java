package com.justhabit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.justhabit.model.controller.UserController;
import com.justhabit.model.dto.UserLevelDTO;

public class TopPanel extends JPanel{
	
	private UserController userController = new UserController();
	
	private UserLevelDTO userLevelInfo = null;
	
	public TopPanel() {
		//상단바
		this.setLocation(0, 0);
		this.setSize(900, 100);
		this.setLayout(null);
		this.setBackground(Color.decode("#e65758"));
		
		
		/* 로그인 된 유저의 레벨관련 정보 객체 생성*/
		userLevelInfo = userInfo(FirstFrame.loggedUserID);
		
		JLabel title = new JLabel("JUST HAB' IT");
		title.setFont(new Font("THE외계인설명서", Font.BOLD,50));
		title.setBounds(300,20,500,70);
		title.setForeground(new Color(255,249,247));
		title.setVisible(true);
		this.add(title);
		
		JLabel userLvl = new JLabel("Lvl :  "+ userLevelInfo.getUserLevel());
		userLvl.setFont(new Font("a디딤돌",Font.BOLD, 15));
		userLvl.setBounds(10, 55, 60, 60);
		userLvl.setForeground(new Color(255,249,247));
		userLvl.setVisible(true);
		this.add(userLvl);
		
		System.out.println(userLvl.getText() + " *****************************");

		String level_image = "";
		switch(userLevelInfo.getUserLevel()) {
			case 1 : level_image = "1렙계란.PNG"; break;
			case 2 : level_image = "2렙계란.PNG"; break;
			case 3 : level_image = "3렙계란.PNG"; break;
			case 4 : level_image = "4렙계란.PNG"; break;
			case 5 : level_image = "5렙계란.PNG"; break;
		}
		
		
		//좌측이미지
		Image leftImage = new ImageIcon("image/" + level_image).getImage().getScaledInstance(100, 70, 0);
		
		JLabel leftLabel = new JLabel(new ImageIcon(leftImage));
		

		leftLabel.setBounds(0, 0, 80, 80);

		this.add(leftLabel);
	
		//오늘날짜 출력
		Date today = new Date();
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
		JLabel dayPrint = new JLabel(dayFormat.format(today));
		dayPrint.setBounds(680,0, 200, 50);
		dayPrint.setFont(new Font("THE외계인설명서",Font.BOLD,15));
		dayPrint.setForeground(new Color(255,249,247));
		this.add(dayPrint);
	}
	
	public UserLevelDTO userInfo(int loggedID) {
		
		UserLevelDTO userLevelInfo = userController.userLevel(loggedID);
		
		return userLevelInfo;
	}
			
}
