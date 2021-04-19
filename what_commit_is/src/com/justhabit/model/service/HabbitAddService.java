package com.justhabit.model.service;


import static com.justhabit.common.JDBC_EC2.commit;
import static com.justhabit.common.JDBC_EC2.getConnection;
import static com.justhabit.common.JDBC_EC2.rollback;

import java.sql.Connection;

import com.justhabit.model.dao.HabbitAddDAO;
import com.justhabit.model.dto.HabbitAddDTO;

public class HabbitAddService {
	
	private HabbitAddDAO habbitAddDAO = new HabbitAddDAO();


	public int insertAllHabbit(HabbitAddDTO habitAddDTO) {
		
		Connection con = getConnection();
		
		int result = habbitAddDAO.insertAllHabbit(con, habitAddDTO);
		
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return result;
	}
}