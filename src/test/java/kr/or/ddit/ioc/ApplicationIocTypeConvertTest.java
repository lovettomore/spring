package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-convert.xml")
public class ApplicationIocTypeConvertTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeConvertTest.class);

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
		assertEquals("2019-08-08", birth_str);
		
		logger.debug("birth_str : {}", birth_str);
	}

}
