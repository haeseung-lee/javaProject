package com.justhabit.model.service;

import static com.justhabit.common.JDBC_EC2.close;
import static com.justhabit.common.JDBC_EC2.commit;
import static com.justhabit.common.JDBC_EC2.getConnection;
import static com.justhabit.common.JDBC_EC2.rollback;

//import static com.justhabit.common.JDBCTemplate.close;
//import static com.justhabit.common.JDBCTemplate.commit;
//import static com.justhabit.common.JDBCTemplate.getConnection;
//import static com.justhabit.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.justhabit.model.dao.HabitRecordDAO;
import com.justhabit.model.dto.HabitInfoDTO;
import com.justhabit.model.dto.HabitMonthTotalDTO;
import com.justhabit.model.dto.HabitRecordDTO;

public class HabitRecordService {
	
	HabitRecordDAO habitDAO = new HabitRecordDAO();

	/**
	 * <pre>
	 * 습관정보 조회
	 * </pre>
	 * 
	 * @param 
	 * @return
	 */
	public HabitInfoDTO selectHabitInfo(HabitInfoDTO registInfo) {
		
		Connection con = getConnection();
		
		HabitInfoDTO info = habitDAO.selectHabit(con, registInfo);
		close(con);
		return info;
	}

	/**
	 * <pre>
	 * CHECK INSERT
	 * </pre>
	 * @param checkRecord
	 * @return
	 */
	public int insertCheckService(HabitRecordDTO checkRecord) {
		
		Connection con = getConnection();
		
		int result = habitDAO.insertCheck(con, checkRecord);
		
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		return result;
	}
	
	/**
	 * <pre>
	 * Timer INSERT
	 * </pre>
	 * @param checkRecord
	 * @return
	 */
	public int insertTimerService(HabitRecordDTO checkRecord) {
		Connection con = getConnection();
		
		int result = habitDAO.insertTimer(con, checkRecord);
		
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		return result;
	}
	

	/**
	 * <pre>
	 * SELECT date
	 * </pre>
	 * @param recordInfo
	 * @return
	 */
	public HabitRecordDTO selectDateinfo(HabitRecordDTO recordInfo) {
		
		Connection con = getConnection();
		
		HabitRecordDTO info= habitDAO.selectDate(con, recordInfo);
		
		close(con);
		return info;
	}

	/**
	 * <pre>
	 * CHECK UPDATE
	 * </pre>
	 * @param checkRecord
	 * @return
	 */
	public int updateCheckRecordService(HabitRecordDTO checkRecord) {
		
		Connection con = getConnection();
		
		int result = habitDAO.updateCheckRecord(con,checkRecord);
		
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		return result;
	}

	/**
	 * <pre>
	 * Timer Update
	 * </pre>
	 * @param timerRecord
	 * @return
	 */
	public int updateTimerRecordService(HabitRecordDTO timerRecord) {
		Connection con = getConnection();
		
		int result = habitDAO.updateTimerRecord(con, timerRecord);
		System.out.println(result);
		if(result > 0 ) {
			commit(con);
		}else {
			rollback(con);
		}
		return result;
	}

	public Object selectTimerDateinfo(HabitRecordDTO timerRecord) {
		Connection con = getConnection();
		
		HabitRecordDTO info= habitDAO.selectTimerDate(con, timerRecord);
		
		close(con);
		return info;
	}

	public  HabitMonthTotalDTO selectMonthTotal(HabitMonthTotalDTO totalRecord) {
		Connection con =getConnection();
		
		HabitMonthTotalDTO total = habitDAO.selectMonthTotal(con, totalRecord);
		return total;
	}

	public HabitMonthTotalDTO selectMonthTimerTotal(HabitMonthTotalDTO totalRecord) {
		Connection con =getConnection();
		
		HabitMonthTotalDTO total = habitDAO.selectMonthTimerTotal(con, totalRecord);
		return total;
	}

	public Map<String,HabitRecordDTO> selectRecordGoal(HabitRecordDTO recordInfo) {
		Connection con = getConnection();
		
		Map<String,HabitRecordDTO> selectRecordGoalList= null;
		if(recordInfo.getRecordType().equals("c")) {
			selectRecordGoalList = habitDAO.selectCheckRecordGoal(con, recordInfo);
		} else {
			selectRecordGoalList = habitDAO.selectTimerRecordGoal(con, recordInfo);
		}
		
		return selectRecordGoalList;
	}


}
