package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.service.IBoardService;

//스프링 환경에서 junit을 사용할 수 있는 방법이에요.
@RunWith(SpringJUnit4ClassRunner.class)
//설정파일 위치를 여기에 지정할 수 있어요.
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpiringIocJunitTest {

	//테스트 하려는 녀석이 이녀석이죠 service. resorece로 service를 주입 받았어요.
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Test
	public void springIocTest() {
		
		/***Given***/
		
		/***When***/
		String msg = boardService.sayHello();
		
		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
		
	}                                       

}
