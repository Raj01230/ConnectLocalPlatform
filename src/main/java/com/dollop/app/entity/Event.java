package com.dollop.app.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String eventId;
	private String title; 
	private Timestamp endDate; 
	private Timestamp startDate;
	private String location;
	private String description;
	private String category; 
	private String responseForCancel;
	private Boolean permission;
	private int availableSlots;
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id_fk")
	private User createdBy; 
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<User> customers;
}
