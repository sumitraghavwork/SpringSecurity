package com.masai.security.payload.response;

import com.masai.security.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

	private Long id;
	private String username;
	private String email;
	private String role;

	public UserInfoResponse(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole().name();
	}

}
