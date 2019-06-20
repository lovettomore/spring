package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {

	
	@Resource(name="collectionBean")
	private IocCollection iocCollection;
	
	
	@Test
	public void springIocCollectionTest() {
		/***Given***/
		
		/***When***/

		/***Then***/
		assertEquals("brown", iocCollection.getList().get(0));
		assertEquals("sally", iocCollection.getList().get(1));
		assertEquals("james", iocCollection.getList().get(2));
		assertEquals("cony", iocCollection.getList().get(3));
		
		assertEquals("brown", iocCollection.getMap().get("name"));
		assertEquals("20150808", iocCollection.getMap().get("birth"));
		
		assertTrue(iocCollection.getSet().contains("brown"));
		assertTrue(iocCollection.getSet().contains("sally"));
		assertTrue(iocCollection.getSet().contains("james"));
		assertTrue(iocCollection.getSet().contains("cony"));
		
		assertEquals("brown", iocCollection.getProperties().get("userId"));
		assertEquals("브라운", iocCollection.getProperties().get("name"));
	}

}
