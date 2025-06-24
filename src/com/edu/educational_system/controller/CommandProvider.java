package com.edu.educational_system.controller;

import java.util.HashMap;
import java.util.Map;

import com.edu.educational_system.controller.impl.CreateCourseCommand;
import com.edu.educational_system.controller.impl.DeletePersonalDataCommand;
import com.edu.educational_system.controller.impl.GetCatalogCommand;
import com.edu.educational_system.controller.impl.GetCourseCommand;
import com.edu.educational_system.controller.impl.NoSuchCommand;
import com.edu.educational_system.controller.impl.RegisterPersonCommand;
import com.edu.educational_system.controller.impl.SaveCourseCommand;
import com.edu.educational_system.controller.impl.StartLessonCommand;

public class CommandProvider {

	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider(){
		repository.put(CommandName.CREATECOURSE, new CreateCourseCommand());
		repository.put(CommandName.GETCATALOG, new GetCatalogCommand());
		repository.put(CommandName.REGISTERPERSON, new RegisterPersonCommand());
		repository.put(CommandName.SAVECOURSE, new SaveCourseCommand());
		repository.put(CommandName.STARTLESSON, new StartLessonCommand());
		repository.put(CommandName.DELETEPERSONALDATA, new DeletePersonalDataCommand());
		repository.put(CommandName.GETCOURSE, new GetCourseCommand());
		repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());
		}

	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			command = repository.get(CommandName.WRONG_REQUEST);
		}
		return command;
	}
}
