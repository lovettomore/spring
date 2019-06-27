package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class ProdDaoTest extends ControllerTestEnv{
	
	@Resource(name = "prodDao")
	private IProdDao prodDao;

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
		List<ProdVO> prodList = prodDao.prodList();
		
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
		ProdVO prodVO = prodDao.getProd(prod_id);
		
		/***Then***/
		assertEquals("P202000014", prodVO.getProd_id());
	}
	
	/**
	 * 
	* Method 		: prodPagingListTest
	* 작성자 			: 
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
		List<ProdVO> prodList = prodDao.prodPasingList(pageVO);

		/***Then***/
		assertNotNull(prodList);
		assertEquals(10, prodList.size());
	}

}
