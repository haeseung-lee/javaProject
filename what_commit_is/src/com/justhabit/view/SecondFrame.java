package com.justhabit.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.controller.UserController;

public class SecondFrame {
	UserController userController = new UserController();
	public static int loggedUserID;

   private JFrame frame ;
   
   private Image ic_logo = new ImageIcon("image/nike-logo-png-21194.png").getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
   private JTextField sign_username;
   private JPasswordField sign_pwd;
   private JPasswordField sign_pwd_check;
   private JTextField sign_email;
   private JTextField sign_PIN;
   
   private int x, y;
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
            	SecondFrame window = new SecondFrame();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the application.
    */
   public SecondFrame() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {

      /* FIRST FRAME FOR LOGIN AND SIGNUP */
      frame = new JFrame();
      frame.setBounds(100, 100, 700, 400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setUndecorated(true);
      frame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
      frame.getContentPane().setLayout(null);
      frame.setLocationRelativeTo(null);

///////////////////////////////////////////////////////////////////////////////////
      /* CIRCLEPANEL AT LEFT */
      LoginPanel circlePanel = new LoginPanel(Color.LIGHT_GRAY, Color.DARK_GRAY);
      circlePanel.addMouseMotionListener(new MouseMotionAdapter() {
         @Override
         public void mouseDragged(MouseEvent e) {
            frame.setLocation(frame.getX() + e.getX() - x, frame.getY() + e.getY() - y);
         }
      });
      circlePanel.addMouseListener(new MouseAdapter() {
         @Override
         public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            
         }
      });
      circlePanel.setBounds(0, 0, 400, 400);
      circlePanel.setArc(circlePanel.getWidth());
      circlePanel.setBorderColor(Color.white);
      circlePanel.setBorderWidth(2);
      frame.getContentPane().add(circlePanel);
      circlePanel.setLayout(null);
      
      JLabel lblLogo = new JLabel("");
      lblLogo.setIcon(new ImageIcon(ic_logo));
      lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
      lblLogo.setBounds(78, 71, 234, 160);
      circlePanel.add(lblLogo);
      
      
      JLabel lblNewLabel_1 = new JLabel("JUST HAB' IT");
      lblNewLabel_1.setForeground(Color.black);
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
      lblNewLabel_1.setBounds(78, 210, 234, 36);
      circlePanel.add(lblNewLabel_1);      
      
      
      /* 오른쪽에 붙이는 로그인 패널*/
      LoginPanel signup = new LoginPanel(Color.decode("#e65758"), Color.decode("#771d32"),LoginPanel.DIAGONAL_DOWN);
      signup.setArc(10);
      signup.setBorderColor(Color.white);
      signup.setBorderWidth(2);
      signup.setBounds(200, 50, 500, 320);
      frame.getContentPane().add(signup);
      signup.setLayout(null);
      

      
      sign_username = new JTextField();
      sign_username.setBackground(Color.WHITE);
      sign_username.setBounds(330, 60, 120, 20);
      signup.add(sign_username);
      sign_username.setBorder(null);
      

      sign_pwd = new JPasswordField();
      sign_pwd.setBounds(330, 90, 120, 20);
      signup.add(sign_pwd);
      sign_pwd.setBorder(null);
      
      sign_pwd_check = new JPasswordField();
      sign_pwd_check.setBounds(330, 120, 120, 20);
      signup.add(sign_pwd_check);
      sign_pwd_check.setBorder(null);
      
      sign_email = new JTextField();
      sign_email.setBounds(330, 150, 120, 20);
      signup.add(sign_email);
      sign_email.setBorder(null);
      
      sign_PIN = new JTextField();
      sign_PIN.setBounds(330, 214, 120, 20);
      signup.add(sign_PIN);
      sign_PIN.setBorder(null);
      sign_PIN.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent ke) {
              if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' 
             		 || ke.getKeyChar() == '\b') {
             	 sign_PIN.setEditable(true);
              } else {
             	 JOptionPane.showMessageDialog(signup, "Please type only number(0~9)");
             	 sign_PIN.setText("");
              }
           }
        });
      
      
      
      JLabel name_label = new JLabel("Username");
      name_label.setForeground(Color.WHITE);
      name_label.setFont(new Font("Tahoma", Font.BOLD, 16));
      name_label.setBounds(229, 60, 250, 14);
      signup.add(name_label);
      
      JLabel pass_label = new JLabel("Password");
      pass_label.setForeground(Color.WHITE);
      pass_label.setFont(new Font("Tahoma", Font.BOLD, 16));
      pass_label.setBounds(229, 90, 250, 14);
      signup.add(pass_label);
      
      JLabel re_label = new JLabel("Re-enter");
      re_label.setForeground(Color.WHITE);
      re_label.setFont(new Font("Tahoma", Font.BOLD, 16));
      re_label.setBounds(229, 120, 250, 14);
      signup.add(re_label);
      
      
      JLabel email_label = new JLabel("Email");
      email_label.setForeground(Color.WHITE);
      email_label.setFont(new Font("Tahoma", Font.BOLD, 16));
      email_label.setBounds(229, 150, 250, 14);
      signup.add(email_label);
      
      JLabel instruction = new JLabel("*회원정보 수정과 회원탈퇴시 필요합니다*");
      instruction.setForeground(Color.WHITE);
      instruction.setFont(new Font("THE외계인설명서", Font.ITALIC, 13));
      instruction.setBounds(229, 190, 330, 14);
      signup.add(instruction);

      
      JLabel pin_label = new JLabel("PIN(4digits)");
      pin_label.setForeground(Color.WHITE);
      pin_label.setFont(new Font("Tahoma", Font.BOLD, 16));
      pin_label.setBounds(229, 215, 250, 14);
      signup.add(pin_label);
      
      
      
            

      
      Image loginImg = new ImageIcon("image/login-2.png").getImage().getScaledInstance(40, 40, 0);
	  JLabel lgLabel = new JLabel(new ImageIcon(loginImg));
	  lgLabel.setBounds(200, 205, 140, 140);
	  signup.add(lgLabel);
	  
	  lgLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				frame.setVisible(false);
	        	 FirstFrame.main(null);
			}
		});
      
      
	  
	  Image signupImg = new ImageIcon("image/sign-up.png").getImage().getScaledInstance(70, 70, 0);
	  JLabel suLabel = new JLabel(new ImageIcon(signupImg));
	  suLabel.setBounds(290, 205, 140, 140);
	  signup.add(suLabel);
      
	  suLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				String result = userController.signupCheck(sign_username.getText().toLowerCase()
	         			, new String(sign_pwd.getPassword()), new String(sign_pwd_check.getPassword())
	         			, sign_email.getText(), sign_PIN.getText());
	         	
	         	if(result.equals("회원가입이 성공적으로 완료되었습니다 :)")) {
	         		JOptionPane.showMessageDialog(signup, result + "\n로그인 화면으로 이동합니다.");
	         		frame.setVisible(false);
	         		FirstFrame.main(null);
	         	}
	         	else {
	         		JOptionPane.showMessageDialog(signup, result);
	         		
	         	}
			}
	  });

      
      
      
      JLabel register_label = new JLabel("REGISTER");
      register_label.setForeground(Color.WHITE);
      register_label.setFont(new Font("Tahoma", Font.BOLD, 20));
      register_label.setBounds(229, 25, 115, 14);
      signup.add(register_label);
      
      JLabel x_label = new JLabel("X");
      x_label.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(JOptionPane.showConfirmDialog(null, "Are you sure to close this application ?") == 0)
               System.exit(0);
         }
      });
      x_label.setHorizontalAlignment(SwingConstants.CENTER);
      x_label.setForeground(Color.WHITE);
      x_label.setFont(new Font("Tahoma", Font.BOLD, 20));
      x_label.setBounds(469, 11, 21, 28);
      signup.add(x_label);
      
   }
   
   
}
