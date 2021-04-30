package com.registration.RegistrationApp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.registration.RegistrationApp.Entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

	public Users findByUserId(String userId);
	public Users findByName(String name);
}
