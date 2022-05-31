package com.hb.springpersistence.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hb.springpersistence.repositories.TagProxy;

@Controller
public class TempController {

	@Autowired
	private TagProxy tagProxy;
	
	@GetMapping("/temp")
	public String getTags(Model model) {
		
		model.addAttribute("tags", tagProxy.getTags());
		
		return "tags";
	}
	
}
