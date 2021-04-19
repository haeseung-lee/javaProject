package com.justhabit.model.dto;

import java.io.Serializable;

public class UserLevelDTO implements Serializable {
	
	
	private static final long serialVersionUID = 5296527180023421000L;
	private int userId;
	private int userLevel;
	private String userImage;
	private int numOfHabits;
	private int successOfCheck;
	private int successOfTimer;
	
	public UserLevelDTO() {
		super();
	}

	public UserLevelDTO(int userId, int userLevel, String userImage, int numOfHabits, int successOfCheck,
			int successOfTimer) {
		super();
		this.userId = userId;
		this.userLevel = userLevel;
		this.userImage = userImage;
		this.numOfHabits = numOfHabits;
		this.successOfCheck = successOfCheck;
		this.successOfTimer = successOfTimer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getNumOfHabits() {
		return numOfHabits;
	}

	public void setNumOfHabits(int numOfHabits) {
		this.numOfHabits = numOfHabits;
	}

	public int getSuccessOfCheck() {
		return successOfCheck;
	}

	public void setSuccessOfCheck(int successOfCheck) {
		this.successOfCheck = successOfCheck;
	}

	public int getSuccessOfTimer() {
		return successOfTimer;
	}

	public void setSuccessOfTimer(int successOfTimer) {
		this.successOfTimer = successOfTimer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserLevelDTO [userId=" + userId + ", userLevel=" + userLevel + ", userImage=" + userImage
				+ ", numOfHabits=" + numOfHabits + ", successOfCheck=" + successOfCheck + ", successOfTimer="
				+ successOfTimer + "]";
	}

	
	
	
}
