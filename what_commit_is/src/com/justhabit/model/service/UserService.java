package com.justhabit.model.service;

import static com.justhabit.common.JDBC_EC2.close;
import static com.justhabit.common.JDBC_EC2.commit;
import static com.justhabit.common.JDBC_EC2.getConnection;
import static com.justhabit.common.JDBC_EC2.rollback;

import java.sql.Connection;

import com.justhabit.model.dao.UserDAO;
import com.justhabit.model.dto.UserDTO;
import com.justhabit.model.dto.UserLevelDTO;

public class UserService {
	
	private UserDAO userDAO = new UserDAO();

	public boolean login(String loginId, String loginPwd) {

		Connection con = getConnection();
		
		boolean IsloggedIn = userDAO.selectUser(con, loginId, loginPwd );
		
		close(con);
		
		return IsloggedIn;
	}

	public boolean userIdCheck(String signup_name) {

		Connection con = getConnection();
		
		boolean isAvailableID = userDAO.checkID(con, signup_name);
		
		close(con);
		
		return isAvailableID;
		
		
	}

	public boolean register(UserDTO registerUser) {
		
		Connection con = getConnection();
		
		boolean isRegistered = userDAO.registerUser(con, registerUser);
		
		if(isRegistered)
			commit(con);
		else 
			rollback(con);
		
		close(con);
		
		return isRegistered;
	}

	public UserDTO userInfo(int loggedUserID) {

		Connection con = getConnection();
		
		UserDTO user = userDAO.myUser(con, loggedUserID);
		
		close(con);
		
		
		return user;
	}

	public boolean deleteUser(int userId) {
		
		Connection con = getConnection();
		
		boolean isDeleted = userDAO.deleteUser(con, userId);
		
		if(isDeleted)
			commit(con);
		else 
			rollback(con);
		
		close(con);
		
		return isDeleted;
	}

	public String userUpdate(UserDTO myUser) {

		Connection con = getConnection();
		
		boolean isUpdated = userDAO.updateUser(con, myUser);
		
		if(isUpdated)
			commit(con);
		else 
			rollback(con);
		
		close(con);
		
		return isUpdated ? "성공" : "업데이트 실패하였습니다";
	}

	public UserLevelDTO userLevel(int loggedUserID) {
		
		Connection con = getConnection();
		
		UserLevelDTO user = userDAO.userLevel(con, loggedUserID);
				
		close(con);
		
		return user;
	}

	public void userLvlUpdate(UserLevelDTO user) {

		Connection con = getConnection();
		
		boolean isUpdated = userDAO.userLvlUpdate(con, user);
		
		if(isUpdated)
			commit(con);
		else 
			rollback(con);
		
		close(con);
	}

}
