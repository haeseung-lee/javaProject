package com.justhabit.model.service;

import static com.justhabit.common.JDBC_EC2.close;
import static com.justhabit.common.JDBC_EC2.commit;
import static com.justhabit.common.JDBC_EC2.getConnection;
import static com.justhabit.common.JDBC_EC2.rollback;

//import static com.justhabit.common.JDBCTemplate.close;
//import static com.justhabit.common.JDBCTemplate.getConnection;



import java.sql.Connection;
import java.util.List;

import com.justhabit.model.dao.HaeinDAO;
import com.justhabit.model.dto.AllHabitDTO;

public class HaeinService {
	
	private HaeinDAO habitDAO = new HaeinDAO();

	public List<AllHabitDTO> selectAllHabitBy(int loggedUserID) {

		Connection con = getConnection();
		
		List<AllHabitDTO> allhabitList = habitDAO.selectAllHabitBy(con, loggedUserID);
		
		close(con);
		
		return allhabitList;
	}



	public int deleteHabitBy(int deleteHabitID) {
		Connection con = getConnection();
		
		int result = habitDAO.deleteHabitBy(con, deleteHabitID);
		System.out.println(result);
		if (result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		return result;
	}

}
