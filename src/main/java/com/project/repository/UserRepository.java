package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
	public boolean existsByEmail(String email);
	
	public User findByEmail(String email);
	
}
