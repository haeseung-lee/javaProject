package com.justhabit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.justhabit.model.controller.HabbitAddController;
import com.justhabit.model.controller.PanelChangeControl;

public class HabbitAdd5 extends JFrame {

	private HabbitAdd5 habbitAdd5;
	
	public HabbitAdd5() {

	   	this.habbitAdd5 = this;
	   
	   	habbitAdd5.setLayout(null);
	   	habbitAdd5.setBounds(0, 100, 900, 462);
	   	habbitAdd5.setBackground(Color.lightGray);
	   	habbitAdd5.setLocationRelativeTo(null);
	   	habbitAdd5.setVisible(true);

	   	JLabel label1 = new JLabel();
	   	
	   	label1.setFont(new Font("THE Oegyeinseolmyeongseo", Font.BOLD, 20));
	   	label1.setSize(500,200);
	   	label1.setLocation(300,0);
	   	
	   	
	   	if(HabbitAdd.habbitAddDTO.getHabitType() == "c") {
	   		
	   		label1.setText("A-4) 하루에 몇번 할건가요?");
	   		habbitAdd5.add(label1);
	   		
	   	} else {
	   		label1.setText("A-4) 하루에 몇시간 할건가요?");
	   		habbitAdd5.add(label1);
	   	  }
	   	

	   	SpinnerNumberModel value = new SpinnerNumberModel(0,0,10,1); // 시작값, 최소값, 최대값, 증가값 , 스피너 규칙정하기

	   	JSpinner spin = new JSpinner(value);
	   	spin.setFont(new Font("Serif", Font.BOLD, 30));
	   	spin.setSize(250,50);
       	spin.setLocation(300,180);
       	habbitAdd5.add(spin);
      	
    	JButton jbutton1 = new JButton("이전");
        jbutton1.setSize(80,50);
        jbutton1.setLocation(300, 280);
        habbitAdd5.add(jbutton1);
       
      
        JButton jbutton2 = new JButton("저장");
        jbutton2.setSize(80,50);
        jbutton2.setLocation(450, 280);
        habbitAdd5.add(jbutton2);
      	
      	jbutton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
            	PanelChangeControl.changeFrame(habbitAdd5 , new HabbitAdd4());
            	
            	
            }
            
         }); 
      	
      	jbutton2.addMouseListener(new MouseAdapter() {
      		@Override
      		public void mouseClicked(MouseEvent e) {
      			
      			HabbitAdd.habbitAddDTO.setHabitGoal((Integer)spin.getValue());
//               위치        인스턴스명        집어넣기          값
      			System.out.println(HabbitAdd.habbitAddDTO);
 
      			HabbitAddController habbitAddController = new HabbitAddController();
      			
      			habbitAddController.insertAllHabbit(HabbitAdd.habbitAddDTO);
      			
      			System.out.println(HabbitAdd.habbitAddDTO);
      			
      			PanelChangeControl.changeFrame(habbitAdd5 , new MainPage());
      			
      		}
      		
      	}); 
   }

}