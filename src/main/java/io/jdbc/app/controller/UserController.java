package io.jdbc.app.controller;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jdbc.app.entity.User;
import io.jdbc.app.response.BaseResponse;
import io.jdbc.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService mUserService;
	
	
	@PostMapping("/insert")
	public User insertUser(@RequestBody User user) {
		long id = -1;
		try {
			id = mUserService.insertUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setId((int)id);
		return user;
	}
	
	@GetMapping("/all_users")
	public List<User> getAllUsers(){
		return mUserService.getAllUsers();
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/valid_user/{email}")
	public List<User> getUser(@PathVariable("email") String email) {
		List<User> users = new ArrayList<User>();
		try {
			 users = mUserService.getUser(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return users;
		}
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/remove/{id}")
	public BaseResponse removeUser(@PathVariable("id") int id) {
		BaseResponse response = new BaseResponse();
		response.setMsg("Failed!!");
		response.setSuccess(false);
		try {
			response =  mUserService.removeUser(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			return response;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/hello")
	public String test(@RequestParam("NAME") String name) {
		return "Hello "+name;
	}
}
