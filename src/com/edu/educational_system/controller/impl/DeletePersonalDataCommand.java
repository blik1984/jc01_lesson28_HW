package com.edu.educational_system.controller.impl;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.model.Course;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceException;
import com.edu.educational_system.service.CourseServiceProvider;

public class DeletePersonalDataCommand implements Command {

	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService courseService = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {
		String[] params;
		params = request.split("\n");
		Course newCourse = null;
		String response = null;
		try {
			newCourse = courseService.getCourseWithObfuscatedPersonalData(params[1]);
			response = newCourse.toString();
		} catch (CourseServiceException e) {
			response = ("I couldn't do it, I have paws");
			}
		return response;
	}
}
