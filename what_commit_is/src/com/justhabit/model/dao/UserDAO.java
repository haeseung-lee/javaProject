package com.justhabit.model.dao;

import static com.justhabit.common.JDBC_EC2.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Properties;

import com.justhabit.model.dto.UserDTO;
import com.justhabit.model.dto.UserLevelDTO;
import com.justhabit.view.FirstFrame;

public class UserDAO {

private Properties prop = new Properties();
	
	public UserDAO() {
		try {
			prop.loadFromXML(new FileInputStream("mapper/habit-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean selectUser(Connection con, String loginId, String loginPwd) {

		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("login");
		
		int result = 0;
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, loginId);
			psmt.setString(2, loginPwd);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
				FirstFrame.loggedUserID = result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return result == 0 ? false: true;
	}

	public boolean checkID(Connection con, String signup_name) {

		PreparedStatement psmt = null;
		ResultSet rset = null;
		
		boolean available = true;
		
		String query = prop.getProperty("checkUsername");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, signup_name);
			
			rset = psmt.executeQuery();
			
			if(rset.next())
				available = false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return available;
	}

	public boolean registerUser(Connection con, UserDTO registerUser) {

		PreparedStatement psmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("registerUser");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, registerUser.getUserName());
			psmt.setString(2, registerUser.getUserPwd());
			psmt.setString(3, registerUser.getUserEmail());
			psmt.setInt(4, registerUser.getUserPin());
			
			result = psmt.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		
		
		return result == 0 ? false : true;
	}

	public UserDTO myUser(Connection con, int loggedUserID) {

		PreparedStatement psmt = null;
		ResultSet rset = null;
		UserDTO user = null;
		
		String query = prop.getProperty("myUser");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, loggedUserID);
			
			
			rset = psmt.executeQuery();
			if(rset.next()) {
				user = new UserDTO();
				user.setUserId(loggedUserID);
				user.setUserName(rset.getString("user_name"));
				user.setUserPwd(rset.getString("user_pwd"));
				user.setUserEmail(rset.getString("user_email"));
				user.setUserPin(rset.getInt("user_pin"));
				user.setUserLevel(rset.getInt("level_num"));
				user.setUserImage(rset.getString("level_image"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return user;
	}

	public boolean deleteUser(Connection con, int userId) {

		PreparedStatement psmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("deleteUser");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, userId);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		
		return result == 0? false : true;
	}

	public boolean updateUser(Connection con, UserDTO myUser) {
		
		PreparedStatement psmt = null;
		
		int result = 0;
		
		String query = prop.getProperty("updateUser");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, myUser.getUserName());
			psmt.setString(2, myUser.getUserPwd());
			psmt.setString(3, myUser.getUserEmail());
			psmt.setInt(4, myUser.getUserId());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		
		
		return result == 0 ? false: true;
	}

	public UserLevelDTO userLevel(Connection con, int loggedUserID) {
		
		PreparedStatement psmt = null;
		ResultSet rset = null;
		UserLevelDTO user = null;
		UserLevelDTO ghostUser = null;
		
		String query = prop.getProperty("userLevel");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, loggedUserID);
			psmt.setInt(2, loggedUserID);
			psmt.setInt(3, loggedUserID);
			psmt.setInt(4, loggedUserID);
			
			rset = psmt.executeQuery();
			
			if(rset.next()) {
				user = new UserLevelDTO();
				user.setUserId(rset.getInt("user_id"));
				user.setUserLevel(rset.getInt("level_num"));
				user.setUserImage(rset.getString("level_image"));
				user.setNumOfHabits(rset.getInt("habits"));
				user.setSuccessOfCheck(rset.getInt("success_check"));
				user.setSuccessOfTimer(rset.getInt("success_timer"));
			}
			
		} catch(SQLIntegrityConstraintViolationException e)	{
			
			ghostUser = new UserLevelDTO(loggedUserID, 1, "1렙계란.PNG", 0, 0, 0);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(psmt);
		}
		
		return user == null ? ghostUser : user;
	}

	public boolean userLvlUpdate(Connection con, UserLevelDTO user) {
		
		PreparedStatement psmt = null;
		int result = 0;
		
		String query = prop.getProperty("userLvlUpdate");
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, user.getUserLevel());
			psmt.setInt(2, user.getUserId());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(psmt);
		}
		
		return result == 0 ? false : true;
	}
}
