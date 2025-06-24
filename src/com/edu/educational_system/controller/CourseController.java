package com.edu.educational_system.controller;

public class CourseController {

	private final char delimiter = '\n';
	private final CommandProvider commandProvider = new CommandProvider();

	public CourseController() {}

	public String doAction(String request) {

		String commandName;
		Command executionCommand;
		commandName = request.substring(0, request.indexOf(delimiter));
		executionCommand = commandProvider.getCommand(commandName.toUpperCase());

		String response;
		response = executionCommand.execute(request);
		return response;
	}

}
