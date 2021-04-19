package com.justhabit.model.dto;

public class AllHabitDTO {
	private int UserID;
	private int habitID;
	private String habitName;
	private String habitType;
	private String habitDays;
	private int habitGoal;
	
	public AllHabitDTO() {
		super();
	}

	public AllHabitDTO(int userID, int habitID, String habitName, String habitType, String habitDays, int habitGoal) {
		super();
		UserID = userID;
		this.habitID = habitID;
		this.habitName = habitName;
		this.habitType = habitType;
		this.habitDays = habitDays;
		this.habitGoal = habitGoal;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
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

	public String getHabitDays() {
		return habitDays;
	}

	public void setHabitDays(String habitDays) {
		this.habitDays = habitDays;
	}

	public int getHabitGoal() {
		return habitGoal;
	}

	public void setHabitGoal(int habitGoal) {
		this.habitGoal = habitGoal;
	}

	@Override
	public String toString() {
		return "AllHabitDTO [UserID=" + UserID + ", habitID=" + habitID + ", habitName=" + habitName + ", habitType="
				+ habitType + ", habitDays=" + habitDays + ", habitGoal=" + habitGoal + "]";
	}

	

	
}
