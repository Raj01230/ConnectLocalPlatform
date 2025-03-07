package com.dollop.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.OtpStorage;

public interface OtpRepository extends JpaRepository<OtpStorage, String> {

	OtpStorage findByEmail(String email);


}
