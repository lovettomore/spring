package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;



public class ProdServiceTest extends ControllerTestEnv{

	@Resource(name = "prodService")
	private IProdService prodService;

	/**
	 * 
	* Method 		: prodListTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: prod 전체 리스트 조회 테스트
	 */
	@Test
	public void prodListTest() {
		/***Given***/
		
		/***When***/
		List<ProdVO> prodList = prodService.prodList();
		
		/***Then***/
		assertNotNull(prodList);
		assertTrue(prodList.size() <= 100);
	}
	
	/**
	 * 
	* Method 		: getProdTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: prod 조회 테스트
	 */
	@Test
	public void getProdTest() {
		/***Given***/
		String prod_id = "P202000014";
		
		/***When***/
		ProdVO prodVO = prodService.getProd(prod_id);
		
		/***Then***/
		assertEquals("P202000014", prodVO.getProd_id());
	}
	
	/**
	 * 
	* Method 		: prodPagingListTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param pageVO
	* @return
	* Method 설명 	: prod 페이징 리스트 조회 테스트
	 */
	@Test
	public void prodPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		@SuppressWarnings("unchecked")
		List<UserVO> prodList = (List<UserVO>) prodService.prodPasingList(pageVO).get("prodList");

		/***Then***/
		assertNotNull(prodList);
		assertEquals(10, prodList.size());
	}

}
