package com.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.movieflix.entities.Movie;
import com.movieflix.services.MovieService;


@Controller
public class MovieController { // Declares the MovieController class
	
	@Autowired // Automatically injects an instance of MovieService into this controller
	MovieService movserv;// Declares a field for the MovieService
	
	@PostMapping("addmovies")// Maps HTTP POST requests to /addmovies to this method
	public String addMovie(@ModelAttribute Movie mov) {
		// Method to handle POST requests to /addmovies, takes a Movie object as a parameter
		movserv.addMovie(mov);// Calls the addMovie method of MovieService to add the movie
		return "addmoviesuccess";// Returns the view name "addmoviesuccess"
	}
	
	@GetMapping("viewmovies")// Maps HTTP GET requests to /viewmovies to this method
	public String viewMovie(Model model) {// Method to handle GET requests to /viewmovies, takes a Model object as a parameter
		List<Movie> lm = movserv.viewMovie();// Calls the viewMovie method of MovieService to get a list of movies
		model.addAttribute("movie", lm);// Adds the list of movies to the model with the attribute name "movie"
		return "viewmovie";// Returns the view name "viewmovie"
	}
	
	@GetMapping("view-movie-user")// Maps HTTP GET requests to /view-movie-user to this method
	public String viewMovieUser(Model model) {// Method to handle GET requests to /view-movie-user, takes a Model object as a parameter
		List<Movie> lm = movserv.viewMovie();// Calls the viewMovie method of MovieService to get a list of movies
		model.addAttribute("movie", lm);// Adds the list of movies to the model with the attribute name "movie"
		return "viewmovieuser";// Returns the view name "viewmovieuser"
	}

}
