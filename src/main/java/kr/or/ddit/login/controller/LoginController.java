package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import sun.util.logging.resources.logging;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	 * 
	* Method 		: loginView
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param session
	* @return
	* Method 설명 	: 사용자 로그인 화면 요청
	 */
	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String loginView(HttpSession session) {
		
		logger.debug("logger :::::::::: {}");
		
		if(session.getAttribute("USER_INFO") != null) {
			return "main";
		}else {
			return "login/login";
		}
		
		/*
		 
		 	prefix : /WEB-INF/views/
		 	surffix : jsp
		 	
			지금 이렇게 경로가 되어있기 때문에 조심해야합니다.
		*/
	}
	
	/**
	 * 
	* Method 		: loginProcess
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 로그인 요청 처리
	 */
	@RequestMapping(path = "login", method = RequestMethod.POST)
	public String loginProcess(String userId, String password, String rememberme, HttpServletResponse response, HttpSession session) {
		
		/*
		 	사용자 파라미터 userId, password
			String userId = request.getParameter("userId");
			String password = request.getParameter("password");
			
			두개가 모두 스트링이니까 파라미터로 userId,와 password로 쉽게 작업할 수 있죠?
		*/

		String encryptPassword = KISA_SHA256.encrypt(password);
		UserVO userVO = userService.getUser(userId);
		
		if(userVO != null && encryptPassword.equals(userVO.getPass())) {
			rememberMeCookie(userId, rememberme, response);
			session.setAttribute("USER_INFO", userVO);
			return "main";
		} else {
			return "login/login";
		}
	}

	/**
	 * 
	* Method 		: rememberMeCookie
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @param rememberme
	* @param response
	* Method 설명 	: rememberme 쿠키를 응답으로 생성
	 */
	private void rememberMeCookie(String userId, String rememberme, HttpServletResponse response) {
		int cookieMaxAge = 0;
		if(rememberme != null) {
			cookieMaxAge = 60 * 60 * 24 * 30; // 30일짜리 MaxAge를 만들어줌
		}
		
		Cookie userIdCookie = new Cookie("userId", userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie rememberMeCookie = new Cookie("rememberme", "true");
		rememberMeCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(userIdCookie);
		response.addCookie(rememberMeCookie);
	}
	
}
