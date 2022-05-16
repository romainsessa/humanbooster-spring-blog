package com.hb.springpersistence.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.dto.PostDetailsDTO;
import com.hb.springpersistence.service.IPostService;

@Controller
@RequestMapping(value = "post")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@GetMapping("{id}")
	public String getPost(@PathVariable(name = "id") Integer id, Model model) {
		PostDTO post = postService.getPostDTO(id);
		model.addAttribute("post", post);
		return "post";		
	}
	
	@GetMapping
	public String getPosts(Model model) { 
		List<PostDTO> posts = postService.getPostDTOs();
		model.addAttribute("posts", posts);
		return "posts";
	}
	
	@GetMapping("/new") // http://localhost:9000/post/new
	public String getForm(Model model) {
		PostDTO post = new PostDTO();
		post.setDetails(new PostDetailsDTO());
		model.addAttribute("post", post);
		return "newPost";
	}
	
	@PostMapping
	public ModelAndView createPost(@ModelAttribute PostDTO post) {
		post.getDetails().setCreatedOn(new Date());
		postService.save(post);		
		return new ModelAndView("redirect:/post");
	}
	
	@GetMapping("{id}/delete")
	public ModelAndView deletePost(@PathVariable(name = "id") Integer id) {
		postService.delete(id);
		return new ModelAndView("redirect:/post");
	}
	
}
