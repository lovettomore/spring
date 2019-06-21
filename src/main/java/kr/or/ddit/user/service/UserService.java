package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IUserService{

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	/**
	 * 
	* Method 		: userList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}
	
	/**
	 * 
	* Method 		: insertUser
	* 작성자 			: 
	* 변경이력 		:
	* @param userVO
	* @return
	* Method 설명 	: 사용자 등록
	 */
	@Override
	public int insertUser(UserVO userVO) {
		return userDao.insertUser(userVO);
	}

	/**
	 * 
	* Method 		: deleteUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

}
