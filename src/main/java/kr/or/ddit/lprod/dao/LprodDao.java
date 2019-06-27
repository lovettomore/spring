package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodDao implements ILprodDao{
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	 * 
	* Method 		: lprodList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 전체 조회
	 */
	@Override
	public List<LprodVO> lprodList() {
		return sqlSession.selectList("lprod.lprodList");
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
	public List<LprodVO> lprodPasingList(PageVO pageVO) {
		return sqlSession.selectList("lprod.lprodPasingList", pageVO);
	}
	
	/**
	 * 
	* Method 		: getLprod
	* 작성자 			: 
	* 변경이력 		:
	* @param lprod_gu
	* @return
	* Method 설명 	: lprod 코드로 파일리스트 조회
	 */
	@Override
	public LprodVO getLprod(String lprod_gu) {
		return sqlSession.selectOne("lprod.getLprod", lprod_gu);
	}

	
	/**
	 * 
	* Method 		: lprodCnt
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: lprod 전체 건수 조회
	 */
	@Override
	public int lprodCnt() {
		return sqlSession.selectOne("lprod.lprodCnt");
	}

}
