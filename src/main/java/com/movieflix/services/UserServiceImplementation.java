package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entities.User;
import com.movieflix.repositories.UserRepository;


@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userrepo;
	
	@Override
	public String addUsers(User user) {
		userrepo.save(user);
		return "User added";
	}

	@Override
	public boolean emailExists(String email) {
		if(userrepo.findByEmail(email)==null) {
			return false;
		}
		else {
			return true;
		}
		  
	}

//	@Override
//	public boolean checkUser(String email, String password) {
//		User usr = userrepo.findByEmail(email);
////		getting db password of the user using email from database 
//		String db_password = usr.getPassword();
////		We are checking db-password with user entered password
//		if(db_password.equals(password)) {
////			if both are same it returns true
//			return true;
//		}
//		else {
////		else returns false
//			return false;
//		}
//	}

	@Override
	public List<User> viewUser() {
		List<User> ls = userrepo.findAll();
		return ls;
	}

	@Override
    public void updateUsers(User user) {
        User existingUser = userrepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setPhoneNo(user.getPhoneNo());
            existingUser.setPassword(user.getPassword());
            existingUser.setGender(user.getGender());
            existingUser.setAddress(user.getAddress());
            existingUser.setPremium(user.isPremium());
            userrepo.save(existingUser);
        }
    }

	@Override
	public String deleteUser(String email) {
        User user = userrepo.findByEmail(email);
        if (user != null) {
        	userrepo.delete(user);
        }
        return "user is deleted";
	}

	@Override
	public User getUser(String email) {
		User user = userrepo.findByEmail(email);
		return user;
	}


	

	

}