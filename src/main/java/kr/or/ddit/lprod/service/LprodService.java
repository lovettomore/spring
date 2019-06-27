package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Service
public class LprodService implements ILprodService {
	
	@Resource(name = "lprodDao")
	private ILprodDao lprodDao;

	
	/**
	 * 
	* Method 		: lprodList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 전체 조회
	 */
	@Override
	public List<LprodVO> lprodList() {
		return lprodDao.lprodList();
	}

	/**
	 * 
	* Method 		: lprodPasingList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> lprodPasingList(PageVO pageVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lprodList", lprodDao.lprodPasingList(pageVO));
		
		//usersCnt > paginationSize 으로 변경
		int lprodCnt = lprodDao.lprodCnt();
		
		//pageSize > pageVO.getPageSize();
		int paginationSize = (int)Math.ceil((double)lprodCnt/pageVO.getPageSize());
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	/**
	 * 
	* Method 		: getLprod
	* 작성자 			: 
	* 변경이력 		:
	* @param lprod_gu
	* @return
	* Method 설명 	: lprod 코드로 lprod리스트 조회
	 */
	@Override
	public LprodVO getLprod(String lprod_gu) {
		return lprodDao.getLprod(lprod_gu);
	}


}
