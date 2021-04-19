package com.justhabit.model.dao;

import static com.justhabit.common.JDBC_EC2.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.justhabit.model.dto.HabbitAddDTO;

public class HabbitAddDAO {
	
	private Properties prop = new Properties();
	
	public HabbitAddDAO() {
		
		try {
			prop.loadFromXML(new FileInputStream("mapper/habit-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertAllHabbit(Connection con, HabbitAddDTO habitAddDTO) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAllHabbit");
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, habitAddDTO.getUserID());
			pstmt.setString(2, habitAddDTO.getHabitName());
			pstmt.setString(3, habitAddDTO.getHabitType());
			pstmt.setString(4, habitAddDTO.getHabitDays());
			pstmt.setInt(5, habitAddDTO.getHabitGoal());
		
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	

}