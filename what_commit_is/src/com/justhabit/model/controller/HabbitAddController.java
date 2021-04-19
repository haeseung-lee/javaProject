package com.justhabit.model.controller;

import java.util.ArrayList;
import java.util.List;

import com.justhabit.model.dto.HabbitAddDTO;
import com.justhabit.model.service.HabbitAddService;
import com.justhabit.view.FirstFrame;
import com.justhabit.view.HabbitAdd;



public class HabbitAddController {
	
	private HabbitAddService habbitAddService = new HabbitAddService(); // 캡슐화로 private을 사용

	public int insertAllHabbit(HabbitAddDTO habitAddDTO) {
		
		HabbitAddDTO habbitadd = new HabbitAddDTO();
		
		habbitadd.setUserID(FirstFrame.loggedUserID);
		habbitadd.setHabitID(habbitadd.getHabitID());
		habbitadd.setHabitName(habbitadd.getHabitName());
		habbitadd.setHabitType(habbitadd.getHabitType());
		habbitadd.setHabitDays(habbitadd.getHabitDays());
		habbitadd.setHabitGoal(habbitadd.getHabitGoal());
		
		
		int result = habbitAddService.insertAllHabbit(habitAddDTO);
		
		if(result > 0) {
			System.out.println("습관저장성공");
		} else {
			System.out.println("습관저장실패");
		}
		
		return 0;
	
		
	}
}
