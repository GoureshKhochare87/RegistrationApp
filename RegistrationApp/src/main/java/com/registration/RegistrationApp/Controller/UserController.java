package com.registration.RegistrationApp.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.RegistrationApp.Dto.ResultModel;
import com.registration.RegistrationApp.Entity.Users;
import com.registration.RegistrationApp.Service.UserService;


@RestController
@RequestMapping(value="user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<ResultModel> getAllUsers() {
		logger.info("Getting all users");
		ResultModel resultModel = new ResultModel();
		try {
			List<Users> response = userService.getAllUsers();
			if (!response.isEmpty()) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage("No Records Found In Users....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("/saveUpdateUser")
	public ResponseEntity<ResultModel> createUpdateUser(@RequestBody Users user) {
		logger.info("Inside saveUpdate users");
		ResultModel resultModel = new ResultModel();
		try {
			List<String> response = userService.saveUser(user);
			if (response.contains("Success")) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
			} else {
				resultModel.setMessage("Failed");
				resultModel.setMessageList(response);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<ResultModel>(resultModel, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteUserById/{id}")
	public ResponseEntity<ResultModel> deleteById(@PathVariable("id") Integer id) {
		logger.info("Deleting user by id");
		ResultModel resultModel = new ResultModel();
		try {
			String response = userService.deleteUser(id);
			if (response.equals("Success")) {
				resultModel.setMessage(response);
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage(response);
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			resultModel.setMessage("Failed");
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<ResultModel> getByUserId(@PathVariable("userId") String userId) {
		logger.info("Get user by id");
		ResultModel resultModel = new ResultModel();
		try {
			Users response = userService.getById(userId);
			if (response!=null) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage("No Records Found In Users....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<ResultModel> getByName(@PathVariable("name") String name) {
		logger.info("Get user by name");
		ResultModel resultModel = new ResultModel();
		try {
			Users response = userService.getByName(name);
			if (response!=null) {
				resultModel.setData(response);
				resultModel.setMessage("Success");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			} else {
				resultModel.setMessage("No Records Found In Users....!");
				return new ResponseEntity<ResultModel>(resultModel, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<ResultModel>(resultModel, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
}