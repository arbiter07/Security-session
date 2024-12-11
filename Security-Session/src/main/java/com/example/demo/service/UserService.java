package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JoinDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private final UserMapper mapper;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User getUser(String username) {
		return mapper.findByUserName(username);
		
	}
	public List<User> getUsers() {
		return mapper.findAll();
	}
	public boolean joinProcess(JoinDTO joinDTO) {
		if(getUser(joinDTO.getUsername()) != null) {
			return false;
		}
	
		User user = new User();
		user.setUsername(joinDTO.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
		user.setRole("ROLE_USER");
		mapper.save(user);
		return true;
	}
}
