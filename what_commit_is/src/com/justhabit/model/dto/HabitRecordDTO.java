package com.justhabit.model.dto;

import java.sql.Date;

public class HabitRecordDTO {

	private int userId;
	private int habitId;
	private String doDate;
	private int check;
	private double timer;
	private int habitGoal;
	private String recordType;
	
	public HabitRecordDTO() {
		super();
	}

	public HabitRecordDTO(int userId, int habitId, String doDate, int check, double timer, int habitGoal, String recordType) {
		super();
		this.userId = userId;
		this.habitId = habitId;
		this.doDate = doDate;
		this.check = check;
		this.timer = timer;
		this.habitGoal = habitGoal;
		this.recordType = recordType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHabitId() {
		return habitId;
	}

	public void setHabitId(int habitId) {
		this.habitId = habitId;
	}

	public String getDoDate() {
		return doDate;
	}

	public void setDoDate(String doDate) {
		this.doDate = doDate;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

	public double getTimer() {
		return timer;
	}

	public void setTimer(double timer) {
		this.timer = timer;
	}

	public int getHabitGoal() {
		return habitGoal;
	}

	public void setHabitGoal(int habitGoal) {
		this.habitGoal = habitGoal;
	}
	
	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	@Override
	public String toString() {
		return "HaeseungRecordDTO [userId=" + userId + ", habitId=" + habitId + ", doDate=" + doDate + ", check="
				+ check + ", timer=" + timer + ", habitGoal=" + habitGoal + ", recordType=" + recordType + "]";
	}

	
	
	
	
}
