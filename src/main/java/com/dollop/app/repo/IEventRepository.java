package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.Event;

public interface IEventRepository extends JpaRepository<Event, String> {

}
