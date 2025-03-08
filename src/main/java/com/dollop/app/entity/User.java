package com.dollop.app.entity;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Builder
@SuperBuilder
public class User extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String name;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "location_id_fk")
	private String location;
	@Column(unique = true)
	private String email;
	private String password;
	private String role;
	
}
