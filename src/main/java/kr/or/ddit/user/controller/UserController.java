package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.ibatis.annotations.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.encrypt.kisa.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVOValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private IUserService userService;
	
	 
	/**
	 * 
	* Method 		: userList
	* 작성자 			: 
	* 변경이력 		:
	* @param model
	* @return
	* Method 설명 	: 사용자 전체 리스트
	 */
	
	//localhost/user/list
	@RequestMapping("/list")
	public String userList(Model model) {
		
		model.addAttribute("userList", userService.userList());
		return "user/userList";
	}
	
	/**
	 * 
	* Method 		: userPagingList
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 전체 페이징 리스트
	 */
	@RequestMapping("/pagingList")
//	public String userPagingList(@RequestParam(name = "page", defaultValue = "1") int page, 
//								 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, Model model) {
//		PageVO pageVO = new PageVO(page, pageSize);
	
	public String userPagingList(PageVO pageVO, Model model) {
		
		//간단한 방법으로 살려는 드릴게
		logger.debug("pageVO.getPage() :::::::::::: {}", pageVO.getPage());
		logger.debug("pageVO.getPageSize() :::::::::::: {}", pageVO.getPageSize());
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		Map<String, Object> resultMap = userService.userPagingList(pageVO);
		
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "tiles.userPagingList";
	}
	
	
	
	/**
	 * 
	* Method 		: pagingListAjax
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param pageVO
	* @param model
	* @return
	* Method 설명 	: 사용자 페이징 리스트 ajax 처리
	 */
	@RequestMapping("/pagingListAjax")
	public String pagingListAjax(PageVO pageVO, Model model) {
		
		model.addAttribute("data", userService.userPagingList(pageVO));
		return "jsonView";
	}
	
	
	@RequestMapping("/pagingListAjaxHtml")
	public String pagingListAjaxHtml(PageVO pageVO, Model model) {
		
		model.addAttribute("data", userService.userPagingList(pageVO));
		return "user/userPagingListAjaxHtml";
		
	}
	
	
	
	@RequestMapping("/pagingListAjaxView")
	public String pagingListAjaxView(Model model) {
		return "tiles.pagingListAjaxView";
	}
	
	
	
	/**
	 * 
	* Method 		: userListExcel
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param model
	* @param request
	* @return
	* Method 설명 	:
	 */
	@RequestMapping("/userListExcel")
	public String userListExcel(Model model, HttpServletRequest request) {
		
		List<String> header = new ArrayList<String>();
		String filename = "userListExcel";
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		
		request.setAttribute("filename", filename);
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		
		return "userExcelView";
	}
	
	/**
	 * 
	* Method 		: user
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 상세 조회
	 */
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		/* jsp에게 요청 하는것 */
		return "user/user";
		
	}
	
	/**
	 * 
	* Method 		: userAjax
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @param model
	* @return
	* Method 설명 	: 사용자 정보 json 응답
	 */
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model) {
		
		/* 
		 	그대로 리턴함 
		 	추측 문자열 : {userVO : {userId : "brown", name : "브라운"...} 이런 형태가 되지 않을까..
		 	실제 문자열 : {"userVO":{"userId":"brown","name":"브라운 이녀석"
		*/
		
		model.addAttribute("userVO", userService.getUser(userId));
		return "jsonView";
	}	
	
	/**
	 * 
	* Method 		: userForm
	* 작성자 			: 
	* 변경이력 		:
	* @return
	* Method 설명 	: 사용자 등록 화면
	 */
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	
	/**
	 * 
	* Method 		: userForm
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userVO
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 	: 사용자 등록
	 */
	//@RequestMapping(path = "form", method = RequestMethod.POST)
	public String userForm(UserVO userVO, BindingResult result, String userId, MultipartFile profile, Model model){
		
		new UserVOValidator().validate(userVO, result);
		
		//벨리데이터를 통해서 검증된 에러가 있는지를 체크하는거에요
		if(result.hasErrors()) {
			return "user/userForm";
		}
		
		logger.debug("userVO ::::::::::::: {}", userVO);
		
		String viewName = "";
		UserVO dbUser = userService.getUser(userId);
		
		
		//중복인지 아닌지 null아니면 사용자가 입력하는 아이디가 이미 db에 있다는 뜻.
		if(dbUser == null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			int insertCnt = userService.insertUser(userVO);
			if(insertCnt == 1) {
				viewName = "redirect:/user/pagingList";
			}
			
		} else {
			model.addAttribute("msg", "이미 사용자가 존재 합니다.");
			viewName = userForm();
		}
		
		return viewName;
	}
	
	
	/**
	 * 
	* Method 		: userForm
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userVO
	* @param userId
	* @param profile
	* @param model
	* @return
	* Method 설명 	: 사용자 등록
	 */
	@RequestMapping(path = "form", method = RequestMethod.POST)
	public String userFormJsr(@Valid UserVO userVO, BindingResult result, String userId, MultipartFile profile, Model model){
		
		//벨리데이터를 통해서 검증된 에러가 있는지를 체크하는거에요
		if(result.hasErrors()) {
			return "user/userForm";
		}
		
		logger.debug("userVO ::::::::::::: {}", userVO);
		
		String viewName = "";
		UserVO dbUser = userService.getUser(userId);
		
		
		//중복인지 아닌지 null아니면 사용자가 입력하는 아이디가 이미 db에 있다는 뜻.
		if(dbUser == null) {
			if(profile.getSize() > 0) {
				String filename = profile.getOriginalFilename();
				String ext = PartUtil.getExt(filename);
				
				String uploadPath = PartUtil.getUploadPath();
				
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(filename);
				
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			int insertCnt = userService.insertUser(userVO);
			if(insertCnt == 1) {
				viewName = "redirect:/user/pagingList";
			}
			
		} else {
			model.addAttribute("msg", "이미 사용자가 존재 합니다.");
			viewName = userForm();
		}
		
		return viewName;
	}
	
	/**
	 * 
	* Method 		: profile
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @param request
	* @param response
	 * @return 
	* @throws IOException
	* Method 설명 	: 사용자 사진 응답 생성
	 */
	@RequestMapping("/profile")
	public String profile(String userId, Model model) throws IOException {
		
		//사용자 정보(PATH)를 조회
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "profileView";
	}
	
	/**
	 * 
	* Method 		: userModify
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userId
	* @param model
	* @return
	* Method 설명 	: 사용자 정보 수정
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		
		UserVO userVO = userService.getUser(userId);
		model.addAttribute("userVO", userVO);
		
		return "user/userModify";
	}
	
	
	/**
	 * 
	* Method 		: userModify
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param userVO
	* @param profile
	* @param session
	* @param model
	* @return
	* @throws IllegalStateException
	* @throws IOException
	* Method 설명 	:
	 */
	@RequestMapping(path = "/modify", method = RequestMethod.POST)
	public String userModify(UserVO userVO, MultipartFile profile, HttpSession session, Model model, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		//추후 ajax 요청으로 분리
		//userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
		
		logger.debug("userVO ::::::::::::: {}", userVO);
		
		if(profile.getSize() > 0) {
			String filename = profile.getOriginalFilename();
			String ext = PartUtil.getExt(filename);
			String uploadPath = PartUtil.getUploadPath();
			
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVO.setPath(filePath);
			userVO.setFilename(filename);
			
			profile.transferTo(new File(filePath));
		}
		
		
		int updateCnt = userService.updateUser(userVO);
		if(updateCnt == 1) {
			//session.setAttribute("msg1", "[UPDATE SUCCESS]");
			redirectAttributes.addFlashAttribute("msg", "[UPDATE SUCCESS]");
			redirectAttributes.addAttribute("userId", userVO.getUserId()); //이거는 파라미터를 자동으로 붙여주는 기능이에요
			//return "redirect:/user/user?userId=" + userVO.getUserId();
			return "redirect:/user/user";
		}else {
			return userModify(userVO.getUserId(), model);
		}
	}
	
	

}



