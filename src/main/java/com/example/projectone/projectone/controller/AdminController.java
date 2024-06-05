package com.example.projectone.projectone.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.projectone.projectone.models.Role;
import com.example.projectone.projectone.models.User;
import com.example.projectone.projectone.repository.RoleRepository;
import com.example.projectone.projectone.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user/admin")
public class AdminController {
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	RoleRepository rrepo;

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("")
	public String admin() {
		return "admin/admin";
	}
	
//	@PreAuthorize("hasAuthority('ADMIN')")
//	@GetMapping("/users")
//	public String users(Model model) {
//		List<User> user = urepo.findAll();
//		List<Role> role = rrepo.findAll();
//		user.set(1, "ROLE_ADMIN");
//		model.addAttribute("list", user);
//		model.addAttribute("lists", role);
//		//model.addAttribute("lists", rrepo.findAll());		
//		return "admin/users";
//	}
	/*@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/userrole")
	public String roleget(@ModelAttribute ("lists") Role role) {
		
		
		return "admin/users";
		}
	*/
	

//	private Object combineData(List<User> user, List<Role> role) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Long id, Model model) {
		Role role = rrepo.getRoleById(id);
		model.addAttribute("update", role);
		return "admin/updateUser";
	}
	
	
}
