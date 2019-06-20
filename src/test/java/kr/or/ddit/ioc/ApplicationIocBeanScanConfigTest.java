package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationIocBeanScanConfig.class})
public class ApplicationIocBeanScanConfigTest {

	/*
		<bean> 태그를 이용하여 스프링 빈을 등록하는 방식을 사용하지 않고
		@Controller, @Service, @Repository 어노테이션을 적용한 클래스를
		base package 하위 모든 클래스를 scan하여 스프링 빈으로 등록.
		--------------------------------------------------------
		boardDao, boardService 스프링 빈이 정상적으로 생성 되었는지 확인!!
	*/
	
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	/**
	 * 
	* Method 		: springBeanScanTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: spring bean scan test
	 */
	@Test
	public void springBeanScanTest() {
		/***Given***/
		
		/***When***/
		String msg = boardDao.sayHello();

		/***Then***/
		assertNotNull(boardDao);
		assertEquals("boardDao sayHello", msg);
		assertEquals(boardDao, boardService.getBoardDao());
		
	}
}
