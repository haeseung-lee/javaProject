package com.justhabit.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.justhabit.model.controller.HabitRecordController;
import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.dto.HabitInfoDTO;
import com.justhabit.model.dto.HabitMonthTotalDTO;
import com.justhabit.model.dto.HabitRecordDTO;

public class TimeRecordView extends JFrame{

	/**
	 * 타이머 기록페이지
	 */
	
	private HabitRecordController habitInfoController = new HabitRecordController();
	private HabitRecordDTO timerRecord = new HabitRecordDTO(); //습관 기록전달DTO
	private HabitInfoDTO registInfo = new HabitInfoDTO(); //습관등록정보전달DTO
	private HabitMonthTotalDTO totalRecord = new HabitMonthTotalDTO(); //습관의 월 total 기록 조회결과
	private Map<String,HabitRecordDTO> recordAndGoalList = null; //일별 기록전달DTO
	private Date todayDate= new Date(); // 오늘 날짜
	private String today = ""; //날짜 문자열로 변환
	private int totalDate =0; //습관실시 일수
	private double totalTimer=0;//기록된 습관 총 시간
	private String thisMonth = "" ;//이번달 "00월"
	private int accomon = 0; //습관성취도 수 카운트
	private JFrame mf = this; 
	
	
	public TimeRecordView() {
		System.out.println(timerPanel.t);
		this.setLayout(null);
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top = new TopPanel();
		this.add(top);
		
		JPanel center = new JPanel();
		center.setBounds(0, 100, 900, 530);
		center.setLayout(null);
		center.setBackground(new Color(255,255,255));
		
		
		//등록된 습관정보 불러오기
		registInfo.setHabitID(MainPage.userhabitid); 
		registInfo = habitInfoController.selectHabitInfo(registInfo);
		//습관상단바
		JPanel habitTop = new JPanel();
		habitTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		habitTop.setBounds(43, 20, 800, 50);
		habitTop.setBackground(new Color(249,228,183));
		
		//습관상단바 - 습관명
		JPanel habitName = new JPanel();
		habitName.setBackground(new Color(249,228,183));
		JLabel nameLabel = new JLabel(registInfo.getHabitName());
		nameLabel.setHorizontalAlignment(JLabel.CENTER);

		//폰트설정
		nameLabel.setFont(new Font("THE외계인설명서",Font.BOLD,25));
		habitName.add(nameLabel);
		
		habitTop.add(habitName);
		
		//습관목표출력
		JLabel habitGoal = new JLabel("목표 : "+registInfo.getHabitGoal()+"시간 /");
		habitGoal.setFont(new Font("THE외계인설명서",Font.PLAIN,20));
		habitTop.add(habitGoal);
		
		//습관 상단바 - 타이머
		JPanel timer=  new timerPanel();
		timer.setBounds(270, 0,400,50);
		timer.setBackground(new Color(249,228,183));
		habitTop.add(timer);
		
		//달력
		JPanel calendarPanel = new JPanel();
		calendarPanel.setBounds(43, 110, 350, 340);
		calendarPanel.setLayout(null);
		calendarPanel.setBackground(new Color(255,255,255));
		
		//달력 날짜 입력
		ArrayList<Integer>calArr = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		for(int i = 0; i <lastDay; i++) {
			calArr.add(i+1);
		}
		
		//월표시
		thisMonth = "";
		if(month < 9) {
			thisMonth = "0"+(month+1);
		} else {
			thisMonth = month+1+"";
		}
		JLabel monthName = new JLabel(thisMonth+"월");
		monthName.setBounds(160,15,80,50);
		monthName.setFont(new Font("THE외계인설명서",Font.BOLD,20));
		monthName.setForeground(new Color(255,255,204));
		
		calendarPanel.add(monthName);
		
		//달력표시
		JPanel calendar = new JPanel();
		calendar.setBounds(0, 80, 350, 250);
		GridLayout gridLayout = new GridLayout(calArr.size()/7+1,7,2,2);
		calendar.setLayout(gridLayout);
		
		Date todayDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		today = dateFormat.format(todayDate);
		
		JButton[] dayButton = new JButton[calArr.size()]; 
		for(int i = 0; i < calArr.size();i++) {
			
			if(i<9) {
				
				dayButton[i] = new JButton("0"+calArr.get(i));			
				dayButton[i].setHorizontalAlignment(JLabel.CENTER);
				dayButton[i].setVerticalAlignment(JLabel.CENTER);
				calendar.add(dayButton[i]);
			} else {
				dayButton[i] = new JButton(calArr.get(i)+"");			
				dayButton[i].setHorizontalAlignment(JLabel.CENTER);
				dayButton[i].setVerticalAlignment(JLabel.CENTER);
				calendar.add(dayButton[i]);
			}
		}
		
		//달성 여부에 따라 신호등 만들기
		timerRecord.setHabitId(MainPage.userhabitid);
		timerRecord.setRecordType(registInfo.getHabitType());
		recordAndGoalList = habitInfoController.selectRecordGoal(timerRecord);
		SimpleDateFormat yearMonth = new SimpleDateFormat("yy/MM");
		String timerYearMonth = yearMonth.format(todayDate);
		String searchDate =  "";
		for(int i = 0; i < calArr.size(); i++) {

			searchDate = timerYearMonth+"/"+dayButton[i].getText();
			if(recordAndGoalList.get(searchDate)!=null) {
				int goal = recordAndGoalList.get(searchDate).getHabitGoal();
				double record = recordAndGoalList.get(searchDate).getTimer();
				if(goal <= record) {
					dayButton[i].setBackground(new Color(102,204,153));
					dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
					accomon++;
				} else {
					dayButton[i].setBackground(new Color(255,204,51));
					dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
				}
			} else {
				dayButton[i].setBackground(new Color(255,255,255));
				dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
			}
		}
		calendarPanel.add(calendar);
		
		//클릭시 그날 기록 출력하기  
		for(int i = 0; i < calArr.size(); i++) {
			searchDate = timerYearMonth+"/"+dayButton[i].getText();
			if(recordAndGoalList.get(searchDate)==null) {
				String dialogDate = searchDate;
				dayButton[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(mf, dialogDate +"\n\n"+ 0 + " / " + registInfo.getHabitGoal());
					}
				});
			} else {
				String dialogDate = searchDate;
				dayButton[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(mf, dialogDate +"\n\n"+ recordAndGoalList.get(dialogDate).getTimer() + " / " + registInfo.getHabitGoal());
						
					}
				});
			}
		}
		

		//문구 표시
		JPanel habitInfo = new JPanel();
		habitInfo.setLayout(null);
		habitInfo.setBounds(470, 95, 338, 350);
		JTextArea info = new JTextArea();
		info.setBounds(0,0,370,320);
		info.setFont(new Font("THE외계인설명서",Font.PLAIN,20));
		info.setEditable(false);
		info.setOpaque(false);
		
		//출력할정보를 검색
		totalRecord.setHabitId(registInfo.getHabitID());
		totalRecord.setTodayMonth(thisMonth);
		totalRecord = habitInfoController.monthTimerTotalController(totalRecord);
		//습관 실시한 일수
		totalDate = totalRecord.getDateCount();
		//습관 총 횟수
		totalTimer = totalRecord.getRecordSum();
		info.setText("\n \n \n                           이번 달 기록 \n \n        달성한 일수   :   " + accomon + "일\n \n        실시한 일수   :   " + totalDate + "일 \n \n        실시한 시간   :   " + totalTimer + "시간");
		habitInfo.add(info);
		
		//저장 버튼 
		
		Image saveImg = new ImageIcon("image/저장버튼.png").getImage().getScaledInstance(60, 50, 0);
		JLabel saveLabel = new JLabel(new ImageIcon(saveImg));
		saveLabel.setBounds(783,139,80,27);
		habitTop.add(saveLabel);
		
		saveLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				SimpleDateFormat todayDateFormat = new SimpleDateFormat("yy/MM/dd");
				accomon = 0;
				
				double div = 60*60*100;
				double hbtTimer = timerPanel.t / div;
				double test = timerPanel.t % div;
				if(hbtTimer == 0) {
					JOptionPane.showMessageDialog(mf, "등록할 기록 없음");
				} else {
					
					//습관기록을위한 기본정보(유저ID,습관ID,오늘날짜)
					timerRecord.setUserId(registInfo.getUserID()); //유저아이디
					timerRecord.setHabitId(registInfo.getHabitID()); //습관아이디
					timerRecord.setTimer(hbtTimer); //기록시간
					today = todayDateFormat.format(todayDate);
					timerRecord.setDoDate(today);  // 오늘날짜
					
					//날짜에 등록된 기록이 없으면 insert, 있으면 update
					int result = habitInfoController.dateTimerSelectController(timerRecord);
					if(result==0){
						habitInfoController.insertTimerController(timerRecord);
						JOptionPane.showMessageDialog(mf, "신규 저장 성공");
					} else {
						habitInfoController.updateTimerController(timerRecord);
						JOptionPane.showMessageDialog(mf, "기록 갱신 성공");
					}
					//total 출력문구 바꾸기
					totalRecord.setHabitId(registInfo.getHabitID());
					totalRecord.setTodayMonth(thisMonth);
					totalRecord = habitInfoController.monthTimerTotalController(totalRecord);
					totalDate = totalRecord.getDateCount();
					totalTimer = totalRecord.getRecordSum();
					
					timerRecord.setHabitId(MainPage.userhabitid);
					timerRecord.setRecordType(registInfo.getHabitType());
					
					//날짜 클릭시 조회되는 값 바꾸기
					timerRecord.setHabitId(MainPage.userhabitid);
					timerRecord.setRecordType(registInfo.getHabitType());
					recordAndGoalList = habitInfoController.selectRecordGoal(timerRecord);
					SimpleDateFormat yearMonth = new SimpleDateFormat("yy/MM");
					String timerYearMonth = yearMonth.format(todayDate);
					String searchDate =  "";
					for(int i = 0; i < calArr.size(); i++) {
						
						searchDate = timerYearMonth+"/"+dayButton[i].getText();
						if(recordAndGoalList.get(searchDate)!=null) {
							int goal = recordAndGoalList.get(searchDate).getHabitGoal();
							double record = recordAndGoalList.get(searchDate).getTimer();
							if(goal <= record) {
								dayButton[i].setBackground(new Color(102,204,153));
								accomon++;
							} else if(record > 0 ){
								dayButton[i].setBackground(new Color(255,204,51));
							} else {
								dayButton[i].setBackground(new Color(255,255,255));
							}
						}
					}
				}
				info.setText("\n \n \n                           이번 달 기록 \n \n        달성한 일수   :   " + accomon + "일\n \n        실시한 일수   :   " + totalDate + "일 \n \n        실시한 시간   :   " + totalTimer + "시간");
			}
		});
		
		
		//하단패널
		JPanel botPan = new JPanel();
		botPan.setLayout(new GridLayout(1,4));
		botPan.setSize(900, 50);
		botPan.setLocation(0, 615);
		botPan.setBackground(new Color(255,255,255));
		this.add(botPan);
		// 메뉴목록
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
	            PanelChangeControl.changeFrame(mf, new MainPage());
	        }
	    });
		menuButton[1].addActionListener(new ActionListener() {
	          
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            PanelChangeControl.changeFrame(mf, new HabbitAdd());
	        }
	    });
		
		menuButton[2].addActionListener(new ActionListener() {
	          
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            new DeleteHabit(mf);
	          }
	       });
		menuButton[3].addActionListener(new ActionListener() {
	          
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            PanelChangeControl.changeFrame(mf, new MyPage());
	        }
	    });
		center.add(habitInfo);
		center.add(calendarPanel);
		center.add(habitTop);
	
		//달력배경
		Image calendarImg = new ImageIcon("image/달력.PNG").getImage().getScaledInstance(470, 450, 0);
		JLabel CalendarBackground = new JLabel(new ImageIcon(calendarImg));
		CalendarBackground.setBounds(0, 0, 350, 340);
		
		//기록배경
		Image monthTotal = new ImageIcon("image/포스트잇.png").getImage().getScaledInstance(380, 350, 0);
		JLabel monthTotalBackground = new JLabel(new ImageIcon(monthTotal));
		monthTotalBackground.setBounds(0,0,338,350);
		habitInfo.add(monthTotalBackground);
		calendarPanel.add(CalendarBackground);
		
		this.add(center);
		this.setResizable(false);
	}
}
