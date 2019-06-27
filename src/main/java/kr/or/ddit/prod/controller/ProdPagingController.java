package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdPagingController {
	
	@Resource(name = "prodService")
	private IProdService prodService;
	
	@RequestMapping("/pagingList")
	public String lprodPagingList(PageVO pageVO, Model model) {
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		Map<String, Object> resultMap = prodService.prodPasingList(pageVO);
		
		List<LprodVO> prodList = (List<LprodVO>) resultMap.get("prodList");
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("prodList", prodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "prod/prodPagingList";
	}

}
