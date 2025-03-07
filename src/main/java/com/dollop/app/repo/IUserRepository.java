package com.dollop.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.entity.User;

public interface IUserRepository extends JpaRepository<User, String> {
	User getByEmailAndPassword(String email, String password);

	User findByName(String username);

	User findByEmail(String email);

	Boolean existsByEmail(String email);

	boolean existsByEmailAndPassword(String email, String password);

	List<User> findByRole(String role);

//	@Query("SELECT u FROM User u WHERE "
//			+ "(u.name LIKE %:search% OR u.email LIKE %:search% OR u.role LIKE %:search% OR u.createdAt LIKE %:search%)")
//	List<User> searchByAnyField(@Param("search") String search);
	
	List<User> findByStatus(Boolean status);
}
