package com.edu.educational_system.service;

import com.edu.educational_system.service.impl.CourseServiceVariantOne;

public class CourseServiceProvider {
	
	private static final CourseServiceProvider instance = new CourseServiceProvider();
	private final CourseService courseService = new CourseServiceVariantOne();
	
	private CourseServiceProvider() {}
	
	public static CourseServiceProvider getInstance() {
		return instance;
	}

	public CourseService getCourseService() {
		return courseService;
	}
	

}
