package com.hb.springpersistence.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.service.IPostService;

@Controller
public class HomeController {

	@Autowired
	private IPostService postService;
	
	@GetMapping("/") 
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/post")
	public String postPage(Model model) {
		List<PostDTO> posts = postService.getPostDTOs();
		model.addAttribute("post", posts.get(0));
		return "post";		
	}
	
	
}
