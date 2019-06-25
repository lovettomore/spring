package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml")
public class SpringIocStTest {

	//테스트 하려는 Dao와 Service를 resource로 주입을 받아용.
	@Resource(name="boardDao")
	private IBoardDao boardDao;
	
	@Resource(name="boardService")
	private IBoardService boardService;
		
	@Test
	public void springIocStTest() {
		/***Given***/
		
		/***When***/
		
		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
	}

}
