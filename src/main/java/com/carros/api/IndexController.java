package com.carros.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	/*
	@GetMapping
	public String hello() {
		return "Hello Spring Boot";
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "Teste Spring Boot";
	}
	*/
	
	/*
	@GetMapping("")
	public String get() {
		return "Get Spring Boot";
	}
	
	@PostMapping("")
	public String post() {
		return "Post Spring Boot";
	}
	
	@PutMapping("")
	public String put() {
		return "Put Spring Boot";
	}
	
	@DeleteMapping("")
	public String delete() {
		return "Delete Spring Boot";
	}
	*/
	
	@GetMapping("login")
	public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return "login: " + login + ", senha: " + senha;
	}
}
