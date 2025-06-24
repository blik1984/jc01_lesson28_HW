package com.edu.educational_system.controller.impl;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.model.Course;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceException;
import com.edu.educational_system.service.CourseServiceProvider;
import com.edu.educational_system.utils.Parser;

public class SaveCourseCommand implements Command {

	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService courseService = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {
		
		String response = null;
		Course course = Parser.parseCourse(request);
		try {
			courseService.saveCourse(course);
			response = "Course saved";

		} catch (CourseServiceException e) {
			response = "Course not saved";
		}
		return response;
	}

}
