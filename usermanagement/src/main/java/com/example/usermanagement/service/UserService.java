package com.example.usermanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Long id){
		return userRepository.findById(id);
	}
	
	@Transactional
	public void deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e){
			System.out.println("User Not Found");
			
		}
	}
	
	@Transactional
	public User updateUser(Long id, User userDetails) {
		if (userRepository.existsById(id)) {
			userDetails.setId(id);
			return userRepository.save(userDetails);
		}
		else {
		return null;
		}    
	}

}
