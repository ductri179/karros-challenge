package com.example.demo.dto;

import com.example.demo.domain.UserEntity;

public class UserDTO {

	private int id;
    private String username;
    private String email;

	public UserDTO(UserEntity user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
