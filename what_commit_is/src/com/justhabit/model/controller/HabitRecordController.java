package com.justhabit.model.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.justhabit.model.dto.HabitInfoDTO;
import com.justhabit.model.dto.HabitMonthTotalDTO;
import com.justhabit.model.dto.HabitRecordDTO;
import com.justhabit.model.service.HabitRecordService;

public class HabitRecordController {
	
	private HabitRecordService habitService = new HabitRecordService();
	
	/**
	 * <pre>
	 * 습관정보 조회
	 * </pre>
	 * @param habitID
	 * @return
	 */
	public HabitInfoDTO selectHabitInfo(HabitInfoDTO registInfo) {
		
		HabitInfoDTO info = habitService.selectHabitInfo(registInfo);
		return info;
	}

	/**
	 * <pre>
	 *   습관기록 SELECT하여 오늘 기록된 습관이 있으면 1반환 없으면 0 반환
	 * </pre>
	 * @param record
	 * @return
	 */
	public int dateSelectController(HabitRecordDTO recordInfo) {
		
		int result = 0;
		if(!(habitService.selectDateinfo(recordInfo)==null)) {
			result = 1;
		}
		
		return result;
	}
	

	/**
	 * <pre>
	 * 횟수기록 INSERT
	 * </pre>
	 * @param checkRecord
	 */
	public void insertCheckController(HabitRecordDTO checkRecord) {
//		오늘날짜로 설정. 테스트시 주석처리
		Date todayDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		checkRecord.setDoDate(dateFormat.format(todayDate));
		
		
		int result = habitService.insertCheckService(checkRecord);
		
		if(result > 0) {
			System.out.println("신규저장성공");
		} else {
			System.out.println("신규저장실패");
		}
	}

	/**
	 * <pre>
	 * 시간기록 INSERT
	 * @param checkRecord
	 */
	public void insertTimerController(HabitRecordDTO timerRecord) {
//		오늘날짜로 설정. 테스트시 주석처리
		Date todayDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		timerRecord.setDoDate(dateFormat.format(todayDate));
		
		
		int result = habitService.insertTimerService(timerRecord);
		
		if(result > 0) {
			System.out.println("신규저장성공");
		} else {
			System.out.println("신규저장실패");
		}
	}

	/**
	 * <pre>
	 *   횟수기록 UPDATE
	 * </pre>
	 * @param checkRecord
	 */
	public void updateCheckController(HabitRecordDTO checkRecord) {
		
		int result = habitService.updateCheckRecordService(checkRecord);
		
		if(result > 0) {
			System.out.println("갱신성공!");
		} else {
			System.out.println("갱신실패!");
		}
	}

	/**
	 * <pre>
	 *   시간기록 UPDATE
	 * </pre>  
	 * @param timerRecord
	 */
	public void updateTimerController(HabitRecordDTO timerRecord) {
		
		int result = habitService.updateTimerRecordService(timerRecord);
		
		if(result > 0) {
			System.out.println("갱신성공!");
		} else {
			System.out.println("갱신실패!");
		}
	}

	public int dateTimerSelectController(HabitRecordDTO timerRecord) {
		int result = 0;
		if(!(habitService.selectTimerDateinfo(timerRecord)==null)) {
			result = 1;
		}
		
		return result;
	}

	/**
	 * <pre>
	 *   월 횟수 total 출력을 위한 정보조회
	 * </pre>
	 * @param totalRecord
	 * @return
	 */
	public HabitMonthTotalDTO monthTotalController(HabitMonthTotalDTO totalRecord) {
		
		HabitMonthTotalDTO monthTotal = habitService.selectMonthTotal(totalRecord);
		return monthTotal;
	}

	/**
	 * <pre>
	 *   월 Timer total 출력을 위한 정보조회
	 * </pre>
	 * @param totalRecord
	 * @return
	 */
	public HabitMonthTotalDTO monthTimerTotalController(HabitMonthTotalDTO totalRecord) {
		
		HabitMonthTotalDTO monthTotal = habitService.selectMonthTimerTotal(totalRecord);
		
		return monthTotal;
	}

	public Map<String,HabitRecordDTO> selectRecordGoal(HabitRecordDTO Record) {
		Map<String,HabitRecordDTO> recordAndGoalList = habitService.selectRecordGoal(Record);
		return recordAndGoalList;
	}


}
