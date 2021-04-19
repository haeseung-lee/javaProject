package com.justhabit.model.dao;

import static com.justhabit.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.justhabit.model.dto.AllHabitDTO;

public class HaeinDAO {
	
	private Properties prop = new Properties();
	
	public HaeinDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/habit-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public List<AllHabitDTO> selectAllHabitBy(Connection con, int loggedUserID) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<AllHabitDTO> allhabitList = null;
		
		String query = prop.getProperty("selectAllHabit");
		
		
			try {
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, loggedUserID);
				
				rset = pstmt.executeQuery();
				
				allhabitList = new ArrayList<>();
				
				while(rset.next()) {
					
					AllHabitDTO habit = new AllHabitDTO();
					habit.setHabitID(rset.getInt("habit_id"));
					habit.setHabitName(rset.getString("habit_name"));
					habit.setHabitType(rset.getString("habit_type"));
					habit.setHabitDays(rset.getString("habit_days"));
					habit.setHabitGoal(rset.getInt("habit_goal"));
					allhabitList.add(habit);
					
				}
				
						
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			
			
		
		
		return allhabitList;
	}


	public int deleteHabitBy(Connection con, int deleteHabitID) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteHabit");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, deleteHabitID);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		
		
		
		
		return 0;
	}

}
