package com.justhabit.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

//import org.apache.log4j.chainsaw.Main;

import com.justhabit.model.controller.PanelChangeControl;
import com.justhabit.model.controller.UserController;
import com.justhabit.model.dto.UserLevelDTO;

public class TodayWord extends JFrame {

  String text;
  String title;
  private Image back;
  private TodayWord todayWord = this;
  
  public static int image_number;
  
  private UserController userController = new UserController();
	
	public TodayWord() {
		
		this.todayWord = this;
		
		todayWord.setLayout(null);
		todayWord.setSize(900, 700);
		todayWord.setBackground(Color.lightGray);
		todayWord.setLocationRelativeTo(null);
		todayWord.setVisible(true);
		todayWord.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] words = new String[5];
		words[0] = "<오늘의 명언>\n\n하루의 시작은\n 습관부터 시작이다.\n\n-채도혁-";
		words[1] = "<오늘의 명언>\n\n들어왔네?\n 잘 생각했어\n\n-채도혁-";
		words[2] = "<오늘의 명언>\n\n내일 할 것도\n오늘 하자,\n그게 맘 편해\n\n-차진서-";
		words[3] = "<오늘의 명언>\n\nKing\n 너네 나 못 이겨\n\n-이해승-";
		words[4] = "<오늘의 명언>\n\n생각하지 말고\n JUST DO IT \n\n-김해인-";
		

		
		JTextPane textfield = new JTextPane();

		textfield.setText(words[(int)(Math.random()*5)]);
		
		SimpleAttributeSet aSet = new SimpleAttributeSet(); 
		StyleConstants.setFontFamily(aSet, "궁서");
		StyleConstants.setFontSize(aSet, 20);
		
		SimpleAttributeSet bSet = new SimpleAttributeSet();
		StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontFamily(bSet, "궁서");
		StyleConstants.setFontSize(bSet, 20);
		
		textfield.setEditable(false);
		textfield.setLocation(200,260);
		textfield.setSize(500,200);
		StyledDocument doc = textfield.getStyledDocument();
        doc.setCharacterAttributes(105, doc.getLength()-105, aSet, false);
        doc.setParagraphAttributes(0, 104, bSet, false);
        
        textfield.setOpaque(false);
        textfield.setBorder(BorderFactory.createEmptyBorder());
        textfield.setBackground(new Color(0,0,0,0));
		
		this.add(textfield);
		
		
		JButton first = new JButton("시작");
		first.setFont(new Font("THE외계인설명서",Font.BOLD,15));
		first.setSize(70,40);
		first.setLocation(410, 560);
		this.add(first);
		first.setOpaque(false);
		first.setContentAreaFilled(false);
		first.setBorderPainted(false);
		
		Image button2 = new ImageIcon("image/button7.png").getImage().getScaledInstance(70, 40, 0);
		JLabel buttonB1 = new JLabel(new ImageIcon(button2));
		buttonB1.setBounds(410, 560, 70,40);
		
		this.add(buttonB1);
		
		userLevelUpdate();

		Image level = new ImageIcon("image/오늘의명언배경.jpeg").getImage().getScaledInstance(900, 700, 0);
		
		JLabel levelImg = new JLabel(new ImageIcon(level));
		levelImg.setBounds(0,0,900,700);
		this.add(levelImg);
		
		
		first.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	PanelChangeControl.changeFrame(todayWord , new MainPage());
            }
         });

	}
	
	
	/**
	 * <pre>
	 *  유저 레벨에 따른 이미지 설정과 습관횟수와 성공횟수를 판단하여 레벨 update, commit
	 * </pre>
	 * @return 이미지 이름 리턴
	 */
	public void userLevelUpdate() {
		
		
	}
}	
	
	
