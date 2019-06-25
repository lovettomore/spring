package kr.or.ddit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;


/*
	<context:component-scan base-package="kr.or.ddit">
		<context:include-filter type="annotation" expression="org.aspectj.lang.annotation.Aspect"/>
	</context:component-scan>

	<aop:aspectj-autoproxy/>
	<context:annotation-config/>
	
	이 세가지를 자바로 바꾸려고 하는거에요 
	<context:annotation-config/> 역할을 @Configuration에서 담당

*/

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board", "kr.or.ddit.aop"}, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Aspect.class))
@EnableAspectJAutoProxy
public class AopScanConfig {

}
