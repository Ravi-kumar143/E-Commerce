package com.ecommerce.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import com.ecommerce.util.JwtUtil;
import com.ecommmerce.dto.UserLoginDto;
import com.ecommmerce.dto.UserRegisterDto;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	
	@PostMapping("register")
	public User register(@RequestBody UserRegisterDto registerDto ) {
		
		
		return userService.register(registerDto.getName(), registerDto.getEmail(), registerDto.getPassword());
	}
	
	@PostMapping("/login")
	public Map<String, String>login(@RequestBody UserLoginDto dto) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		String token = jwtUtil.generateToken(dto.getEmail());		
		return Map.of("token",token);
	}
	
	
}
