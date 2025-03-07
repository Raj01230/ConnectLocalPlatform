package com.dollop.app.entity;

import java.time.LocalDateTime;

import com.dollop.app.enums.OtpType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpStorage {
	@Id
	private String email;
	private String otp;
	private LocalDateTime expiryTime;
	@Enumerated(EnumType.STRING)
	private OtpType type;
}
