package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.TempUser;

public interface ITempUserRepository extends JpaRepository<TempUser, String> {

}
