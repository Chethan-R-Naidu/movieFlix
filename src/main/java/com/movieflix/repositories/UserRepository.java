package com.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	//This method is used find the user present with same email
		//This is developer(me) created method
		public User findByEmail(String email);
		
		public boolean existsByEmail(String email);
		
		public String deleteByEmail(String email); 
}
