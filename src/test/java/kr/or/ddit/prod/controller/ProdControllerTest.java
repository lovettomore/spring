package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class ProdControllerTest extends ControllerTestEnv{

	/**
	 * 
	* Method 		: prodPagingListTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: prod 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void prodPagingListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prod/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<ProdVO> prodList = (List<ProdVO>) mav.getModelMap().get("prodList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVO pageVO = (PageVO) mav.getModelMap().get("pageVO");

		/***Then***/
		assertEquals("prod/prodPagingList", viewName);
		assertEquals(10, prodList.size());
		assertEquals(8, paginationSize);
		assertEquals(2, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
	}
	

}
