package com.justhabit.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.justhabit.model.controller.HaeinController;
import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.dto.AllHabitDTO;

public class DeleteHabit extends JFrame {
	
	private HaeinController habitDayController = new HaeinController();
	public static int deleteHabitID;
	private String btn1 = "삭제";
	private String btn2 = "취소";
	public static String selectedHabbit;
	public static int selectedHabitNum;
	public JFrame mv = null;
	public JFrame dh = this;
	

	List<AllHabitDTO> allhabitList = habitDayController.selectAllHabitBy(FirstFrame.loggedUserID);
	
	
	public DeleteHabit(JFrame mv) {
		
		this.mv = mv;
		
		this.setLayout(null);
		this.setSize(300,200);
		this.setVisible(true);
		
		JLabel ask = new JLabel("어떤 습관을 삭제하시겠습니까?");
		ask.setFont(new Font("THE외계인설명서", Font.BOLD, 15));
		ask.setBounds(50, 40, 200, 20);
		this.add(ask);
		
		String[] habit = new String[allhabitList.size()];
		
		for(int h = 0; h < allhabitList.size(); h++) {
			habit[h] = allhabitList.get(h).getHabitName();
		}
		
		JComboBox habitCombo = new JComboBox(habit);
		
		
		habitCombo.setBounds(50, 80, 200, 20);
		this.add(habitCombo);
		
		 ActionListener actionListener = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        System.out.println("Selected: " + habitCombo.getSelectedItem());
		        System.out.println(", Index: " + habitCombo.getSelectedIndex());
		        selectedHabbit = (String)habitCombo.getSelectedItem();
			    selectedHabitNum = habitCombo.getSelectedIndex();
		        
		      }
		    };
		    habitCombo.addActionListener(actionListener);
		

		//버튼 클릭시 event - 선택한 습관을 입력받아야. 입력받을 때 habit id를 받기
		//해당 습관에 해당하는 habid id가 db에서 삭제되어야 함 (JDBC)
		//삭제되고 난 후 새로운 mainpage로 돌아가기 
		
		
		JButton delete = new JButton("삭제");
		JButton cancel = new JButton("취소");
		
		delete.setBounds(70, 120, 60, 30);
		cancel.setBounds(150, 120, 60, 30);
		
		this.add(delete);
		this.add(cancel);
		
		
		
		delete.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  
	        	  System.out.println(selectedHabbit);
	        	  System.out.println(selectedHabitNum);
	        	  deleteHabitID  = allhabitList.get(selectedHabitNum).getHabitID();
	        	  
	        	  System.out.println(deleteHabitID);
	        	  
	        	  habitDayController.deleteHabitBy(deleteHabitID);
	        	  
	        	  mv.setVisible(false);
	        	  PanelChangeControl.changeFrame(dh, new MainPage());
	        	  
	          }
	       });


		cancel .addActionListener(e -> {
			dh.dispose();
		});

		
		
	}	
	

}
	  

