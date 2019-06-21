package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoTest extends LogicTestEnv{
	
	@Resource(name = "userDao")
	private IUserDao userDao;

	/**
	 * 
	* Method 		: userListTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: 사용자 전체 리스트 조회 테스트
	 */
	@Test
	public void userListTest() {
		/***Given***/
		
		/***When***/
		List<UserVO> userList = userDao.userList();
		
		/***Then***/
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
	}
	
	/**
	 * 
	* Method 		: insertUserTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: 사용자 등록 테스트
	 * @throws ParseException 
	 */
	@Test
	public void insertUserTest() throws ParseException {
		/***Given***/
		//사용자 정보를 담고있는 vo객체 준비
		
		SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVO = new UserVO("lovememore", "seokyoung", "또교미", "tto1234", "대전시 서구 둔산동", "103-24", "22434", spf.parse("2019-06-20"));
		
		/***When***/
		//userDao.insertUser() 를 실행
		int insertCnt = userDao.insertUser(userVO);

		/***Then***/
		//inserCnt(1) 이면 성공
		assertEquals(1, insertCnt);
		
		//userDao.deleteUser(userVO.getUserId());
	}

}
