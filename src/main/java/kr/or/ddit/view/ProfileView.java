package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;

public class ProfileView implements View{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileView.class);

	/*
		contentType을 알려줘야하네여, 애매하네여? 우리가 자체적으로 알려줘야 하나바여 ㅠㅠ 
		우리가 사진을 응답을 만들어야 하니깐 img라고 해보죵
	*/
	@Override
	public String getContentType() {
		return "img";
	}
	
	
	/*
		우리가 특정사용자의 이미지 파일을 보내주는거자나요
		응답을 보내주려면은 path정보 userVO가 필요하지 않을까?
	*/
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.debug(":::::::::::::: profileView.redner():::::::::::::::");
		
		UserVO userVO = (UserVO) model.get("userVO");
		
		//PATH정보로 file을 읽어 들여서
		ServletOutputStream sos = response.getOutputStream();
		FileInputStream fis = null;
		String filePath = null;
		
		//사용자가 업로드한 파일이 존재할 경우
		if(userVO.getPath() != null) {
			filePath = userVO.getPath();
		} else {
			// 사용자가 업로드한 파일이 존재하지 않을 경우 : no-image.gif
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
		
	}

}
