package com.aavs.postgrado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

	@GetMapping
	private String about(Model model) {
		model.addAttribute("pagActual", "about");
		return "about";
	}
}
