package com.justhabit.model.controller;

import java.util.List;

import com.justhabit.model.dto.AllHabitDTO;
import com.justhabit.model.service.HaeinService;
import com.justhabit.view.FirstFrame;

public class HaeinController {

	private HaeinService habitService = new HaeinService();
//	public List<AllHabitDTO>  selectAllHabitBy(int loggedUserID) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public List<AllHabitDTO> selectAllHabitBy(int loggedUserID) {
		
		return habitService.selectAllHabitBy(loggedUserID);
		
	}

	public void deleteHabitBy(int deleteHabitID) {

		int result = habitService.deleteHabitBy(deleteHabitID);
		if(result > 0) {
			System.out.println("삭제를 성공했습니다.");
		} else {
			System.out.println("삭제를 실패했습니다.");
		}
		
		
	}


	
}
