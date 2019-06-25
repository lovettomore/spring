package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;


public class UserControllerTest extends ControllerTestEnv{
	
	/**
	 * 
	* Method 		: userListTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: 사용자 전체 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(117, ((List<UserVO>)mav.getModelMap().get("userList")).size());
	}
	
	/**
	 * 
	* Method 		: userPagingListTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: 사용자 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userPagingListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userPagingList").param("page", "2").param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<UserVO> userList = (List<UserVO>) mav.getModelMap().get("userList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVO pageVO = (PageVO) mav.getModelMap().get("pageVO");

		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(12, paginationSize);
		assertEquals(2, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
	}
	
	/**
	 * 
	* Method 		: userPagingListWithoutParameterTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* @throws Exception
	* Method 설명 	: 사용자 페이징 리스트 테스트(page, pageSize 파라미터 없이 호출)
	 */
	@Test
	public void userPagingListWithoutParameterTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/userPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<UserVO> userList = (List<UserVO>) mav.getModelMap().get("userList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVO pageVO = (PageVO) mav.getModelMap().get("pageVO");

		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(12, paginationSize);
		assertEquals(1, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
	}

}
