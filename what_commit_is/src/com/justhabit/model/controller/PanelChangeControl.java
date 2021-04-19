package com.justhabit.model.controller;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.justhabit.view.FirstFrame;
import com.justhabit.view.LoginPanel;

public class PanelChangeControl {

	public static JFrame mainFrame;
	
	   
    public static void changePanel(JPanel oldp, JPanel newp) { 
      mainFrame.getContentPane().remove(oldp);
      mainFrame.getContentPane().add(newp);
      mainFrame.validate();
      newp.repaint();
//      mainFrame.remove(oldp);
//      mainFrame.add(newp);
//      newp.revalidate();
//      oldp.setVisible(false);
//      newp.setVisible(true);
      
    }
    
    public static void changePanel(JFrame jf, JPanel oldp, JPanel newp) {
        
        jf.remove(oldp);
        jf.add(newp);
        newp.revalidate();
        newp.repaint();
      }
   
    public static void changeFrame(JFrame oldf, JFrame newf) {
      oldf.setVisible(false);
      newf.dispose();
      newf.setVisible(true);
    }
	
    public static void loadPan(JPanel panOld, JPanel panNew) { //Panel교체 메소드 
    	  Container pane = mainFrame.getContentPane(); 
    	  pane.remove(panOld); 
    	  pane.add(panNew); 
//    	  LoadPanListener.curPane = panNew; 
    	  mainFrame.setContentPane(pane); 
    }

	public static void changePanel(JFrame frame, LoginPanel signup, FirstFrame firstFrame) {
		frame.setVisible(false);
	} 
    
}
