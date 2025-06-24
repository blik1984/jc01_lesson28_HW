package com.edu.educational_system.controller.impl;

import java.util.List;

import com.edu.educational_system.controller.Command;
import com.edu.educational_system.service.CourseService;
import com.edu.educational_system.service.CourseServiceException;
import com.edu.educational_system.service.CourseServiceProvider;

public class GetCatalogCommand implements Command {

	private final CourseServiceProvider courseServiceProvider = CourseServiceProvider.getInstance();
	private final CourseService courseService = courseServiceProvider.getCourseService();

	@Override
	public String execute(String request) {

		String response = "";

		List<String> data;
		try {
			data = courseService.getCatalog();
			for (String s : data) {
				response = response + s + "\n";
			}
		} catch (CourseServiceException e) {
			response = ("I couldn't do it, I have paws");
		}

		return response;

	}

}
