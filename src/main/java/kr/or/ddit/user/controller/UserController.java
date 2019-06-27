package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
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
		
		@SuppressWarnings("unchecked")
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (Integer)resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "user/userPagingList";
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
		
		return "user/user";
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
	@RequestMapping(path = "form", method = RequestMethod.POST)
	public String userForm(UserVO userVO, String userId, MultipartFile profile, Model model){
		
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
	* @throws IOException
	* Method 설명 	: 사용자 사진 응답 생성
	 */
	@RequestMapping("/profile")
	public void profile(String userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//사용자 정보(PATH)를 조회
		UserVO userVO = userService.getUser(userId);
		
		//PATH정보로 file을 읽어 들여서
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = null;
		String filePath = null;
		
		//사용자가 업로드한 파일이 존재할 경우
		if(userVO.getPath() != null) {
			filePath = userVO.getPath();
		}
		// 사용자가 업로드한 파일이 존재하지 않을 경우 : no-image.gif
		else
			filePath = request.getServletContext().getRealPath("/img/noimage.gif");
		
			File file = new File(filePath);
			fis = new FileInputStream(file);
			byte[] buffer = new byte[512];
			
			//response객체에 스트림으로 써준다.
			while(fis.read(buffer, 0, 512) != -1) {
				sos.write(buffer);
			};
			
			fis.close();
			sos.close();
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
	public String userModify(UserVO userVO, MultipartFile profile, HttpSession session, Model model) throws IllegalStateException, IOException {
		
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
			session.setAttribute("msg1", "[UPDATE SUCCESS]");
			return "redirect:/user/user?userId=" + userVO.getUserId();
		}else {
			return userModify(userVO.getUserId(), model);
		}
	}
	
	

}



