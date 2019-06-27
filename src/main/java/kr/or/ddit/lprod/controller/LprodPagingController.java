package kr.or.ddit.lprod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVO;

@RequestMapping("/lprod")
@Controller
public class LprodPagingController {
	
	private static final Logger logger = LoggerFactory.getLogger(LprodPagingController.class);
	
	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	@RequestMapping("/pagingList")
	public String lprodPagingList(PageVO pageVO, Model model) {
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		Map<String, Object> resultMap = lprodService.lprodPasingList(pageVO);
		
		List<LprodVO> lprodList = (List<LprodVO>) resultMap.get("lprodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "lprod/lprodPagingList";
	}

}
