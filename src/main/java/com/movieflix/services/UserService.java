package com.movieflix.services;

import java.util.List;

import com.movieflix.entities.User;

public interface UserService {
	
public String addUsers(User user);
	
	public boolean emailExists(String email);
	
//	public boolean checkUser(String email,String password);
	
	public List<User> viewUser();
	
	public void updateUsers(User user);
	
	public String deleteUser(String email);

	public User getUser(String email);
}