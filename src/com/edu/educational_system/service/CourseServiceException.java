package com.edu.educational_system.service;

public class CourseServiceException extends Exception{

	private static final long serialVersionUID = -2709679683670603732L;
	
	public CourseServiceException() {
		super();
	}

	public CourseServiceException(String message) {
		super(message);
	}

	public CourseServiceException(Exception e) {
		super(e);
	}

	public CourseServiceException(String message, Exception e) {
		super(message, e);
	}

}
