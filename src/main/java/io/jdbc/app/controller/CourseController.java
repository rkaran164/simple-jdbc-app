package io.jdbc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jdbc.app.entity.Course;
import io.jdbc.app.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {
	
	@Autowired 
	private CourseService mCourseService;
	
	@RequestMapping("/list")
	public Course getCourse() {
		return mCourseService.getCourse(); 
	}
}
