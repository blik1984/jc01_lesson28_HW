package com.edu.educational_system.service;

import java.util.List;

import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;

public interface CourseService {

	void createCourse(Course course) throws CourseServiceException;

	boolean enrollPerson(Course course, Person person);
	
	void conductLesson(Course course)  ;
	
	List<String> getCatalog() throws CourseServiceException;
	
	void saveCourse(Course currentCourse) throws CourseServiceException;
	
	List<Person> getParticipants(Course course) throws CourseServiceException;

	double getAverageGrade(Course course) throws CourseServiceException;
	
	Course getCourseWithObfuscatedPersonalData(String name) throws CourseServiceException;
	
	Course getCourse(String name) throws CourseServiceException;


}
  