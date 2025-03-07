package com.dollop.app.service;


import com.dollop.app.payload.EventRequest;
import com.dollop.app.reponse.ApiResponse;

public interface IEventService {
	public ApiResponse createEvent(EventRequest event);
	public ApiResponse joinEvent(EventRequest event);
	public ApiResponse updateEvent(EventRequest event);
	public ApiResponse cancelEvent(String id);
	public ApiResponse showAllEvents();
	public ApiResponse showAvailableEvents();
	public ApiResponse showExpiredEvents();
	public ApiResponse availableSlotes(String id);
	public ApiResponse statusEvent(String id);
}
