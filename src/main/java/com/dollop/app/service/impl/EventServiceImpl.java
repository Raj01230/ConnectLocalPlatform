package com.dollop.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dollop.app.entity.Event;
import com.dollop.app.entity.User;
import com.dollop.app.exception.UserNotFoundException;
import com.dollop.app.payload.EventRequest;
import com.dollop.app.repo.IEventRepository;
import com.dollop.app.repo.IUserRepository;
import com.dollop.app.reponse.ApiResponse;
import com.dollop.app.service.IEventService;
@Service
public class EventServiceImpl implements IEventService {
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IEventRepository eventRepo;

	@Override
	public ApiResponse createEvent(EventRequest event) {
		Event e = Event.builder().title(event.getTitle()).category(event.getCategory())
				.customers(getUsersByIds(event.getCustomers())).endDate(event.getEndDate()).startDate(event.getStartDate())
				.description(event.getDescription()).isDeleted(false).status(true).permission(false)
				.location(event.getLocation()).createdBy(userRepo.findById(event.getCreatedBy()).get()).availableSlots(event.getAvailableSlots()).build();
		eventRepo.save(e);

		return ApiResponse.builder().message("Create Event SuccessFully ").response(HttpStatus.CREATED).build();
	}

	private List<User> getUsersByIds(List<String> customers) {
		List<User> ul = new ArrayList<>();
		for (String user : customers) {
			if (userRepo.existsById(user)) {
				ul.add(userRepo.findById(user).get());
			} else
				throw new UserNotFoundException("User Not Found: " + user);
		}
		return ul;
	} 

	@Override
	public ApiResponse joinEvent(EventRequest event) {
		return null;
	}

	@Override
	public ApiResponse updateEvent(EventRequest event) {
		return null;
	}

	@Override
	public ApiResponse cancelEvent(String id) {
		return null;
	}

	@Override
	public ApiResponse showAllEvents() {
		return null;
	}

	@Override
	public ApiResponse showAvailableEvents() {
		return null;
	}

	@Override
	public ApiResponse showExpiredEvents() {
		return null;
	}

	@Override
	public ApiResponse availableSlotes(String id) {
		return null;
	}

	@Override
	public ApiResponse changeStatusEvent(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse givePermissionEvent(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResponse getPermitedEvent() {
		return null;
	}

	@Override
	public ApiResponse getNotPermitedEvent() {
		return null;
	}

	@Override
	public ApiResponse getEventByDateRange(String firstDate, String lastDate) {
		return null;
	}

}
