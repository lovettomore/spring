package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.paging.model.PageVO;
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
	
	/**
	 * 
	* Method 		: getUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userid
	* @return
	* Method 설명 	: 사용자 정보 조회
	 */
	UserVO getUser(String userId);
	
	/**
	 * 
	* Method 		: updateUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userVO
	* @return
	* Method 설명 	: 사용자 업데이트
	 */
	int updateUser(UserVO userVO);
	
	/**
	 * 
	* Method 		: userPagingList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param pageVO
	* @return
	* Method 설명 	: 사용자 페이징 리스트 조회
	 */
	Map<String, Object> userPagingList(PageVO pageVO);
	
	
	/**
	 * 
	* Method 		: userListForPassEncrypt
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param sqlSession
	* @return
	* Method 설명 	: 비밀번호 암호화 적용대상 사용자 전체 조회
	 */
	int encryptPassAllUser(SqlSession sqlSession);

}
