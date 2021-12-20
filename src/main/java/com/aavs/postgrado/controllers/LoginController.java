package com.aavs.postgrado.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping
	private String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if(error == null) {
			model.addAttribute("error", false);
		}else if (error.equalsIgnoreCase("true")){
			model.addAttribute("error", true);
		}
		return "login";
	}
}
