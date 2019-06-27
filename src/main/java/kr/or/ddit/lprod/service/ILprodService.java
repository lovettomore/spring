package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodService {
	
	/**
	 * 
	* Method 		: lprodList
	* 작성자 			: chewoop
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
	Map<String, Object> lprodPasingList(PageVO pageVO);
	
	/**
	 * 
	* Method 		: getLprod
	* 작성자 			: 
	* 변경이력 		:
	* @param lprod_gu
	* @return
	* Method 설명 	: lprod 코드로 lprod리스트 조회
	 */
	LprodVO getLprod(String lprod_gu);
	
	
}
