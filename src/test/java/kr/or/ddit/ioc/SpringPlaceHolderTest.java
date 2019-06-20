package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringPlaceHolderTest {

	@Resource(name="dbInfo")
	private DbInfo dbInfo;
	
	/**
	 * 
	* Method 		: springPlaceHolderTest
	* 작성자 			: 
	* 변경이력 		:
	* Method 설명 	: spring placeholder test
	 */
	@Test
	public void springPlaceHolderTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertEquals("oracle.jdbc.driver.OracleDriver", dbInfo.getDriver());
		assertEquals("jdbc:oracle:thin:@112.220.114.130:1521:xe", dbInfo.getUrl());
		assertTrue(dbInfo.getUrl().contains("jdbc:oracle:thin:@112.220.114.130:1521:xe"));
		assertEquals("MDMS", dbInfo.getUsername());
		assertEquals("java", dbInfo.getPassword());
	}

}
