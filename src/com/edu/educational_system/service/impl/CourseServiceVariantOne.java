package com.edu.educational_system.service.impl;

import java.util.List;

import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.repository.CourseRepository;
import com.edu.educational_system.repository.CourseRepositoryProvider;
import com.edu.educational_system.repository.CourseRepositoryException;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceException;

public class CourseServiceVariantOne implements CourseService {
	private final CourseRepository courseRepository;

	{
		CourseRepositoryProvider provider = CourseRepositoryProvider.getInstance();
		courseRepository = provider.getCourseRepository();
	}

	public CourseServiceVariantOne() {
	}

	public void createCourse(Course course) throws CourseServiceException {
		try {
			courseRepository.addCourse(course);
		} catch (CourseRepositoryException e) {
			throw new CourseServiceException(e);
		}
	}

	public boolean enrollPerson(Course course, Person person) {
		return course.addParticipant(person);
	}

	public void conductLesson(Course course) {
		course.conductLesson();
	}

	public List<Person> getParticipants(Course course) {
		return course.getParticipants();
	}

	public double getAverageGrade(Course course) {
		return course.calculateAverageGrade();
	}

	public List<String> getCatalog() throws CourseServiceException {
		try {
			return courseRepository.getCourseCatalog();
		} catch (CourseRepositoryException e) {
			throw new CourseServiceException(e);
		}
	}

	public void saveCourse(Course currentCourse) throws CourseServiceException {
		try {
			courseRepository.saveCourse(currentCourse);
		} catch (CourseRepositoryException e) {
			throw new CourseServiceException(e);
		}
	}

	public Course getCourse(String name) throws CourseServiceException {
		try {
			return courseRepository.getCourse(name);
		} catch (CourseRepositoryException e) {
			throw new CourseServiceException(e);
		}
	}

	public Course getCourseWithObfuscatedPersonalData(String name) throws CourseServiceException {
		try {
			return courseRepository.getObfuscatedCourse(name);
		} catch (CourseRepositoryException e) {
			throw new CourseServiceException(e);
		}
	}
}
