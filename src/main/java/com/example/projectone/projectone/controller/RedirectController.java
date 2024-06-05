package com.example.projectone.projectone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.projectone.projectone.models.User;
import com.example.projectone.projectone.repository.UserRepository;

@Controller
public class RedirectController {
	
	@Autowired
	UserRepository urepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/blog")
	public String blog() {
		return "blog";
	}
	@GetMapping("/cause")
	public String cause() {
		return "cause";
	}
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/registertion")
	public String registertion(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "registertion";
	}
	@PostMapping("/registertion")
	public String login(@ModelAttribute("user") User user) {
		System.out.println(user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		urepo.save(user);
		return "login";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
   
    @PreAuthorize("hasAuthority('USER')")
	@GetMapping("/user/user")
	public String user() {
		return "user";
	}
    @PreAuthorize("hasAuthority('STUDENT')")
	@GetMapping("/user/student")
	public String student() {
		return "student";
	}
    @PreAuthorize("hasAuthority('TEACHER')")
	@GetMapping("/user/teacher")
	public String teacher() {
		return "teacher";
	}

	@GetMapping("/extra")
	public String extra() {
		return "extra";
	}
	
	
}
