package com.edu.educational_system.repository;

import java.util.List;

import com.edu.educational_system.model.Course;

public interface CourseRepository {

	void addCourse(Course course) throws CourseRepositoryException;

	void saveCourse(Course course) throws CourseRepositoryException;

	Course getCourse(String name) throws CourseRepositoryException;

	List<Course> getAllCourses() throws CourseRepositoryException;

	List<String> getCourseCatalog() throws CourseRepositoryException;

	Course getObfuscatedCourse(String name) throws CourseRepositoryException;

}
