package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodDao {
	
	/**
	 * 
	* Method 		: lprodList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 전체 조회
	 */
	List<LprodVO> lprodList();
	
	/**
	 * 
	* Method 		: lprodPasingList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 페이징 리스트 조회
	 */
	List<LprodVO> lprodPasingList(PageVO pageVO);
	
	/**
	 * 
	* Method 		: getLprod
	* 작성자 			: 
	* 변경이력 		:
	* @param lprod_gu
	* @return
	* Method 설명 	: lprod 코드로 파일리스트 조회
	 */
	LprodVO getLprod(String lprod_gu);
	
	/**
	 * 
	* Method 		: lprodCnt
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 전체 건수 조회
	 */
	int lprodCnt();
	
}
