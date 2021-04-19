package com.justhabit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.controller.UserController;
import com.justhabit.model.dto.UserDTO;
import com.justhabit.model.dto.UserLevelDTO;

public class MyPage extends JFrame {
	
	private MyPage myPage;
	private UserController userController = new UserController();
	
	
	public MyPage() {
		
		this.myPage = this;
		
		myPage.setLayout(null);
		myPage.setSize(900, 700);
		myPage.setBackground(Color.lightGray);
		myPage.setLocationRelativeTo(null);
		myPage.setVisible(true);
		myPage.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel top = new TopPanel();
		myPage.add(top);
		
		
		
		//가운데 화면
		JPanel userPanel = new JPanel();
		userPanel.setLayout(null);
		userPanel.setBounds(0,80,900,530);
		userPanel.setBackground(Color.white);
		this.add(userPanel);
		
		
		JLabel label_username  = new JLabel("Username : ");
		JLabel label_email  = new JLabel("Email : ");
		JLabel label_password = new JLabel("Password : ");
		JLabel label_pin = new JLabel("PIN : ");
		JLabel levelExp = new JLabel("레벨 : ");
		JLabel label_edit = new JLabel("수정");
		JLabel label_delete = new JLabel("탈퇴");
		label_username.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		label_email.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		label_password.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		label_pin.setFont(new Font("THE외계인설명서",Font.BOLD,20));
	    levelExp.setFont(new Font("THE외계인설명서",Font.BOLD,15));
	    label_edit.setFont(new Font("THE외계인설명서",Font.BOLD,13));
	    label_delete.setFont(new Font("THE외계인설명서",Font.BOLD,13));

	    
	    label_username.setBounds(400, 60, 150, 150);
	    label_email.setBounds(400, 100, 150, 150);
	    label_password.setBounds(400, 140, 150, 150);
	    label_pin.setBounds(550, 270, 150, 150);
		levelExp.setBounds(130, 190, 150, 150);
		label_edit.setBounds(650, 400, 140, 140);
		label_delete.setBounds(750, 400, 140, 140);

		
		userPanel.add(label_username);
		userPanel.add(label_email);
		userPanel.add(label_password);
		userPanel.add(label_pin);
		userPanel.add(levelExp);
		userPanel.add(label_edit);
		userPanel.add(label_delete);

		
		UserDTO myUser = userController.userInfo(FirstFrame.loggedUserID);
		
		String pwd = "";
		for(int i = 0; i < myUser.getUserPwd().length(); i++) {
			if(i == 0)
				pwd += myUser.getUserPwd().charAt(0);
			else
				pwd += "*";
		}
		
		String pin = ""; 
		for(int i = 0; i < 4; i++ ) {
			if(i == 0)
				pin += Integer.toString(myUser.getUserPin()).charAt(0);
			else
				pin += '*';
		}
			
		
		
		JTextField myUser_name  = new JTextField(myUser.getUserName());
		JTextField myUser_email = new JTextField(myUser.getUserEmail());
		JTextField myUser_pwd  = new JTextField(pwd);
		JTextField myUser_pin = new JTextField(pin);
		
		JLabel myUser_level = new JLabel("<html><font color=green>"+ Integer.valueOf(myUser.getUserLevel()).toString()+"</font></html>");
		myUser_name.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		myUser_email.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		myUser_pwd.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		myUser_pin.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		myUser_level.setFont(new Font("THE외계인설명서",Font.ITALIC,20));
//		myUser_level.setBackground(Color.YELLOW);

		
		
		myUser_name.setBounds(600, 120, 200, 30);
		myUser_email.setBounds(600, 160, 200, 30);
		myUser_pwd.setBounds(600, 200, 200, 30);
		myUser_pin.setBounds(600, 330, 200, 30);
		myUser_level.setBounds(200, 190, 150, 150);

		
		myUser_name.setEditable(false);
		myUser_email.setEditable(false);
		myUser_pwd.setEditable(false);
		myUser_pin.setEditable(false);
		
		
		userPanel.add(myUser_name);
		userPanel.add(myUser_email);
		userPanel.add(myUser_pwd);
		userPanel.add(myUser_pin);
		userPanel.add(myUser_level);

		
		
		JLabel instruction  = new JLabel();
		instruction.setFont(new Font("THE외계인설명서",Font.ITALIC,21));
		instruction.setVisible(false);
		instruction.setBounds(400, 290, 420, 20);
		userPanel.add(instruction);
		
		
		
		JButton level_info = new JButton("레벨 정보");
		level_info.setSize(150,30);
		level_info.setLocation(120, 290);
		level_info.setBackground(Color.YELLOW);
		userPanel.add(level_info);
		level_info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            	new LevelInfoPage(myPage, myUser.getUserName());
            }
         });
		
		
		myUser_pin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' 
               		 || ke.getKeyChar() == '\b') {
                	myUser_pin.setEditable(true);
                } else {
               	 JOptionPane.showMessageDialog(myPage, "Please type only number(0~9)");
               	myUser_pin.setText("");
                }
             }
          });
		
		
		Image editImg = new ImageIcon("image/edit-user.png").getImage().getScaledInstance(70, 70, 0);
		JLabel edLabel = new JLabel(new ImageIcon(editImg));
		edLabel.setBounds(600, 350, 140, 140);
		userPanel.add(edLabel);
		
		edLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				myUser_name.setEditable(true);
            	myUser_name.setBackground(Color.LIGHT_GRAY);
        		myUser_email.setEditable(true);
            	myUser_email.setBackground(Color.LIGHT_GRAY);
        		myUser_pwd.setEditable(true);
            	myUser_pwd.setBackground(Color.LIGHT_GRAY);
            	
            	
            	if(myUser_pin.getText().equals(String.valueOf(myUser.getUserPin()))) {
            		
            		myUser.setUserName(myUser_name.getText());
            		myUser.setUserEmail(myUser_email.getText());
            		myUser.setUserPwd(myUser_pwd.getText());
            		
            		String result = userController.updateUser(myUser, myUser_name.getText());
            		
            		switch(result) {
            		case "성공" : 
            			JOptionPane.showMessageDialog(myPage, "회원정보 수정되었습니다!");
            			break;
            		default :
            			JOptionPane.showMessageDialog(myPage, result);
            		}
            		
            	}else {
            		JOptionPane.showMessageDialog(myPage, "가입시 입력한 PIN을 입력해주세요");
      			  	myUser_pin.setText("");
            	}
    

            	instruction.setText("<html>회원정보 <font color=red>수정</font>를 위한 <font color=blue>PIN</font>을 입력해주세요 :)</html>");
            	instruction.setVisible(true);
            	
        		myUser_pin.setEditable(true);
        		myUser_pin.setText("");
            	myUser_pin.setBackground(Color.GRAY);
            	
			}
		});
		
		

