package com.edu.educational_system.controller.impl;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceProvider;
import com.edu.educational_system.utils.Parser;

public class RegisterPersonCommand implements Command {

	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService courseService = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {
		String response = "Error. User not added";
		String[] params = request.split("\n");

		Course course = Parser.parseCourse(params[1]);
		Person person = Parser.parsePerson(params[2]);
		
		boolean flag = courseService.enrollPerson(course, person);

		if (flag) {
			response = "User added";
		}

		return response;
	}

}
