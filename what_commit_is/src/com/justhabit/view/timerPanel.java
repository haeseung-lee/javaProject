package com.justhabit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.justhabit.model.controller.HabitRecordController;
import com.justhabit.model.dto.HabitRecordDTO;
import com.justhabit.model.dto.HabitInfoDTO;

public class timerPanel extends JPanel{
	
	JButton start; 
	JButton reset;
	JButton pause;
	JButton recordButton;
	Thread display;
	JLabel hbtHr;
	JLabel hbtMin;
	JLabel hbtSec;
	JLabel hbtMillsec;
	int hr;
	int mm;
	int ss;
	int ms;
	static int t;
	
//	static double count = 0; //시간 카운트용
	
	public timerPanel() {
		//타이머화면
		
		JPanel p =new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel c1 = new JLabel(":");
		JLabel c2 = new JLabel(":");
		JLabel c3 = new JLabel(":");
		hbtHr = new JLabel("00");
		hbtMin = new JLabel("00");
		hbtSec = new JLabel("00");
		hbtMillsec = new JLabel("00");
		
		JLabel[] clock = {c1,c2,c3,hbtHr,hbtMin,hbtSec,hbtMillsec};
		for(int i = 0; i < clock.length; i++) {
			if(i<3) {
				clock[i].setFont(new Font("D2Coding",Font.PLAIN,15));
			} else {
				clock[i].setFont(new Font("D2Coding",Font.PLAIN,15));
			}
		}
		
		
		start = new JButton("START");
		start.setBackground(new Color(171,188,180));
		start.setForeground(new Color(255,255,255));
		start.setFont(new Font("THE외계인설명서", Font.BOLD, 15));
		reset = new JButton("RESET");
		reset.setBackground(new Color(171,188,180));
		reset.setForeground(new Color(255,255,255));
		reset.setFont(new Font("THE외계인설명서", Font.BOLD, 15));
		pause = new JButton("PAUSE");
		pause.setBackground(new Color(171,188,180));
		pause.setForeground(new Color(255,255,255));
		pause.setFont(new Font("THE외계인설명서", Font.BOLD, 15));
		bp.add(start);
		bp.add(pause);
		bp.add(reset);
		bp.setBackground(new Color(249,228,183));
		
		wp.add(hbtHr);
		wp.add(c1);
		wp.add(hbtMin);
		wp.add(c2);
		wp.add(hbtSec);
		wp.add(c3);
		wp.add(hbtMillsec);
		wp.setBackground(new Color(249,228,183));
		p.add(wp);
		p.add(bp);
		add(p);
		p.setBackground(new Color(249,228,183));
		pause.setEnabled(false);
		reset.setEnabled(false);
//		this.setBackground(new Color(249,228,183));
		
		start.addActionListener(new ButtonListener());
		pause.addActionListener(new ButtonListener());
		reset.addActionListener(new ButtonListener());
	}

	/**
	 * @author 
	 * 타이머 시작,정지,종료
	 */
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			
			if(s.equals("START")) {
				start.setEnabled(false);
				pause.setEnabled(true);
				reset.setEnabled(false);
				
				display = new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						hr = Integer.parseInt(hbtHr.getText());
						mm = Integer.parseInt(hbtMin.getText());
						ss = Integer.parseInt(hbtSec.getText());
						ms = Integer.parseInt(hbtMillsec.getText());
						
						while(display == Thread.currentThread()) {
							
							hr = t / (60*60*100) % 24;
							mm = t /(60*100) % 60;
							ss = t / 100 % 60;
							ms = t % 100;
							 
							try {
								Thread.sleep(10);
							
								hbtHr.setText(String.format("%02d", hr));
								hbtMin.setText(String.format("%02d", mm));
								hbtSec.setText(String.format("%02d", ss));
								hbtMillsec.setText(String.format("%02d", ms));
								t++;
							} catch(InterruptedException e1) {
							}
						}
					}
				});
				display.start();
			}
			else if(s.equals("PAUSE")) {
				start.setEnabled(true);
				pause.setEnabled(false);
				reset.setEnabled(true);
				
				display = null;
			} else if(s.equals("RESET")) {
				start.setEnabled(true);
				pause.setEnabled(false);
				reset.setEnabled(false);
				
				hbtHr.setText("00");
				hbtMin.setText("00");
				hbtSec.setText("00");
				hbtMillsec.setText("00");
				t=0;
				
			}
		}
	}
}
