package kr.or.ddit.config.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.or.ddit.view.ExcelDownloadView;
import kr.or.ddit.view.ProfileView;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//기본필터를 꺼줘야 겠죠?
@ComponentScan(basePackages = {"kr.or.ddit"}, 
			   useDefaultFilters = false, 
			   includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})})

@EnableWebMvc 	//<mvc:annotation-driven/> 이거랑 같은거라고 생각하면 됩니당.
@Configuration 	//설정파일이라는것을 명시 해야겠죠?


//WebMvcConfigurerAdapter 얘를 상속을받으면 디폴트 서블릿을 상속 받을수가 있어요. //configureDefaultServletHandling 이거를 재정의(오버라이드) 하시면 되거든용?
public class ApplicationContext extends WebMvcConfigurerAdapter{

	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable(); //이렇게 하면 <mvc:default-servlet-handler/> 얘를 활성화 한다는 뜻이거든용?
	}
	
	
	/*
	 	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/WEB-INF/views/"/> <!-- 접두 -->
			<property name="suffix" value=".jsp"/> <!-- 접미 -->
			<property name="order" value="3"/>
		</bean>
	*/
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		//InternalResourceViewResolver irvr = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp"); 이렇게 해도 된데요.
		
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		irvr.setOrder(3);
		
		return irvr;
		
	}
	
	
	/*
		<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
			<property name="order" value="2"/>
		</bean>
	*/
	@Bean
	public ViewResolver beanNameViewResolver() {
		BeanNameViewResolver bnvr = new BeanNameViewResolver();
		bnvr.setOrder(2);
		
		return bnvr;
	}
	
	
	/*
		<bean class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
			<property name="definitions">
				<list>
					<value>classpath:kr/or/ddit/config/tiles/tiles-config.xml</value>
				</list>
			</property>
		</bean>
	*/
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-config.xml");
		
		return tilesConfigurer;
	}
	
	
	/*
	 	<bean class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
			<property name="order" value="1"/>
			<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"></property>
		</bean>
	*/
	@Bean
	public ViewResolver tilesViewResolver() {
		
		TilesViewResolver tvr = new TilesViewResolver();
		tvr.setOrder(1);
		tvr.setViewClass(TilesView.class);
		
		return tvr;
	}
	
	/*
		<bean id ="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
		<bean id ="profileView" class="kr.or.ddit.view.ProfileView"/>
		<bean id="userExcelView" class="kr.or.ddit.view.ExcelDownloadView"/>
	*/
	@Bean
	public View jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Bean
	public View profileView() {
		return new ProfileView();
	}
	
	@Bean
	public View userExcelView() {
		return new ExcelDownloadView();
	}
	
	
	/*
		<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
			<property name="maxUploadSizePerFile" value="3145728"/>
			<property name="maxUploadSize" value="15728640"/>
		</bean>
	*/
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		cmr.setMaxUploadSizePerFile(1024 * 1024 * 3);
		cmr.setMaxUploadSize(1024 * 1024 * 3 * 5);
		
		return cmr;
	}
	
	
	/*
		<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
			<property name="basenames">
				<list>
					<value>classpath:kr/or/ddit/msg/error</value>
					<value>classpath:kr/or/ddit/msg/msg</value>
				</list>
			</property>
		</bean>
	*/
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new  ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:kr/or/ddit/msg/error", "classpath:kr/or/ddit/msg/msg");
		
		return messageSource;
	}
	
	
	/*
		<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/>
	*/
	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

}







