package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
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
		MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/userList", mav.getViewName());
		assertEquals(116, ((List<UserVO>)mav.getModelMap().get("userList")).size());
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
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
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
		MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<UserVO> userList = (List<UserVO>) mav.getModelMap().get("userList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVO pageVO = (PageVO) mav.getModelMap().get("pageVO");

		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertEquals(10, userList.size());
		assertEquals(12, paginationSize);
		
		//PageVO에서 equals, hashCode메소드를 구현하고, 객채간 값 비교
		assertEquals(new PageVO(1, 10), pageVO);
		
		assertEquals(1, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
	}
	
	/**
	 * 
	* Method 		: userTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* @throws Exception
	* Method 설명 	: 사용자 상세조회 테스트
	 */
	@Test
	public void userTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVO = (UserVO) mav.getModelMap().get("userVO");
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("brown", userVO.getUserId());
	}
	
	/**
	 * 
	* Method 		: userFormTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: 사용자 입력 화면 요청 
	 * @throws Exception 
	 */
	@Test
	public void userFormTest() throws Exception {
		mockMvc.perform(get("/user/form")).andExpect(view().name("user/userForm"));
	}
	
	/**
	 * 
	* Method 		: userFormPostSuccessTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: 사용자 등록 테스트 (success 시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/brown.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file).param("userId", "loveme")
													   	  .param("pass", "love1234")
													   	  .param("name", "박서경")
													   	  .param("alias", "또굥이")
													   	  .param("addr1", "대전광역시 중구 중앙로76")
													   	  .param("addr2", "영민빌딩 2층 204호")
													   	  .param("zipcd", "34940")
													   	  .param("birth", "1991-04-13"))
													   	  .andExpect(view().name("redirect:/user/pagingList"));
	}
	
	/**
	 * 
	* Method 		: userFormPostFailTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: 사용자 등록 테스트 (success 시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostFailTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/brown.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file).param("userId", "lovememore")
													   	  .param("pass", "love1234")
													   	  .param("name", "박서경")
													   	  .param("alias", "또굥이")
													   	  .param("addr1", "대전광역시 중구 중앙로76")
													   	  .param("addr2", "영민빌딩 2층 204호")
													   	  .param("zipcd", "34940")
													   	  .param("birth", "1991-04-13"))
													   	  .andExpect(view().name("user/userForm"));
	}
	
	
	/**
	 * 
	* Method 		: profileTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: 사용자 사진 응답 테스트
	 * @throws Exception 
	 */
	@Test
	public void profileTest() throws Exception {
		mockMvc.perform(get("/user/profile").param("userId", "brown")).andExpect(status().isOk());
	}
	
	/**
	 * 
	* Method 		: userModify
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: 사용자 수정 화면 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void userModify() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		UserVO userVO = (UserVO) mav.getModelMap().get("userVO");
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userModify", viewName);
		assertEquals("brown", userVO.getUserId());
		
	}
	
	/**
	 * 
	* Method 		: userModifyPostTest
	* 작성자 			: 
	* 변경이력 		:
	* @throws Exception
	* Method 설명 	: 사용자 수정 요청 테스트
	 */
	@Test
	public void userModifyPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/brown.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		mockMvc.perform(fileUpload("/user/modify").file(file).param("userId", "lovememore")
													   	  .param("pass", "love1234")
													   	  .param("name", "박서경")
													   	  .param("alias", "또굥이")
													   	  .param("addr1", "대전광역시 중구 중앙로76")
													   	  .param("addr2", "영민빌딩 2층 204호")
													   	  .param("zipcd", "34940")
													   	  .param("birth", "1991-04-13"))
													   	  .andExpect(view().name("redirect:/user/user?userId=lovememore"));
	}
	

}
