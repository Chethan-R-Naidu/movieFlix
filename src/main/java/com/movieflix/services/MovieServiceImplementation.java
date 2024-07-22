package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.Movie;
import com.movieflix.repositories.MovieRepository;

@Service
public class MovieServiceImplementation implements MovieService{

	@Autowired
	MovieRepository movserv;
	
	@Override
	public String addMovie(Movie mov) {
		movserv.save(mov);
		return "movie is added";
	}

	@Override
	public List<Movie> viewMovie() {
		List<Movie> lm = movserv.findAll();
		return lm;
	}

}
