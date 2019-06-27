package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodDaoTest extends ControllerTestEnv{
	
	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;

	/**
	 * 
	* Method 		: lpordListTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: lprod 전체 리스트 조회 테스트
	 */
	@Test
	public void lpordListTest() {
		/***Given***/
		
		/***When***/
		List<LprodVO> lprodList = lprodDao.lprodList();
		
		/***Then***/
		assertEquals(15, lprodList.size());
	}
	
	/**
	 * 
	* Method 		: getLprodTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: lprod 선택 조회 테스트
	 */
	@Test
	public void getLprodTest() {
		/***Given***/
		String lprod_gu = "P101";
		
		/***When***/
		LprodVO lprodVO = lprodDao.getLprod(lprod_gu);

		/***Then***/
		assertEquals("컴퓨터제품", lprodVO.getLprod_nm());
		assertEquals(1, lprodVO.getLprod_id());
	}
	
	/**
	 * 
	* Method 		: lprodPagingListTest
	* 작성자 			: chewoop
	* 변경이력 		:
	* Method 설명 	: lprod 페이징 리스트 테스트
	 */
	@Test
	public void lprodPagingListTest() {
		/***Given***/
		PageVO pageVO = new PageVO(1, 10);
		
		/***When***/
		List<LprodVO> lprodPagingList = lprodDao.lprodPasingList(pageVO);

		/***Then***/
		assertNotNull(lprodPagingList);
		assertEquals(10, lprodPagingList.size());
	}
	
	

}
