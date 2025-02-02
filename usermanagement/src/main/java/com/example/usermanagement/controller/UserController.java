package com.example.usermanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	// Create User
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User saveUser = userService.saveUser(user);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
		// return ResponseEntity.ok(saveUser); -- This also works
	}
	
	
	// Retrive User
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id){
		Optional<User> getUser= userService.getUserById(id);
		return getUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	//Update
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
		User updatedUser= userService.updateUser(id, userDetails);
		return updatedUser != null ? ResponseEntity.ok(updatedUser): ResponseEntity.notFound().build();
		
	}
	
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserById(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	

}
