package com.edu.educational_system.repository;

import com.edu.educational_system.repository.impl.FileCourseRepository;

public class CourseRepositoryProvider {

	private static final CourseRepositoryProvider instance = new CourseRepositoryProvider();
	private final CourseRepository courseRepository = new FileCourseRepository();

	private CourseRepositoryProvider() {
	}

	public static CourseRepositoryProvider getInstance() {
		return instance;
	}

	public CourseRepository getCourseRepository() {
		return courseRepository;
	}

}
