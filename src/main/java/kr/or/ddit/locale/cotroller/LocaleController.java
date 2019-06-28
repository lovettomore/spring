package kr.or.ddit.locale.cotroller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/locale")
@Controller
public class LocaleController {
	private static final Logger logger = LoggerFactory.getLogger(LocaleController.class);
	
		/***
		 * 
		* Method 		: view
		* 작성자 			: 
		* 변경이력 		:
		* @return
		* Method 설명 	: locale test를 위한 view 요청
		 */
		
		//localhost/locale/view
		@RequestMapping("/view")
		public String view(Locale locale, Model model) {
		//public String view(@RequestParam(name="lang", defaultValue = "ko")String lang, Model model) {
			
//		logger.debug("locale ::::::: {}", locale);
//		logger.debug("getCountry ::::::: {}", locale.getCountry());
//		logger.debug("getLanguage ::::::: {}", locale.getLanguage());
//		logger.debug("getISO3Country ::::::: {}", locale.getISO3Country());
//		logger.debug("getISO3Language ::::::: {}", locale.getISO3Language());
		
		
		model.addAttribute("lang", locale.getLanguage());
		return "tiles.locale";
	}
}
