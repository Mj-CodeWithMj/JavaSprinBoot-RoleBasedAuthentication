package com.example.projectone.projectone.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectone.projectone.models.Role;
import com.example.projectone.projectone.models.User;
import com.example.projectone.projectone.repository.RoleRepository;
import com.example.projectone.projectone.repository.UserRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class HomeController {
	
	@Autowired
	UserRepository urepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository rolerepo;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return urepo.save(user);
	}
		
//	@PostMapping("/createUser")
//	public User createUsera(@RequestBody User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		return urepo.save(user);
//	this is for posting first time data without any authentication
//	}
	
//	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/getUser")
	public List<User> getUser() {
		List<User> user = urepo.findAll();
		return user;
	}
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/getUserById/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		Optional<User> user = urepo.findById(id);
		return user;
		
	}
	
	@PutMapping("/updateUser/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return urepo.save(user);
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) {
		urepo.deleteById(id);
	}
	
	
// role curd opretion
	
	@PostMapping("/createRole")
	public Role createRole(@RequestBody Role role) {
		return rolerepo.save(role);
	}
	@GetMapping("/getRole")
	public List<Role> getRole() {
		List<Role> role = rolerepo.findAll();
		return role;
	}
	@GetMapping("/getRoleById/{id}")
	public Optional<Role> getRoleById(@PathVariable Long id) {
		Optional<Role> role = rolerepo.findById(id);
		return role;
	}
	

}
