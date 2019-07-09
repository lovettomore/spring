package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdPagingController {
	
	@Resource(name = "prodService")
	private IProdService prodService;
	
	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	@RequestMapping("/pagingList")
	public String lprodPagingList(PageVO pageVO, Model model) {
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		Map<String, Object> resultMap = prodService.prodPasingList(pageVO);
		Map<String, Object> resultMap1 = lprodService.lprodPasingList(pageVO);
		
		
		List<ProdVO> prodList = (List<ProdVO>) resultMap.get("prodList");
		List<ProdVO> lprodList = (List<ProdVO>) resultMap1.get("lprodList");
		
		int paginationSize = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("prodList", prodList);
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "prod/prodPagingList";
	}
	
	@RequestMapping(path = "/pagingList", method = RequestMethod.POST)
	public String lprodPagingSelect(String lprodGu, Model model, PageVO pageVO) {
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		
		Map<String, Object> resultMap = prodService.prodPasingList(pageVO);
		List<ProdVO> prodList = (List<ProdVO>) resultMap.get("prodList");
		
//		if(lprodGu == prodList.get) {
//			
//		}
		return "prod/prodPagingList";
	}

}
