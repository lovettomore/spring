package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDao implements IUserDao{
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	* Method 		: userList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 리스트 조회
	 */
	@Override
	public List<UserVO> userList() {
		return sqlSession.selectList("user.userList");
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
		return sqlSession.insert("user.insertUser", userVO);
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
		return sqlSession.delete("user.deleteUser", userId);
	}

	/**
	 * 
	* Method 		: getUser
	* 작성자 			: chewoop
	* 변경이력 		: 
	* @param userId
	* @return
	* Method 설명 	: 특정 사용자 조회
	 */
	@Override
	public UserVO getUser(String userId) {
		return sqlSession.selectOne("user.userInfo", userId);
	}

}
