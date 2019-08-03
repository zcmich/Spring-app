package com.app.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.demo.model.User;
import com.app.demo.repository.UserRepository;

public class UserDAO {
  
	@Autowired(required=true)
	UserRepository userRepository;
	
	/*to save a user */
	 public User save(User user) {
		 return userRepository.save(user);
	 }
	
	/*to search for a user */
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	/*get a user by id */
	 public User findOne(Long userid) {
		 return userRepository.getOne(userid);
	 }
	
	
	
	/*delete a user  */
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	
	
}
