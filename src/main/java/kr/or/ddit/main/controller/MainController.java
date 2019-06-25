package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/*
	servlet		- extends HttpServlet
				- servlet-mapping (web.xml, @WebServlet) 
				- service > doXXX
	
	spring		- pojo(Plain Old Java Object) 
				- @RequestMapping(class, method)
				- @Requestmapping에 설정한 url method 호출
*/

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/main")
	public String mainView(Model model, HttpServletRequest request) {
		
		/*
			prefix : /WEB-INF/views/
			surffix : .jsp
		
			prefix + viewName + surffix
			/WEB-INF/views/main.jsp
		*/
		
		//아래의 문장은 다음과 동일하다. request.setAttribute("mainUserId", "brown")
		model.addAttribute("mainUserId", "brown");
		//request.setAttribute("mainUserId", "brown");
		
		//이녀석이 viewName이에요.
		return "main";
	}
	
	/*
	
		메소드에 적용된 @ModelAttribute
		@RequestMapping이 붙은 메소드가 실행될 때(요청이 처리될 때)
		@ModelAttribute가 적용된 메소드가 리턴하는 값을 Model객체에 자동적으로 넣어준다.
		localhost/main > @RequestMapping("/main") : mainView > Model에는 rangers 속성이 들어가 있다
		localhost/mainMav > @RequestMapping("/mainMav") : mainViewMav > Model에는
		rangers 속성이 들어가 있다
	 
	*/
	 
	@ModelAttribute("rangers")
	public List<String> rangers() {

		logger.debug("{}", "rangers()");
		List<String> rangers = new ArrayList<String>();
		rangers.add("sally");
		rangers.add("brown");
		rangers.add("cony");
		rangers.add("james");
		rangers.add("moon");

		return rangers;
	}
	
	
	
	/**
	 * 
	* Method 		: pathvariable
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @return
	* Method 설명 	: pathvariable로부터 사용자 아이디 가져오기(예시 : 도서관 사업소)
	 */
	//localhost/main/pathvariable/brown
	//localhost/main/pathvariable/sally
	//path를 조금 손쉽게 받을 수 있는 방법 @PathVariable
	@RequestMapping("/main/pathvariable/{userId}")
	public String pathvariable(@PathVariable("userId") String userId) {
		
		logger.debug("userId ::::::::: {}", userId);
		return "main";
	}
	
	/**
	 * 
	* Method 		: header
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param accept
	* @return
	* Method 설명 	: Accept 헤더 정보 가져오기
	 */
	@RequestMapping("/main/header")
	public String header(@RequestHeader(name = "Accept"/*, required = false*/) String accept) {
		
		logger.debug("Accepth :::::::::: {}", accept);
		return "main";
	}
	
}







