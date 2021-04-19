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
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.justhabit.model.controller.PanelChangeControl;

public class ReviseInfo extends JFrame {
	
	private ReviseInfo reviseInfo;
	
	public ReviseInfo() {
		
		this.reviseInfo = this;
		
		this.setLayout(null);
		this.setSize(900, 700);
		this.setBackground(Color.lightGray);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.add(new TopPanel());
		
		JPanel userPanel = new JPanel();
		userPanel.setLayout(null);
		userPanel.setBounds(0,80,900,480);
		userPanel.setBackground(Color.white);
		this.add(userPanel);
		
		JLabel id  = new JLabel("ID : ");
		JLabel userName  = new JLabel("닉네임 : ");
		JLabel email = new JLabel("이메일 : ");
		JLabel levelExp = new JLabel("레벨 : ");
	    id.setFont(new Font("a디딤돌",Font.BOLD,20));
	    userName.setFont(new Font("a디딤돌",Font.BOLD,20));
	    email.setFont(new Font("a디딤돌",Font.BOLD,20));
	    levelExp.setFont(new Font("a디딤돌",Font.BOLD,20));
	    
	    
		id.setBounds(500, 110, 150, 150);
		userName.setBounds(500, 150, 150, 150);
		email.setBounds(500, 190, 150, 150);
		levelExp.setBounds(130, 290, 150, 150);
		
		userPanel.add(id);
		userPanel.add(userName);
		userPanel.add(email);
		userPanel.add(levelExp);
		
		JTextField idBox = new JTextField(17);
		JTextField usernameBox = new JTextField(17);
		JTextField emailBox = new JTextField(17);
		idBox.setBounds(580, 170, 220, 30);
		usernameBox.setBounds(580, 210, 220, 30);
		emailBox.setBounds(580, 250, 220, 30);
		userPanel.add(usernameBox);
		userPanel.add(emailBox);
		userPanel.add(idBox);
		
		JButton cancel = new JButton("취소");
		cancel.setSize(90,40);
		cancel.setLocation(465, 390);
		userPanel.add(cancel);
		cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PanelChangeControl.changeFrame(reviseInfo , new MyPage());
            }
         });
		
		
		JButton exit = new JButton("저장");
		exit.setSize(90,40);
		exit.setLocation(345, 390);
		
		exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PanelChangeControl.changeFrame(reviseInfo , new MyPage());
            }
         });
		
		
		userPanel.add(exit);
		
//		JButton revise = new JButton("회원정보수정");
//		revise.setSize(130,40);
//		revise.setLocation(670, 60);
//		this.add(revise);
		
		
		Image level = new ImageIcon("image/turtle1.jpeg").getImage().getScaledInstance(250, 250, 0);
		
		JLabel levelImg = new JLabel(new ImageIcon(level));
		levelImg.setBounds(80,60,300,300);
		this.add(levelImg);
	}
	
	
}