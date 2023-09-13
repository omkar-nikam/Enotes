package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.entity.User;
import com.project.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class Mycontroller {

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session ) {
		
		boolean f=userService.existEmailCheck(user.getEmail());
		if(f) {
			session.setAttribute("msg","Email id already exist" );
		}
		
		else {
			User saveUser=userService.saveUser(user);
			if(saveUser!=null) {
				session.setAttribute("msg","Register Successfully" );
			}
			else {
				session.setAttribute("msg","Somethig went wrong" );
			}
		}
		
		
		
		return "redirect:/register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
	
	
	
	
	
	
}
