package io.jdbc.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.jdbc.app.entity.Course;
import io.jdbc.app.entity.Lession;

@Service
public class CourseService {
	
	private Course course; 
	private void populateCourse() {
		course = new Course();
		course.setId(1);
		course.setCode("course_code");
		List<Lession> lessions = new ArrayList<>();
		for(int i=0;i<10;i++) {
			lessions.add(new Lession("name"+i,i));
		}
		course.setLessions(lessions);
	}
	
	
	public Course getCourse() {
		populateCourse();
		return course;
	}

}
