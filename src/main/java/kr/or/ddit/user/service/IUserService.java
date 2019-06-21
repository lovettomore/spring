package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.UserVO;

public interface IUserService {
	
	
	/**
	 * 
	* Method 		: userList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 리스트 조회
	 */
	List<UserVO> userList();

	/**
	 * 
	* Method 		: insertUser
	* 작성자 			: 
	* 변경이력 		:
	* @param userVO
	* @return
	* Method 설명 	: 사용자 등록
	 */
	int insertUser(UserVO userVO);

	/**
	 * 
	* Method 		: deleteUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: 사용자 삭제
	 */
	int deleteUser(String userId);

}
