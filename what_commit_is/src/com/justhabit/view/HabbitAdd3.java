package com.justhabit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.dto.HabbitAddDTO;

public class HabbitAdd3 extends JFrame {

	public static HabbitAddDTO habbitAddDTO = new HabbitAddDTO();
	private HabbitAdd3 habbitAdd3;
	
	String result = "";
	
	public HabbitAdd3() {
	   
	   this.habbitAdd3 = this;

	   habbitAdd3.setLayout(null);
	   habbitAdd3.setBounds(0, 100, 900, 462);
	   habbitAdd3.setBackground(Color.lightGray);
	   habbitAdd3.setLocationRelativeTo(null);
	   habbitAdd3.setVisible(true);


	   JLabel label1 = new JLabel("A-2) 어떤 요일에 하고 싶나요?");
	   
	   label1.setFont(new Font("THE Oegyeinseolmyeongseo", Font.BOLD, 20));
	   label1.setSize(500,200);
	   label1.setLocation(260,0);
	   habbitAdd3.add(label1);
       
       JPanel checkPanel = new JPanel();
       checkPanel.setSize(350,50);
       checkPanel.setLocation(250,170);
       
       String[] Days = {"일","월","화","수","목","금","토"};
       JCheckBox[] buttons = new JCheckBox[7];
       
       for(int i = 0; i < buttons.length; i++) {
    	   
    	   buttons[i] = new JCheckBox(Days[i]);
    	   checkPanel.add(buttons[i]);
       }
       
		habbitAdd3.add(checkPanel);
		
		JButton jbutton1 = new JButton("이전");
	   	jbutton1.setSize(80,50);
       	jbutton1.setLocation(300, 280);
       	habbitAdd3.add(jbutton1);	
       
       	JButton jbutton2 = new JButton("다음");
       	jbutton2.setSize(80,50);
       	jbutton2.setLocation(450, 280);
       	habbitAdd3.add(jbutton2);
      	
      	jbutton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            	
            	PanelChangeControl.changeFrame(habbitAdd3 , new HabbitAdd());

            }
            
         }); 

      	jbutton2.addMouseListener(new MouseAdapter() {
      		@Override
      		public void mouseClicked(MouseEvent e) {
      			
      			String selectDay = "";
      			
      			for (int i = 0; i < buttons.length; i++) {
      				
					if(buttons[i].isSelected()) {
						
						selectDay += buttons[i].getText();
					}
				}
      			
      		System.out.println("strDay : " + selectDay);
      		HabbitAdd.habbitAddDTO.setHabitDays(selectDay);
      		System.out.println(HabbitAdd.habbitAddDTO);
      			
      		PanelChangeControl.changeFrame(habbitAdd3 , new HabbitAdd4());
      		
      		}
      		
      	});

   }
}
