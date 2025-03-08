package com.dollop.app.payload;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventRequest {
	@NotEmpty(message = "Title cannot be empty. Please provide a Title.")
	private String title; 
	@NotEmpty(message = "startDate cannot be empty. Please provide a eventDate.")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}( \\d{2}:\\d{2}:\\d{2})?$", 
    message = "Invalid date format. Please use 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm:ss'.")
	private Timestamp startDate;
	@NotEmpty(message = "endDate cannot be empty. Please provide a eventDate.")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}( \\d{2}:\\d{2}:\\d{2})?$", 
    message = "Invalid date format. Please use 'yyyy-MM-dd' or 'yyyy-MM-dd HH:mm:ss'.")
	private Timestamp endDate;
	@NotEmpty(message = "location cannot be empty. Please provide a location.")
	private String location;
	@NotEmpty(message = "description cannot be empty. Please provide a description.")
	private String description;
	@NotEmpty(message = "category cannot be empty. Please provide a category.")
	private String category;
	@NotEmpty(message = "permission cannot be empty. Please provide a permission.")
	private String permission;
	@NotEmpty(message = "Creater cannot be empty. Please provide a Creater.")
	private String createdBy; 
	@NotEmpty(message = "availableSlots cannot be empty. Please provide a availableSlots.")
	@Type(Integer.class)
	private int availableSlots;
	
	@NotEmpty(message = "customers cannot be empty. Please provide a customers.")
	private List<String> customers;
}
