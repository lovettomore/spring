package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	 * 
	* Method 		: test
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: 사용자 전체 리스트 조회
	 */
	@Test
	public void userListTest() {
		/***Given***/
		
		/***When***/
		List<UserVO> userList = userService.userList();
		
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
		UserVO userVO = new UserVO("lovettomore", "seokyoung", "또교미", "tto1234", "대전시 서구 둔산동", "103-24", "22434", spf.parse("2019-06-20"));
		
		/***When***/
		//userDao.insertUser() 를 실행
		int insertCnt = userService.insertUser(userVO);

		/***Then***/
		//inserCnt(1) 이면 성공
		assertEquals(1, insertCnt);
		
		userService.deleteUser(userVO.getUserId());
	}
	
	/**
	 * 
	* Method 		: userListTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: 특정 사용자 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = userService.getUser(userId);
		
		/***Then***/
		assertEquals("brown", userVO.getUserId());
		assertEquals("brown", userVO.getUserId());
	}
	
	/**
	 * 
	* Method 		: userPagingList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param pageVO
	* @return
	* Method 설명 	: 사용자 페이징 리스트 조회 테스트
	 */
	@Test
	public void userPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		@SuppressWarnings("unchecked")
		List<UserVO> userList = (List<UserVO>) userService.userPagingList(pageVO);

		/***Then***/
		assertNotNull(userList);
		assertEquals(10, userList.size());
	}

}

