package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Content;
import kr.ac.hansung.cse.service.NaverService;

@RestController
public class NaverController {
	
	@Autowired
	NaverService nService;
	
	@RequestMapping("/api/naver/{searchItem}")
	public List<Content> getItems(@PathVariable String searchItem){
		return nService.searchItmes(searchItem);
	}
}
