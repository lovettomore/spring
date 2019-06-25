package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

//우리가 테스트 해야하는것은 일반 자바환경 중에서도 웹환경 입니다.
//applicationContext도 웹환경에 맞춰진녀석을 사용해야해요. 그래서 applicationContext를 생성해 줍니다.
public class MainControllerTest extends ControllerTestEnv {

	/**
	 * 
	* Method 		: mainViewTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: Main View 호출 테스트
	 * @throws Exception 
	 */
	
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/main")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView(); //model과 view를 한꺼번에 관리를 할수가 있어요.
		String viewName = mav.getViewName();
		String userId = (String)mav.getModel().get("mainUserId");
		
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("brown", userId);
	}
	
	@Test
	public void mainViewAndExpectTest() throws Exception {
		mockMvc.perform(get("/main")).andExpect(status().isOk())
									 .andExpect(view().name("main"))
									 .andExpect(model().attribute("mainUserId", "brown"))
									 .andExpect(model().attributeExists("rangers"))
									 .andExpect(model().attributeExists("userVO"));
		
	}
	
	/**
	 * 
	* Method 		: pathvariableTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: @pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void pathvariableTest() throws Exception {
		mockMvc.perform(get("/main/pathvariable/brown")).andExpect(status().isOk())
														.andExpect(view().name("main"));

	}
	
	/**
	 * 
	* Method 		: pathvariableTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: @pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void pathvariableTest2() throws Exception {
		mockMvc.perform(get("/main/pathvariable/sally")).andExpect(status().isOk())
														.andExpect(view().name("main"));

	}
	
	/**
	 * 
	* Method 		: pathvariableTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: @pathvariable 테스트
	 * @throws Exception 
	 */
	@Test
	public void requestHeaderTest() throws Exception {
		mockMvc.perform(get("/main/header").accept("text/html"))
										   .andExpect(status().isOk())
										   .andExpect(view().name("main"));

	}
	
	

	

}
