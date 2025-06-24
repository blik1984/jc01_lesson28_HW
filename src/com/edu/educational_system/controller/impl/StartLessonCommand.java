package com.edu.educational_system.controller.impl;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.model.Course;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceProvider;
import com.edu.educational_system.utils.Parser;

public class StartLessonCommand implements Command {

	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService courseService = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {

		String[] params = request.split("\n");
		Course course = Parser.parseCourse(params[1]);
		courseService.conductLesson(course);
		String response = "Lesson is started";
		return response;
	}

}
