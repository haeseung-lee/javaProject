package com.justhabit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.dto.HabbitAddDTO;

public class HabbitAdd extends JFrame {


   public static HabbitAddDTO habbitAddDTO = new HabbitAddDTO();
   private HabbitAdd habbitAdd = this;
   

   public HabbitAdd() {
	   
	HabbitAdd.habbitAddDTO.setUserID(FirstFrame.loggedUserID);

	habbitAdd.setLayout(null);
	habbitAdd.setSize(900, 462);
		
	   
	
	JPanel question = new JPanel();
	
	JLabel label1 = new JLabel("A-1) 무슨 습관을 들이고 싶나요?");
   
	label1.setFont(new Font("THE Oegyeinseolmyeongseo", Font.BOLD, 20));
	label1.setSize(400,200);
	label1.setLocation(260,0);
	habbitAdd.add(label1);

	JTextField text = new JTextField(15);
	text.setSize(400,120);
	text.setLocation(250, 150);
	text.setEditable(true);
	habbitAdd.add(text);
	question.validate();
	
	
	JButton jbutton1 = new JButton("메인으로");
    jbutton1.setSize(100,50);
    jbutton1.setLocation(300, 280);
    habbitAdd.add(jbutton1); 
	
	JButton jbutton2 = new JButton("다음");
	jbutton2.setSize(100,50);
	jbutton2.setLocation(480, 280);
	habbitAdd.add(jbutton2);
      	
	
	jbutton1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        	System.out.println(text.getText());
        	HabbitAdd.habbitAddDTO.setHabitName(text.getText());
        	PanelChangeControl.changeFrame(habbitAdd , new MainPage());
        	
        }
     });

  	jbutton2.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
        	
        System.out.println(text.getText());
	
        HabbitAdd.habbitAddDTO.setHabitName(text.getText());
        PanelChangeControl.changeFrame(habbitAdd , new HabbitAdd3());
        }
        
     });
  	

  		habbitAdd.setVisible(true);
  		habbitAdd.setResizable(false);
  		habbitAdd.setLocationRelativeTo(null); 
  		habbitAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   } 
}