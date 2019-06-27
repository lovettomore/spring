package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

public interface IProdService {
	
	
	/**
	 * 
	* Method 		: prodList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: prod 전체 조회
	 */
	List<ProdVO> prodList();
	
	/**
	 * 
	* Method 		: prodPasingList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: prod 페이징 리스트 조회
	 */
	Map<String, Object> prodPasingList(PageVO pageVO);
	
	/**
	 * 
	* Method 		: getLprod
	* 작성자 			: 
	* 변경이력 		:
	* @param lprod_gu
	* @return
	* Method 설명 	: prod 코드로 파일리스트 조회
	 */
	ProdVO getProd(String prod_id);
	
	
}
