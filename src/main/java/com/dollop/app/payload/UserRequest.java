package com.dollop.app.payload;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class UserRequest {
//	@NotNull
	@NotEmpty(message = "Name cannot be empty. Please provide a name.")
	private String name;
	@NotEmpty(message = "Email cannot be empty. Please provide a Email.")
	@Email(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z]+\\.[A-Za-z]{2,}$" ,message="Invalid email. Use format: local@domain.com")
	private String email;
	@NotEmpty(message = "Password cannot be empty. Please provide a password.")
	@Pattern(regexp = "^[A-Za-z\\d@$!%*?&]{8,}$",message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&).")
	private String password;
}