//		JButton exit = new JButton("탈퇴하기");
//		exit.setSize(180,40);
//		exit.setLocation(610, 430);
//		userPanel.add(exit);
//		
		Image deleteImg = new ImageIcon("image/delete-user.png").getImage().getScaledInstance(70, 70, 0);
		JLabel dtLabel = new JLabel(new ImageIcon(deleteImg));
		dtLabel.setBounds(700, 350, 140, 140);
		userPanel.add(dtLabel);
		
		
		dtLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            	instruction.setText("<html>회원 <font color=red>탈퇴</font>를 위한 <font color=blue>PIN</font>을 입력해주세요 :(</html>");
            	instruction.setVisible(true);

        		myUser_pin.setEditable(true);
            	myUser_pin.setBackground(Color.GRAY);
            	
            	if(myUser_pin.getText().equals(String.valueOf(myUser.getUserPin()))) {
            		if(userController.deleteUser(myUser)) {
                		JOptionPane.showMessageDialog(myPage, myUser.getUserName() + "님의 탈퇴가 완료 되었습니다");
            			myPage.setVisible(false);
            			FirstFrame.main(null);
            		}
            		else
                		JOptionPane.showMessageDialog(myPage, myUser.getUserName() + "님의 탈퇴가 실패했습니다");
            	} else {
      			  	JOptionPane.showMessageDialog(myPage, "가입시 입력한 PIN을 입력해주세요");
      			  	myUser_pin.setText("");

            	}
            }
         });
		
		
		Image level = new ImageIcon("image/" + myUser.getUserImage()).getImage().getScaledInstance(130, 130, 0);
		
		JLabel levelImg = new JLabel(new ImageIcon(level));
		levelImg.setBounds(50,30,300,300);
		userPanel.add(levelImg);
		
		//메뉴패널추가
		JPanel botPan = new JPanel();
		botPan.setLayout(new GridLayout(1,6));
		botPan.setSize(900, 50);
		botPan.setLocation(0, 615);
		botPan.setBackground(Color.decode("#771d32"));
		this.add(botPan);
		//메뉴목록
		String[] menu = {"메인페이지", "습관등록","습관삭제", "마이페이지"};
		
		//버튼추가
		JButton[] menuButton = new JButton[menu.length];
		for(int i =0; i <menu.length; i++) {
			
			menuButton[i] = new JButton(menu[i]);
			menuButton[i].setBackground(Color.decode("#e65758"));
			menuButton[i].setForeground(new Color(255,249,247));
			menuButton[i].setFont(new Font("THE외계인설명서", Font.BOLD, 17));
			botPan.add(menuButton[i]);
		}
		menuButton[0].addActionListener(new ActionListener() {
	          
	          @Override
	          public void actionPerformed(ActionEvent e) {
	             PanelChangeControl.changeFrame(myPage, new MainPage());
	          }
	       });
		menuButton[1].addActionListener(new ActionListener() {
	          
	          @Override
	          public void actionPerformed(ActionEvent e) {
	             PanelChangeControl.changeFrame(myPage, new HabbitAdd());
	          }
	       });
		
		menuButton[2].addActionListener(new ActionListener() {
	          
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  new DeleteHabit(myPage);
	          }
	       });
		
		menuButton[3].setEnabled(false);
		
	}
	
		
}