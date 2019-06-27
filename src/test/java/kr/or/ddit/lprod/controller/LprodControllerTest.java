package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv{

	@Test
	public void lprodPagingListTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList").param("page", "2").param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<LprodVO> lprodList = (List<LprodVO>) mav.getModelMap().get("lprodList");
		int paginationSize = (Integer) mav.getModelMap().get("paginationSize");
		PageVO pageVO = (PageVO) mav.getModelMap().get("pageVO");
		
		/***Then***/
		assertEquals("lprod/lprodPagingList", viewName);
		assertEquals(5, lprodList.size());
		assertEquals(2, paginationSize);
		assertEquals(2, pageVO.getPage());
		assertEquals(10, pageVO.getPageSize());
	}

}
