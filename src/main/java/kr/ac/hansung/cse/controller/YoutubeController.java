package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Content;
import kr.ac.hansung.cse.service.YoutubeService;

@RestController
public class YoutubeController {
	
	@Autowired
	YoutubeService yService;
	
	@RequestMapping("/api/youtube/{searchText}")
	public List<Content> getItems(@PathVariable String searchText){
		
		return yService.searchItems(searchText);
	}
}
