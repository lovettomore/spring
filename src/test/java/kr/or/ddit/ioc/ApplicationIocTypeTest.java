package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type.xml")
public class ApplicationIocTypeTest {

	@Resource(name="userVO")
	private UserVO userVO;
	
	@Test
	public void applicationTypeTest() {
		/***Given***/
		
		/***When***/
		Date birth = userVO.getBirth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth_str = sdf.format(birth);
		
		/***Then***/
		assertNotNull(userVO);
		assertEquals("2015-03-05", birth_str);
	}

}
