package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private IUserService userService;
	
	 
	/**
	 * 
	* Method 		: userList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param model
	* @return
	* Method 설명 	: 사용자 전체 리스트
	 */
	@RequestMapping("/userList")
	public String userList(Model model) {
		
		model.addAttribute("userList", userService.userList());
		return "user/userList";
	}
	
	/**
	 * 
	* Method 		: userPagingList
	* 작성자 			: chewoop
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 페이징 리스트
	 */
	@RequestMapping("/userPagingList")
	
//	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int page, 
//								 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, Model model) {
//		PageVO pageVO = new PageVO(page, pageSize);
	
	public String userPagingList(PageVO pageVO, Model model) {
		
		//간단한 방법으로 살려는 드릴게
		logger.debug("pageVO.getPage() :::::::::::: {}", pageVO.getPage());
		logger.debug("pageVO.getPageSize() :::::::::::: {}", pageVO.getPageSize());
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		
		@SuppressWarnings("unchecked")
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "user/userPagingList";
	}

}
