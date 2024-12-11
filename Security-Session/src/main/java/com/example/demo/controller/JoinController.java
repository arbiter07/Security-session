package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.JoinDTO;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class JoinController {

    private UserService userService;

	@GetMapping("/join")
    public String joinPage() {
        return "join";
    }
    
    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {
        return userService.joinProcess(joinDTO) ? "redirect:/login" : "join";
    }
}
