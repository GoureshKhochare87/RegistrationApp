package com.registration.RegistrationApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registration.RegistrationApp.Repository.UserRepository;
import com.registration.RegistrationApp.Entity.Users;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<Users> getAllUsers(){
		return (List<Users>) userRepository.findAll();
	}

	public List<String> saveUser(Users user) {
		Users userData = userRepository.findByUserId(user.getUserId());
		List<String> response = new ArrayList<String>();
		if (userData != null) {
			user.setId(userData.getId());
			userData.setName(user.getName());
			userData.setPassword(user.getPassword());
			userData.setId(userData.getId());
			userData.setSurname(user.getSurname());
			userData.setCity(user.getCity());
			userData.setDob(user.getDob());
			userData.setDesignation(user.getDesignation());
			userData.setPinCode(user.getPinCode());
			userData.setContactNumber(user.getContactNumber());
			userData.setJoiningDate(user.getJoiningDate());
			userData.setEmail(user.getEmail());
			userData.setRole(user.getRole());
			userRepository.save(userData);
		} else {
			System.out.println("Inside Save API");
			userRepository.save(user);
			String userId = "NeoSoft_" + user.getId();
			user.setUserId(userId);
			userRepository.save(user);
		}
		response.add("Success");
		return response;
	}
	
	public String deleteUser(Integer id) {
		Optional<Users> user = userRepository.findById(id);
		if (user.isPresent()) {
			userRepository.deleteById(id);
			return "Success";
		}
		return "Failed";
	}
	
	public Users getById(String userId) {
		return userRepository.findByUserId(userId);
		
	}
	
	public Users getByName(String name) {//add
		return userRepository.findByName(name);
		
	}
}
