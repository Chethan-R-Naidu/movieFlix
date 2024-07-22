package com.movieflix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {// Declares the NavController class
	
	
	@GetMapping("map-register")// Maps HTTP GET requests to /map-register to this method from index.html
	public String userRegister() {// Method to handle requests to /map-register
		return "register";// Returns the view name "register.html"
	}
	
	@GetMapping("map-login")// Maps HTTP GET requests to /map-login to this method
	public String userLogin() {// Method to handle requests to /map-login
		return "Login";// Returns the view name "Login"
	}
	
	@GetMapping("map-addmovie")// Maps HTTP GET requests to /map-addmovie to this method
	public String userMovie() {// Method to handle requests to /map-addmovie
		return "addmovie";// Returns the view name "addmovie"
	}
	
	@GetMapping("map-updateuser")// Maps HTTP GET requests to /map-updateuser to this method
	public String updateUser() {// Method to handle requests to /map-updateuser
		return "UpdateUser";// Returns the view name "UpdateUser"
	}
	
	@GetMapping("map-deleteuser")// Maps HTTP GET requests to /map-deleteuser to this method
	public String DeleteUser() {// Method to handle requests to /map-deleteuser
		return "deleteUser";// Returns the view name "deleteUser"
	}
	
	@GetMapping("Admin Home")// Maps HTTP GET requests to /Admin Home to this method
	public String adminHomePage() {// Method to handle requests to /Admin Home
		return "adminHome";// Returns the view name "adminHome"
	}
	


}
