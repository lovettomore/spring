package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVO;

@Service
public class ProdService implements IProdService {
	
	@Resource(name = "prodDao")
	private IProdDao prodDao;

	
	/**
	 * 
	* Method 		: prodList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: prod 전체 조회
	 */
	@Override
	public List<ProdVO> prodList() {
		return prodDao.prodList();
	}

	/**
	 * 
	* Method 		: prodPasingList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: prod 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> prodPasingList(PageVO pageVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("prodList", prodDao.prodPasingList(pageVO));
		
		//usersCnt > paginationSize 으로 변경
		int prodCnt = prodDao.prodCnt();
		
		//pageSize > pageVO.getPageSize();
		int paginationSize = (int)Math.ceil((double)prodCnt/pageVO.getPageSize());
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
	* Method 설명 	: prod 코드로 prod리스트 조회
	 */
	@Override
	public ProdVO getProd(String prod_id) {
		return prodDao.getProd(prod_id);
	}


}
