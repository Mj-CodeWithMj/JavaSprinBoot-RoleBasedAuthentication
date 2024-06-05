package com.example.projectone.projectone.models;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String email;
	private String Password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	
	
	public User() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getEmail();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", Password=" + Password + ", roles="
				+ roles + "]";
	}
	
	
	

}
