package com.project.service;


import com.project.entity.User;


public interface UserService {
	
	public User saveUser(User user);
	
	public boolean existEmailCheck(String email);
}
