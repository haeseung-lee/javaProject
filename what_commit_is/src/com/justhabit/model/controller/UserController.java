package com.justhabit.model.controller;

import com.justhabit.model.dto.UserDTO;
import com.justhabit.model.dto.UserLevelDTO;
import com.justhabit.model.service.UserService;

public class UserController {

	private UserService userService = new UserService();
	
	public boolean loginCheck(String loginId, String loginPwd) {
		
		/* 유저가 입력한 로그인 아이디/패스워드 유효성 체크 */
		
		// 1. 입력한 정보가 공백으로 이루어진 경우 로그인 실패 
		if(loginId.trim().equals("") || loginPwd.trim().equals("")) {
			return false;
		} 
		
		
		
		
		return userService.login(loginId, loginPwd);
	}

	public String signupCheck(String signup_name, String pwd1, String pwd2,
			String email, String pin) {

		/* 유저가 입력한 회원가입용 데이터 유효성 체크 */
		
		//1. 모든 입력값 공백으로 이루어졌는지 확인
		if(signup_name.trim().equals("") || pwd1.trim().equals("") ||
				pwd2.trim().equals("") || email.trim().equals("")||
				pin.trim().equals(""))
			return "모든 필요한 정보가 채워지지 않았습니다";
		
		//2. 입력한 두번의 패스워드가 일치하는지 확인.
		if(!pwd1.equals(pwd2))
			return "입력하신 두 패스워드가 일치하지 않습니다";
		
		//3. 입력한 아이디가 이미 존재하는지 확인
		if(!userService.userIdCheck(signup_name)) 
			return "이미 존재하는 유저입니다";
		
		/* UserDTO를 생성하여 service호출 */
		UserDTO registerUser = new UserDTO();
		registerUser.setUserName(signup_name);
		registerUser.setUserPwd(pwd1);
		registerUser.setUserEmail(email);
		registerUser.setUserPin(Integer.parseInt(pin));
		
		
		/* 회원가입 */ 
		
		boolean isRegistered = userService.register(registerUser);
		
		
		return isRegistered == true ? "회원가입이 성공적으로 완료되었습니다 :)" : "에러발생";
		
	}

	public UserDTO userInfo(int loggedUserID) {
		
		/* 로그인 된 유저의 정보 가져오기 */
		return userService.userInfo(loggedUserID);
	}

	public boolean deleteUser(UserDTO myUser) {
		
		/* 시스템 계정 삭제를 못하게 막는다. */
		if(myUser.getUserId() < 10) {
			return false;
		}
		
		return userService.deleteUser(myUser.getUserId());
	}

	public String updateUser(UserDTO myUser, String input_username) {

		String result = "";
		
		/* 유저가 입력한 값 유효성 검사 */
		
		// 1. 시스템 계정 수정을 못하게 막는다. 
		if(myUser.getUserId() < 10) {
			result = "시스템계정은 수정할 수 없습니다";
		}
		
		// 2. 입력값이 빈값인지 체크.
		else if(myUser.getUserName().trim().equals("") || myUser.getUserPwd().trim().equals("")
				|| myUser.getUserEmail().trim().equals("")) {
			result = "빈칸을 모두 채워주세요";
		}
		
		// 3. 비밀번호에 기본값인 *이 포함되어있는지 확인
		else if(myUser.getUserPwd().contains("*")) {
			result = "비밀번호에 *포함 될 수 없습니다";
		}

		// 4. 사용자가 입력한 아이디가 현재 사용중인 아이디가 아닌 다른 아이디로 변경 하려고 할 때
		//    username 중복인지 확인 -> 위에 만든 userIdCheck() 사용
		else if(!myUser.getUserName().equals(input_username)) {
			if(!userService.userIdCheck(myUser.getUserName()))
				result = "입력한 아이디가 이미 존재하는 아이디입니다";
		}
		
		// 5. 회원정보 수정 
		else {
			result = userService.userUpdate(myUser);
		}
		
		return result;
	}

	public UserLevelDTO userLevel(int loggedUserID) {
		
		/* 1. 유저 정보 가져오기 */ 
		UserLevelDTO user = userService.userLevel(loggedUserID);
		
		/* 2. 기준에 맞게 레벨 update */
		/////////////////////////
		// 레벨 1 습관수 0 성공횟수 0// 
		// 레벨 2 습관수 1 성공횟수 1// 
		// 레벨 3 습관수 3 성공횟수 3//
		// 레벨 4 습관수 5 성공횟수 5////////
		// 레벨 5 습관수 7이상 성공횟수 7이상// 
		/////////////////////////////
		int userLevel = user.getUserLevel();
		int totalHabits = user.getNumOfHabits();
		int successOfChecks = user.getSuccessOfCheck();
		int successOfTimers = user.getSuccessOfTimer();
		
		int level = 1;
		
		
		
		if((successOfChecks + successOfTimers >= 7 ) && totalHabits >= 5 ) {
			level = 5;
		} else if((successOfChecks + successOfTimers >= 6 ) && totalHabits >= 4) {
			level = 4;
		} else if((successOfChecks + successOfTimers >= 5 ) && totalHabits >= 3) {
			level = 3;
		} else if((successOfChecks + successOfTimers >= 4 ) && totalHabits >= 2) {
			level = 2;
		} else {
			level = 1;
		}
		
		/* 3. 유저레벨에 변경이 생길 시, 유저 정보 업데이트 
		 * 3-1 View로 리턴값 변경
		 * 3-2 DB update  */
		if( userLevel != level ) {
			user.setUserLevel(level);
			userService.userLvlUpdate(user);
		}
		
		
		return user;
	}

}
