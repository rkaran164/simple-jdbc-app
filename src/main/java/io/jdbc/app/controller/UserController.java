package io.jdbc.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jdbc.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService mUserService;
	
	@RequestMapping("/insert/{name}")
	public String insertUser(@PathVariable("name") String name) {
		mUserService.insertUser(name);
		return name;
	}
	
	@RequestMapping(method=RequestMethod.POST,value = "/hello")
	public String test(@RequestParam("NAME") String name) {
		return "Hello "+name;
	}
}
