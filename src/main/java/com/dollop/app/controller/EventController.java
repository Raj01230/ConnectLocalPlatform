package com.dollop.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.payload.EventRequest;
import com.dollop.app.reponse.ApiResponse;
import com.dollop.app.service.IEventService;

@RequestMapping("/event")
@RestController
public class EventController {
	@Autowired
	IEventService eventService;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createEvent(@RequestBody EventRequest event) {
		System.out.println("event   " + event);
		return new ResponseEntity<ApiResponse>(eventService.createEvent(event), HttpStatus.CREATED);
	}
}
