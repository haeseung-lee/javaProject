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
import java.text.SimpleDateFormat;

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

public class FirstFrame {
	
	UserController userController = new UserController();
	public static int loggedUserID;

   private JFrame frame ;
   private LoginPanel login;
   
   private Image ic_logo = new ImageIcon("image/nike-logo-png-21194.png").getImage().getScaledInstance(130, 70, Image.SCALE_SMOOTH);
   private JTextField txtUsername;
   private JPasswordField txtPassword;
   
   private int x, y;
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               FirstFrame window = new FirstFrame();
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
   public FirstFrame() {
      initialize();
   }
//
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
      lblLogo.setBounds(78, 90, 234, 160);
      circlePanel.add(lblLogo);
      
      JLabel lblNewLabel_1 = new JLabel("JUST HAB' IT");
      lblNewLabel_1.setForeground(Color.black);
      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
      lblNewLabel_1.setBounds(78, 210, 234, 36);
      circlePanel.add(lblNewLabel_1);      
      
      
      /* 오른쪽에 붙이는 로그인 패널*/
      login = new LoginPanel(Color.decode("#e65758"), Color.decode("#771d32"),LoginPanel.DIAGONAL_DOWN);
      login.setArc(10);
      login.setBorderColor(Color.white);
      login.setBorderWidth(2);
      login.setBounds(200, 50, 500, 320);
      frame.getContentPane().add(login);
      login.setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setBounds(229, 87, 250, 50);
      login.add(panel);
      panel.setLayout(null);
      
      txtUsername = new JTextField();
      txtUsername.setBackground(Color.WHITE);
      txtUsername.setBounds(10, 11, 230, 28);
      panel.add(txtUsername);
      txtUsername.setColumns(10);
      txtUsername.setBorder(null);
      
      JPanel panel_1 = new JPanel();
      panel_1.setLayout(null);
      panel_1.setBackground(Color.WHITE);
      panel_1.setBounds(229, 173, 250, 50);
      login.add(panel_1);
      
      txtPassword = new JPasswordField();
      txtPassword.setBounds(10, 11, 230, 28);
      panel_1.add(txtPassword);
      txtPassword.setBorder(null);
      
      JLabel lblNewLabel_2 = new JLabel("Username");
      lblNewLabel_2.setForeground(Color.WHITE);
      lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
      lblNewLabel_2.setBounds(229, 62, 250, 14);
      login.add(lblNewLabel_2);
      
      JLabel lblNewLabel_2_1 = new JLabel("Password");
      lblNewLabel_2_1.setForeground(Color.WHITE);
      lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
      lblNewLabel_2_1.setBounds(229, 148, 250, 14);
      login.add(lblNewLabel_2_1);
      
      Image loginImg = new ImageIcon("image/login-2.png").getImage().getScaledInstance(70, 70, 0);
	  JLabel lgLabel = new JLabel(new ImageIcon(loginImg));
	  lgLabel.setBounds(290, 205, 140, 140);
	  login.add(lgLabel);
      
      
      
      
      
      txtPassword.addKeyListener(new KeyAdapter() {
          
          @Override
          public void keyPressed(KeyEvent e) {
             if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                 if(userController.loginCheck(txtUsername.getText().toLowerCase(), 
                         new String(txtPassword.getPassword()))) {
                        
                          PanelChangeControl.changeFrame(frame, new TodayWord());
                   } else {
                      
                      JOptionPane.showMessageDialog(login, "ID or Password wrong\nPlease try again :)");
                      txtUsername.setText("");
                      txtPassword.setText("");
                   }
             }
          }
       });
      
      lgLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				if(userController.loginCheck(txtUsername.getText().toLowerCase(), 
	    				  new String(txtPassword.getPassword()))) {
	    			  //login   
	        		  	PanelChangeControl.changeFrame(frame, new TodayWord());
	    		  } else {
	    			  //login failed
	    			  JOptionPane.showMessageDialog(login, "ID or Password wrong\nPlease try again :)");
	    			  txtUsername.setText("");
	    			  txtPassword.setText("");
	    		  }
			}
		});
		
      
      Image signupImg = new ImageIcon("image/sign-up.png").getImage().getScaledInstance(40, 40, 0);
	  JLabel suLabel = new JLabel(new ImageIcon(signupImg));
	  suLabel.setBounds(380, 205, 140, 140);
	  login.add(suLabel);
      
	  suLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				frame.setVisible(false);
	        	 SecondFrame.main(null);
			}
	  });
      
      JLabel lblNewLabel_3 = new JLabel("LOGIN");
      lblNewLabel_3.setForeground(Color.WHITE);
      lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
      lblNewLabel_3.setBounds(229, 25, 115, 14);
      login.add(lblNewLabel_3);
      
      JLabel lblNewLabel_3_1 = new JLabel("X");
      lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(JOptionPane.showConfirmDialog(null, "Are you sure to close this application ?") == 0)
               System.exit(0);
         }
      });
      lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_3_1.setForeground(Color.WHITE);
      lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 20));
      lblNewLabel_3_1.setBounds(469, 11, 21, 28);
      login.add(lblNewLabel_3_1);
      
   }
   
}