package kr.or.ddit.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

//설정파일 읽을때 얘도 같이 읽을꺼에요
@ImportResource({
				 "classpath:kr/or/ddit/config/spring/application-scheduler.xml",
				 "classpath:kr/or/ddit/config/spring/application-batch.xml"
			   })

/*
	@service, @repository 우리는 얘네만 스캔 할꺼에요.두개의 annotation 대상으로만 스캔을 합니다.
	이녀석은 지금 kr/or/ddit/config/spring/root-context.xml을 자바 config로 변경하고 있는거에요.
	
	부모컨테이너 소스 > datasoure > transaction할꺼에요
*/
@Configuration

//useDefaultFilters 얘를 안써주면 전체를 스캔을해요. 리소스가 낭비가 되는거죠. 초급 개발자가 실수를 제일 많이 한데요.
@ComponentScan(
					basePackages = "kr.or.ddit",
					useDefaultFilters = false, 
					includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Service.class, Repository.class})}
			  ) 
public class RootContext {

}
