package com.app.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.dao.UserDAO;
import com.app.demo.model.User;

@RestController
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	UserDAO userDAO;
	
	/* to save a User */
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userDAO.save(user);

	}


	/* to get all Users */
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDAO.findAll();
	}

    @GetMapping("/users/{id}")
    public ResponseEntity<User> GetUserById(@PathVariable(value="id") Long userid){
    	User us =userDAO.findOne(userid);
    	
    	if(us==null) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok().body(us);
    	
    }
    
    /*update userinfo by userid*/
    @PutMapping("/user/{userid}")
    public ResponseEntity<User> updateUser(@PathVariable(value="id") Long userid,@Valid @RequestBody User userDetails ){
         User us =userDAO.findOne(userid);
    	
    	if(us==null) {
    		return ResponseEntity.notFound().build();
    	}
    
    	us.setUsername(userDetails.getUsername());
    	us.setFirstname(userDetails.getFirstname());
    	us.setLastname(userDetails.getLastname());
    	
    	User updateUser=userDAO.save(us);
    	return ResponseEntity.ok().body(updateUser);
    }
     
    
    /*Delete a User*/
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value="id") Long userid){
    	User us =userDAO.findOne(userid);
    	if(us==null) {
    		return ResponseEntity.notFound().build();
    	}
        userDAO.delete(us);
        return ResponseEntity.ok().build();
        
    }
    
}