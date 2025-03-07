package com.dollop.app.reponse;

import java.sql.Timestamp;
import java.util.List;

import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import com.dollop.app.entity.MapLocation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventResponse {
	private String eventId;
	@NotEmpty(message = "")
	private String title;
	private Timestamp eventDate;
	private MapLocation location;
	private String description;
	private String category;
	private String response;
	private String permission;
	private int availableSlots;
	private String CreatedBy;
	private List<String> customers;
}
