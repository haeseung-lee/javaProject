package com.justhabit.model.dto;

public class HabitInfoDTO {

	private int habitID;
	private String habitName;
	private String habitType;
	private int habitGoal;
	private String habitDays;
	private int UserID;
	
	public HabitInfoDTO() {
		super();
	}

	public HabitInfoDTO(int habitID, String habitName, String habitType, int habitGoal, String habitDays,
			int userID) {
		super();
		this.habitID = habitID;
		this.habitName = habitName;
		this.habitType = habitType;
		this.habitGoal = habitGoal;
		this.habitDays = habitDays;
		UserID = userID;
	}

	public int getHabitID() {
		return habitID;
	}

	public void setHabitID(int habitID) {
		this.habitID = habitID;
	}

	public String getHabitName() {
		return habitName;
	}

	public void setHabitName(String habitName) {
		this.habitName = habitName;
	}

	public String getHabitType() {
		return habitType;
	}

	public void setHabitType(String habitType) {
		this.habitType = habitType;
	}

	public int getHabitGoal() {
		return habitGoal;
	}

	public void setHabitGoal(int habitGoal) {
		this.habitGoal = habitGoal;
	}

	public String getHabitDays() {
		return habitDays;
	}

	public void setHabitDays(String habitDays) {
		this.habitDays = habitDays;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	@Override
	public String toString() {
		return "HabitInfoDTO [habitID=" + habitID + ", habitName=" + habitName + ", habitType=" + habitType
				+ ", habitGoal=" + habitGoal + ", habitDays=" + habitDays + ", UserID=" + UserID + "]";
	}
	
	
	
	
}
