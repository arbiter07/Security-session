package com.example.demo.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class MainController {

	@GetMapping(value = {"", "/"})
	public String mainPage(Model model) {
		
		
		log.info("************************************");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		String name = authentication.getName();
		log.info("name {]" , name);
		log.info("role {]" , role);
		log.info("************************************");
		
		model.addAttribute("name", "hello mustache! my name is " + name);
		model.addAttribute("role", "my role is " + role);
		return "main";
	}

}
