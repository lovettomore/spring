package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdDao implements IProdDao{
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
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
		return sqlSession.selectList("prod.prodList");
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
	public List<ProdVO> prodPasingList(PageVO pageVO) {
		return sqlSession.selectList("prod.prodPasingList", pageVO);
	}
	
	/**
	 * 
	* Method 		: getProd
	* 작성자 			: 
	* 변경이력 		:
	* @param lprod_gu
	* @return
	* Method 설명 	: prod prod_id로 리스트 조회
	 */
	@Override
	public ProdVO getProd(String prod_id) {
		return sqlSession.selectOne("prod.getProd", prod_id);
	}

	
	/**
	 * 
	* Method 		: prodCnt
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: prod 전체 건수 조회
	 */
	@Override
	public int prodCnt() {
		return sqlSession.selectOne("prod.prodCnt");
	}

}
