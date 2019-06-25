package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
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

	/**
	 * 
	* Method 		: updateUser
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userVO
	* @return
	* Method 설명 	: 사용자 수정
	 */
	@Override
	public int updateUser(UserVO userVO) {
		return sqlSession.update("user.updateUser", userVO);
	}

	/**
	 * 
	* Method 		: userPagingList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param pageVO
	* @return
	* Method 설명 	: 사용자 페이징 리스트 조회
	 */
	@Override
	public List<UserVO> userPagingList(PageVO pageVO) {
		return sqlSession.selectList("user.userPagingList", pageVO);
	}
	
	/**
	 * 
	* Method 		: usersCnt
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 건수 조회
	 */
	@Override
	public int usersCnt() {
		return sqlSession.selectOne("user.usersCnt");
	}

	
	/**
	 * 
	* Method 		: userListForPassEncrypt
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param sqlSession
	* @return
	* Method 설명 	: 비밀번호 암호화 적용대상 사용자 전체 조회
	 */
	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	
	/**
	 * 
	* Method 		: updateUserEncryptPass
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param sqlSession
	* @param userVO
	* @return
	* Method 설명 	: 사용자 비밀번호 암호화 적용
	 */
	public static int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return sqlSession.update("user.updateUserEncryptPass", userVO);
	}


}
