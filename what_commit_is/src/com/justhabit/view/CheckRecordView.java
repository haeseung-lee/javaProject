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

public class CheckRecordView extends JFrame{
	
	/**
	 * 체크박스 기록페이지
	 */
	
	private HabitRecordController habitInfoController = new HabitRecordController();
	private HabitRecordDTO checkRecord = new HabitRecordDTO(); //습관 기록전달DTO
	private HabitInfoDTO registInfo = new HabitInfoDTO(); //습관등록정보전달DTO
	private HabitMonthTotalDTO totalRecord = new HabitMonthTotalDTO(); //습관의 월 total 기록 조회결과
	private Map<String,HabitRecordDTO> recordAndGoalList = null; //습관의 날짜별 기록 조회결과 
	private int checkCount = 0; // 횟수 카운트용
	private Date todayDate= new Date(); // 오늘 날짜
	private String today = ""; //날짜 문자열로 변환
	private int totalDate =0; //습관실시 일수
	private int totalCheck=0;//기록된 습관 총 수 
	private String thisMonth = "" ;//이번달 "00월"
	private int accomon = 0; //목표달성일 수 카운트
	private JFrame mf = this;
	
	public CheckRecordView() {
		
		this.setLayout(null);
		
		this.add(new TopPanel());
		JPanel center = new JPanel();
		center.setBounds(0, 100, 900, 530);
		center.setLayout(null);
		center.setBackground(new Color(255,255,255));
		
		//등록된 습관정보 불러오기
		registInfo.setHabitID(MainPage.userhabitid);
		registInfo = habitInfoController.selectHabitInfo(registInfo);
		
		//습관상단바
		JPanel habitTop = new JPanel();
		habitTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		habitTop.setBounds(43, 35, 800, 50);
		habitTop.setBackground(new Color(249,228,183));
		
		//습관상단바 - 습관명
		JPanel namePanel = new JPanel();
		namePanel.setBounds(0,0,200,50);
		namePanel.setBackground(new Color(249,228,183));
		JLabel nameLabel = new JLabel(registInfo.getHabitName());
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//폰트설정
		nameLabel.setFont(new Font("THE외계인설명서",Font.BOLD,30));
		namePanel.add(nameLabel);
		
		habitTop.add(namePanel);
		
		//습관 상단바 - 횟수
		JPanel habitCheck =  new JPanel();
		habitCheck.setLayout(new FlowLayout(FlowLayout.CENTER));
		habitCheck.setBackground(new Color(249,228,183));
		
		//횟수표시 및 증가버튼
		JLabel habitCount = new JLabel("      목표 : "+ registInfo.getHabitGoal() + "회 / 현재 : 0회      ");
		habitCount.setFont(new Font("THE외계인설명서", Font.PLAIN, 20));
		habitCheck.add(habitCount);

		Image plusImg = new ImageIcon("image/더하기사진.png").getImage().getScaledInstance(35, 35, 0);
		JLabel plusLabel = new JLabel(new ImageIcon(plusImg));
		habitCheck.add(plusLabel);
		plusLabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkCount == registInfo.getHabitGoal()) {
					JOptionPane.showMessageDialog(mf, "최대치입니다.");
				} else {
					checkCount++;
					habitCount.setText("      목표 : "+ registInfo.getHabitGoal() + "회 / 현재 : "+checkCount + "회      ");
				}
			}
		});

		Image minusImg = new ImageIcon("image/빼기사진.png").getImage().getScaledInstance(35, 35, 0);
		JLabel minusLabel = new JLabel(new ImageIcon(minusImg));
		habitCheck.add(minusLabel);
		minusLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(checkCount == 0) {
					JOptionPane.showMessageDialog(mf, "0보다 작을 수 없습니다.");
				} else {
					checkCount--;
					habitCount.setText("      목표 : "+ registInfo.getHabitGoal() + "회 / 현재 : "+checkCount + "회      ");
				}
			}
		});
		
		habitTop.add(habitCheck);
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
		if(month < 9) {
			thisMonth = "0"+(month+1);
		} else {
			thisMonth = month+1+"";
		}
		JLabel monthName = new JLabel(thisMonth+"월");
		monthName.setBounds(160,17,80,50);
		monthName.setFont(new Font("THE외계인설명서",Font.BOLD,30));
		monthName.setForeground(new Color(255,255,204));
		
		calendarPanel.add(monthName);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		
		String day = dateFormat.format(todayDate);
		//달력표시
		JPanel calendar = new JPanel();
		calendar.setBounds(0, 83, 350, 250);
		GridLayout gridLayout = new GridLayout(calArr.size()/7+1,7,2,2);
		calendar.setLayout(gridLayout);
		
		JButton[] dayButton = new JButton[calArr.size()]; 
		for(int i = 0; i < calArr.size();i++) {
			String CalenderDate = "";
			if(i<9) {
				CalenderDate ="0"+calArr.get(i);
				dayButton[i] = new JButton(CalenderDate);			
				dayButton[i].setHorizontalAlignment(JLabel.CENTER);
				dayButton[i].setVerticalAlignment(JLabel.CENTER);
				dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
				calendar.add(dayButton[i]);
			} else {
				CalenderDate = calArr.get(i)+"";
				dayButton[i] = new JButton(CalenderDate);			
				dayButton[i].setHorizontalAlignment(JLabel.CENTER);
				dayButton[i].setVerticalAlignment(JLabel.CENTER);
				dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
				calendar.add(dayButton[i]);
			}
		}
		
		//달성 여부에 따라 신호등 만들기
		checkRecord.setHabitId(MainPage.userhabitid);
		checkRecord.setRecordType(registInfo.getHabitType());
		recordAndGoalList = habitInfoController.selectRecordGoal(checkRecord);
		SimpleDateFormat yearMonth = new SimpleDateFormat("yy/MM");
		String checkYearMonth = yearMonth.format(todayDate);
		String searchDate =  "";
		for(int i = 0; i < calArr.size(); i++) {
			
			searchDate = checkYearMonth+"/"+dayButton[i].getText();
			if(recordAndGoalList.get(searchDate)!=null) {
				int goal = recordAndGoalList.get(searchDate).getHabitGoal();
				int record = recordAndGoalList.get(searchDate).getCheck();
				if(goal == record) {
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
				
		//클릭시 그날 기록 출력하기  
		for(int i = 0; i < calArr.size(); i++) {
			searchDate = checkYearMonth+"/"+dayButton[i].getText();
			if(recordAndGoalList.get(searchDate)==null) {
				String dialogDate = searchDate;
				dayButton[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(mf, dialogDate + "\n\n"+ 0 + " / " + registInfo.getHabitGoal());
					}
				});
			} else {
				String dialogDate = searchDate;
				dayButton[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(mf, dialogDate +"\n\n"+ recordAndGoalList.get(dialogDate).getCheck() 
								                                     + " / " + registInfo.getHabitGoal());
					}
				});
			}
		}
		
		calendarPanel.add(calendar);
		
		//기존 등록된 습관이 있으면 횟수 출력하기
		SimpleDateFormat todayRecord = new SimpleDateFormat("yy/MM/dd");
		String existingRecordDaty = todayRecord.format(todayDate);
		if(recordAndGoalList.get(existingRecordDaty)!=null) {
			habitCount.setText("      목표 : "+ registInfo.getHabitGoal() + "회 / 현재 : "
		                    +recordAndGoalList.get(existingRecordDaty).getCheck() + "회      ");
			checkCount = recordAndGoalList.get(existingRecordDaty).getCheck();
		}
		
		//문구 표시
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(470, 95, 338, 350);
		JTextArea infoText = new JTextArea();
		
		infoText.setBounds(0,0,370,320);
		infoText.setFont(new Font("THE외계인설명서",Font.PLAIN,20));
		infoText.setEditable(false);
		infoText.setOpaque(false);

		//출력할 정보를 검색
		totalRecord.setHabitId(registInfo.getHabitID());
		totalRecord.setTodayMonth(thisMonth);
		totalRecord = habitInfoController.monthTotalController(totalRecord);
		//습관 실시한 일수
		totalDate = totalRecord.getDateCount();
		//습관 총 횟수
		totalCheck = (int)totalRecord.getRecordSum();
		infoText.setText("\n \n \n                           이번 달 기록 \n \n \n        달성한 일수   :   " + accomon + "일\n \n        실시한 일수   :   " + totalDate + "일 \n \n        실시한 횟수   :   " + totalCheck + "회");
		infoPanel.add(infoText);
		
		//습관기록저장
		Image saveImg = new ImageIcon("image/저장버튼.png").getImage().getScaledInstance(60, 50, 0);
		JLabel saveLabel = new JLabel(new ImageIcon(saveImg));
		saveLabel.setBounds(840,128,60,45);
		habitTop.add(saveLabel);
		saveLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleDateFormat todayDateFormat = new SimpleDateFormat("yy/MM/dd");
				accomon = 0;
				
				if(checkCount == 0) {
					JOptionPane.showMessageDialog(mf, "등록할 기록 없음");
				} else {
					
					//습관기록을위한 기본정보(유저ID,습관ID,오늘날짜)
					
					checkRecord.setUserId(registInfo.getUserID());  //유저아이디
					checkRecord.setHabitId(registInfo.getHabitID()); // 습관아이디
					checkRecord.setCheck(checkCount); // 체크횟수
					today = todayDateFormat.format(todayDate);
					checkRecord.setDoDate(today);   // 오늘날짜
					
					//날짜에 등록된 기록이 없으면 insert, 있으면 update	
					int result = habitInfoController.dateSelectController(checkRecord);
					if(result==0){
						habitInfoController.insertCheckController(checkRecord);
						JOptionPane.showMessageDialog(mf, "신규 저장 성공");
					} else {
						habitInfoController.updateCheckController(checkRecord);
						JOptionPane.showMessageDialog(mf, "기록 갱신 성공");
					}
					//total 출력문구 바꾸기
					totalRecord.setHabitId(registInfo.getHabitID());
					totalRecord.setTodayMonth(thisMonth);
					totalRecord = habitInfoController.monthTotalController(totalRecord);
					totalDate = totalRecord.getDateCount();
					totalCheck = (int)totalRecord.getRecordSum();
					
					//날짜 클릭시 조회되는 값 바꾸기
					checkRecord.setHabitId(MainPage.userhabitid);
					checkRecord.setRecordType(registInfo.getHabitType());
					recordAndGoalList = habitInfoController.selectRecordGoal(checkRecord);
					SimpleDateFormat yearMonth = new SimpleDateFormat("yy/MM");
					String checkYearMonth = yearMonth.format(todayDate);
					String searchDate =  "";
					
					for(int i = 0; i < calArr.size(); i++) {
						
						searchDate = checkYearMonth+"/"+dayButton[i].getText();
						if(recordAndGoalList.get(searchDate)!=null) {
							int goal = recordAndGoalList.get(searchDate).getHabitGoal();
							int record = recordAndGoalList.get(searchDate).getCheck();
							if(goal == record) {
								dayButton[i].setBackground(new Color(102,204,153));
								dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
								accomon++;
							} else if(record > 0 ){
								dayButton[i].setBackground(new Color(255,204,51));
								dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
							} else {
								dayButton[i].setBackground(new Color(255,255,255));
								dayButton[i].setFont(new Font("THE외계인설명서", Font.PLAIN, 15));
							}
						}
					}
					//상단에 출력되는 값 변경
					habitCount.setText("      목표 : "+ registInfo.getHabitGoal() + "회 / 현재 : "+recordAndGoalList.get(existingRecordDaty).getCheck() + "회      ");
					checkCount = recordAndGoalList.get(existingRecordDaty).getCheck();
					infoText.setText("\n \n \n                           이번 달 기록 \n \n \n        달성한 일수   :   " + accomon + "일\n \n        실시한 일수   :   " + totalDate + "일 \n \n        실시한 횟수   :   " + totalCheck + "회");
				}
			}
		});		
		
		//하단패널
		JPanel botPan = new JPanel();
		botPan.setLayout(new GridLayout(1,4));
		botPan.setSize(900, 50);
		botPan.setLocation(0, 615);
		botPan.setBackground(new Color(255,255,255));
		
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
		//메뉴버튼추가
		
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
		
		center.add(infoPanel);
		center.add(calendarPanel);
		center.add(habitTop);
		
		//달력배경
		Image calendarImg = new ImageIcon("image/달력.PNG").getImage().getScaledInstance(470, 430, 0);
		JLabel CalendarBackground = new JLabel(new ImageIcon(calendarImg));
		CalendarBackground.setBounds(0, 0, 350, 340);
		
		//기록배경
		Image monthTotal = new ImageIcon("image/포스트잇.png").getImage().getScaledInstance(380, 350, 0);
		JLabel monthTotalBackground = new JLabel(new ImageIcon(monthTotal));
		monthTotalBackground.setBounds(0,0,338,350);
		
		infoPanel.add(monthTotalBackground);
		calendarPanel.add(CalendarBackground);
		
		this.setResizable(false);
		this.add(center);
		this.setSize(900, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
