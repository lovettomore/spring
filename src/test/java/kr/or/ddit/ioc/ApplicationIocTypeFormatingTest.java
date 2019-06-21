package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")

public class ApplicationIocTypeFormatingTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormatingTest.class);
	
	@Resource(name = "formattingVO")
	private FormattingVO formattingVO;
	

	@Test
	public void formatingConversionServiceTest() {
		/***Given***/
		
		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sdf.format(formattingVO.getReg_dt());
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
		String newDate2 = sdf2.format(formattingVO.getMod_dt());
		
		
		/***Then***/
		assertNotNull(formattingVO);
		assertEquals("2019-06-21", newDate);
		assertEquals("06-21-2019", newDate2);
		assertEquals(6000, formattingVO.getNumber());
		
		logger.debug("newDate : {}", newDate);
		logger.debug("newDate2 : {}", newDate2);
		logger.debug("number : {}", formattingVO.getNumber());
		
	}

}
