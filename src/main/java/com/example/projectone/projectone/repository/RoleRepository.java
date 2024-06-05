package com.example.projectone.projectone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projectone.projectone.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role getRoleById(Long id);

}
