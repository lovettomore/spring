package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.util.PartUtil;

@RequestMapping("/file")
@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	/**
	 * 
	 * Method : uploadView 작성자 : 변경이력 :
	 * 
	 * @return Method 설명 : file upload를 할 수 있는 테스트 리턴
	 */
	@RequestMapping("/uploadView")
	public String uploadView() {

		return "upload/upload";
	}

	// 업로드를 처리할 수 있는 리퀘스트 맵핑
	@RequestMapping("/upload")
	public String upload(@RequestPart("img") MultipartFile file, Model model) {

		logger.debug("file.getOriginalFilename() : {}", file.getOriginalFilename());
		logger.debug("file.getName() ::::::::::::: {}", file.getName());
		logger.debug("file.getSize() ::::::::::::: {}", file.getSize());

		// 파일을 하나 만들어서 그것을 인자로 해서 한번 해볼게여
		 
		String path = PartUtil.getUploadPath();
		String ext = PartUtil.getExt(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString(); //중복을 피하기위한 가짜 파일 이름
		
		File uploadFile = new File(path + File.separator + fileName + ext);
		//File uploadFile = new File("/Users/lovettomore/Documents/springUpload");
		
		try {
			file.transferTo(uploadFile);
			model.addAttribute("msg", "[FILE_UPLOAD : SUCCESS]");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			model.addAttribute("msg", "[FILE_UPLOAD : FAIL]");
		}

		// 업로드가 끝나면 업로드 페이지로 돌아가기
		return "upload/upload";
	}
}
