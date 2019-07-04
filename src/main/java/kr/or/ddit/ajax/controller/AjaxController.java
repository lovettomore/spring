package kr.or.ddit.ajax.controller;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name = "userService")
	private IUserService userService;

	/**
	 * 
	* Method 		: view
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: ajax 호출용 view
	 */
	@RequestMapping("/view")
	public String view() {
		
		return "tiles.ajaxView";
	}
	
	@RequestMapping("/requestData")
	public String requestData(Model model) {
		
//		List<String> rangers = new ArrayList<String>();
//		rangers.add("brown");
//		rangers.add("sally");
//		rangers.add("cony");
		
		model.addAttribute("pageVO", new PageVO(5, 10));
//		model.addAttribute("pageVO2", new PageVO(2, 12));
//		model.addAttribute("rangers", rangers);
		/*
			{pageVO: {page:5, pageSize:10}}
			{pageVO: {page:5, pageSize:10}, pageVO2: {page:2, pageSize:12}, rangers:["brown","sally","cony"]}
		*/
		return "jsonView";
	}
	
	
	
	@RequestMapping("/requestDateResponseBody")
	@ResponseBody //이녀석이 의미하는 것은 응답 내용을 responsebody에 작성을 해라 라는 거에요
	public PageVO requestDateResponseBody () {
		return new PageVO(5, 10);
	}
	
	
	
	
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
		logger.debug("user ::::::: "); 
		logger.debug("user ::::::: : {}", userId); 
		
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "jsonView";
	}
	
	@RequestMapping("/userHtml")
	public String userHtml(String userId, Model model) {
		
		logger.debug("user ::::::: : {}", userId); 
		
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "user/userHtml"; //응답 뷰의 차이
	}
	
	
	//consumes : content-type제한  >  content type이 json일떄만 들어오는거에요
	//produces : Accept 헤더제한 : 메소드가 생성 가능한 타입 (accept헤더를 보고 판단한다.)
	@RequestMapping(path = "/requestBody", /*method = {RequestMethod.GET},*/ consumes = {"application/json"}, produces = {"application/json", "application/xml"})
	@ResponseBody
	public UserVO requestBody(@RequestBody UserVO userVO) {
		
		logger.debug("userVO log :::::::: {}", userVO);
		userVO.setUserId(userVO.getUserId() + "_MODIFY");
		userVO.setPass(userVO.getPass() + "_MODIFY");
		
		return userVO;
		
	}
}
