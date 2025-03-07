package com.dollop.app.payload;

import java.sql.Timestamp;
import java.util.List;

import com.dollop.app.entity.Event;
import com.dollop.app.entity.MapLocation;
import com.dollop.app.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	private String eventId;
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
