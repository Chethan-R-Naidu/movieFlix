package com.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.movieflix.entities.Movie;
import com.movieflix.entities.User;
import com.movieflix.services.MovieService;
import com.movieflix.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userserv;

	@Autowired
	MovieService moveserv;

	@PostMapping("Register") // Maps HTTP POST requests to /Register to this method from
	public String addUsers(@ModelAttribute User user) {// Method to handle POST requests to /Register, takes a User
														// object as a parameter
		// the response comes from the register.html
		boolean status = userserv.emailExists(user.getEmail());// Checks if the email already exists
		if (status == true) {// If email exists
			return "registerfailure";// Returns the view name "registerfailure"
		} else {
			userserv.addUsers(user);// Adds the user to the database
			return "registersuccess";// Returns the view name "registersuccess"
		}

	}

	@PostMapping("login") // Maps HTTP POST requests to /login to this method
	public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		// Method to handle POST requests to /login, takes email, password, and HttpSession as parameters
		User usr = userserv.getUser(email);// Retrieves the user based on the email

		if (usr == null) { // If user is not found
			System.out.println("User not found");
			return "loginfailure";// Returns the view name "loginfailure"
		}

		// Getting db password of the user using email from the database
		String dbPassword = usr.getPassword();

		// We are checking db-password with the user-entered password
		if (dbPassword.equals(password)) {
			// if both are the same it returns true
			session.setAttribute("email", email); // Set the actual email in the session
			if (email.equals("admin@gmail.com")) {// Checks if the email is the admin email
				return "adminHome";// Returns the view name "adminHome"
			} else {// If not admin email
				return "home";// Returns the view name "home"
			}
		} else {
			// else returns false
			System.out.println("Invalid credentials");
			return "loginfailure";// Returns the view name "loginfailure"
		}
	}

	@GetMapping("map-view") // Maps HTTP GET requests to /map-view to this method
	public String viewuser(Model model) { // Method to handle GET requests to /map-view, takes a Model object as a
											// parameter
		List<User> lu = userserv.viewUser(); // Retrieves the list of users from the database
		model.addAttribute("end_users", lu); // Adds the list of users to the model with the attribute name "end_users"
		return "viewallusers"; // Returns the view name "viewallusers"
	}

	@PostMapping("updateUser") // Maps HTTP POST requests to /updateUser to this method
	public String updateUsers(User user) { // Method to handle POST requests to /updateUser, takes a User object as a
											// parameter
		userserv.updateUsers(user); // Updates the user in the database
		return "userupdatesuccess"; // Returns the view name "userupdatesuccess"
	}

	@PostMapping("delete_user") // Maps HTTP POST requests to /delete_user to this method
	public String deleteUser(String email) { // Method to handle POST requests to /delete_user, takes email as a
												// parameter
		userserv.deleteUser(email); // Deletes the user from the database based on the email
		return "deleteduser"; // Returns the view name "deleteduser"
	}

	@GetMapping("exploremovies") // Maps HTTP GET requests to /exploremovies to this method
	public String exploreMovie(Model model, HttpSession session) { // Method to handle GET requests to /exploremovies,
																	// takes a Model object and HttpSession as
																	// parameters
		String email = (String) session.getAttribute("email"); // Retrieves the email attribute from the session
		User user = userserv.getUser(email); // Retrieves the user based on the email
		if (user.isPremium() == true) { // Checks if the user is a premium user
			List<Movie> lm = moveserv.viewMovie(); // Retrieves the list of movies from the database
			model.addAttribute("movie", lm); // Adds the list of movies to the model with the attribute name "movie"
			return "viewmovieuser"; // Returns the view name "viewmovieuser"
		} else { // If user is not a premium user
			return "payment"; // Returns the view name "payment"
		}
	}

	@GetMapping("logout") // Maps HTTP GET requests to /logout to this method
	public String logout(HttpSession session) { // Method to handle GET requests to /logout, takes HttpSession as a
												// parameter
		session.invalidate(); // Invalidates the session
		return "Login"; // Returns the view name "Login"
	}
}
