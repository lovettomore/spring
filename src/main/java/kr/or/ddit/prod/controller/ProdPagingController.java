package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(ProdPagingController.class);
	
	@Resource(name = "prodService")
	private IProdService prodService;
	
	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	@RequestMapping(path = "/pagingList", method = RequestMethod.GET)
	public String lprodPagingList(PageVO pageVO, Model model) {
		
		pageVO = new PageVO(pageVO.getPage(), pageVO.getPageSize());
		Map<String, Object> resultMap = prodService.prodPasingList(pageVO);
		Map<String, Object> resultMap1 = lprodService.lprodPasingList(pageVO);
		
		
		List<ProdVO> prodList = (List<ProdVO>) resultMap.get("prodList");
		List<LprodVO> lprodList = (List<LprodVO>) resultMap1.get("lprodList");
		
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
		Map<String, Object> resultMap1 = lprodService.lprodPasingList(pageVO);
		
		List<ProdVO> prodList = (List<ProdVO>) resultMap.get("prodList");
		List<LprodVO> lprodList = (List<LprodVO>) resultMap1.get("lprodList");
		
		int paginationSize = (Integer) resultMap.get("paginationSize");
		logger.debug("여기까지 오니? log ::::::::::");
		logger.debug("lprodGu log :::::::: {}", lprodGu);
		
		for(ProdVO prod : prodList) {
			if(lprodGu == prod.getProd_lgu()) {
				logger.debug("for log 여기까지 오니? ");
				logger.debug("prod.getProd_lgu() log :::::::: {}", prod.getProd_lgu());
				
				model.addAttribute("prodList", prodList);
			}
		}
		
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVO", pageVO);
		return "prod/prodPagingList";
	}

}
