package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PartUtil {
	
	private static final String UPLOAD_PATH = "/Users/lovettomore/Documents/springUpload/";
	
	public static String getExt(String fileName) {
	
		/*
		      String ext = "";
		      String[] splited = fileName.split("\\.");
		      
		      if(splited.length == 1)
		      return splited[splited.length-1];
		*/
      
      
      //lastIndexOf()는 찾는 문자가 없으면 -1을 반환
      String ext = "";
      int startIndex = fileName.lastIndexOf(".")+1;
      
      if(startIndex != 0) ext =  fileName.substring(startIndex);
      return ext.equals("") ? "" : "." + ext ;
      
   }
	
	/**
	 * 
	* Method 		: checkUploadFolder
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param yyyy
	* @param mm
	* Method 설명 	: 년, 월 업로드 폴더가 존재하는지 체크, 없을 경우 폴더 생성
	 */
	public static void checkUploadFolder(String yyyy, String mm) {
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		//신규년도로 넘어갔을 때 해당 년도의 폴더를 생성한다.
		if(!yyyyFolder.exists()) {
			//해당 폴더 안에 새로운 폴더 생성
			yyyyFolder.mkdir();
		}
		
		//월에 해당하는 폴더가 있는지 확인
		File mmFolder = new File(UPLOAD_PATH+ yyyy + File.separator + mm);
		if(!mmFolder.exists()) {
			mmFolder.mkdir();
		}
	}
	
	/**
	 * 
	* Method 		: getUploadPath
	* 작성자 			: chewoop
	* 변경이력 		:
	* @param yyyy
	* @param mm
	* @return
	* Method 설명 	: 업로드 경로를 반환
	 */
	public static String getUploadPath() {
		
		//업로드 할 폴더 확인
		//년도에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지
		Date date = new Date();
		SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");
		
		//String type으로 형변환
		String yyyyMM = yyyyMMSdf.format(date);
		String yyyy = yyyyMM.substring(0,4);
		String mm = yyyyMM.substring(4,6);
		
		PartUtil.checkUploadFolder(yyyy, mm);
		return UPLOAD_PATH + yyyy + File.separator + mm;
	}
	

}
