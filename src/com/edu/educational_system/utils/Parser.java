package com.edu.educational_system.utils;

import java.util.ArrayList;
import java.util.List;

import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.model.Student;
import com.edu.educational_system.model.Teacher;

public class Parser {

	public static Course parseCourseWithObfuscationPersonalData(String data) {

		String[] dataCourse = data.split("\\|");
		String nameCourse = dataCourse[0].split("name=")[1];
		Course curs = new Course(nameCourse);
		String[] users = dataCourse[1].split("/");
		String[] users2 = dataCourse[2].split("/");

		for (String s2 : users) {
			Person p = parsePersonWithObfuscationPersonalData(s2);

			if (p != null) {
				curs.addParticipant(p);
			}
		}
		for (String s : users2) {
			Person p = parsePerson(s);
			if (p != null) {
				curs.addParticipant(p);
			}
		}
		return curs;

	}

	private static Person parsePersonWithObfuscationPersonalData(String s) {
		String[] person = s.split(", | ");
		if (person[0].equalsIgnoreCase("/Student") || person[0].equalsIgnoreCase("Student")) {
			String group = person[1].split("group=")[1];
			double grade = Double.parseDouble(person[2].split("averageGrade=")[1]);
			String name = person[3].split("name=")[1];
			String email = person[4].split("email=")[1];
			Person balbes = new Student(getObfuscatedName(name, email), "email", group, grade);
			return balbes;
		} else if (person[0].equalsIgnoreCase("/Teacher") || person[0].equalsIgnoreCase("Teacher")) {
			String subject = person[1].split("subject=")[1];
			String name = person[2].split("name=")[1];
			String email = person[3].split("email=")[1];
			Person balbes = new Teacher(name, email, subject);
			return balbes;
		} else if (person[0].equalsIgnoreCase("/Administrator") || person[0].equalsIgnoreCase("Administrator")) {
			String department = person[1].split("department=")[1];
			String name = person[2].split("name=")[1];
			String email = person[3].split("email=")[1];
			Person balbes = new Teacher(name, email, department);
			return balbes;
		} else {
			return null;
		}
	}

	private static String getObfuscatedName(String s1, String s2) {

		String obfuscatedName = "" + s1.hashCode() + s2.hashCode();
		return obfuscatedName;

	}

	public static Course parseCourse(String data) {

		String[] dataCourse = data.split("\\|");
		String nameCourse = dataCourse[0].split("name=")[1];
		Course curs = new Course(nameCourse);
		String[] users = dataCourse[1].split("/");
		String[] users2 = dataCourse[2].split("/");
		for (String s : users) {
			Person p = parsePerson(s);
			if (p != null) {
				curs.addParticipant(p);
			}
		}
		for (String s : users2) {
			Person p = parsePerson(s);
			if (p != null) {
				curs.addParticipant(p);
			}
		}
		return curs;
	}

	public static Person parsePerson(String s) {
		String[] person = s.split(", | \\[");

		if (person[0].equalsIgnoreCase("/Student") || person[0].equalsIgnoreCase("Student")) {
			String group = person[1].split("group=")[1];
			double grade = Double.parseDouble(person[2].split("averageGrade=")[1]);
			String name = person[3].split("name=")[1];
			String email = person[4].split("email=")[1];
			Person balbes = new Student(name, email, group, grade);
			return balbes;
		} else if (person[0].equalsIgnoreCase("/Teacher") || person[0].equalsIgnoreCase("Teacher")) {
			String subject = person[1].split("subject=")[1];
			String name = person[2].split("name=")[1];
			String email = person[3].split("email=")[1];
			Person balbes = new Teacher(name, email, subject);
			return balbes;
		} else if (person[0].equalsIgnoreCase("/Administrator") || person[0].equalsIgnoreCase("Administrator")) {
			String department = person[1].split("department=")[1];
			String name = person[2].split("name=")[1];
			String email = person[3].split("email=")[1];
			Person balbes = new Teacher(name, email, department);
			return balbes;
		} else {
			return null;
		}
	}

	public static List<String> parseCatalog(String data) {

		String[] s = data.split("\n");
		List<String> result = new ArrayList<>();

		for (int i = 0; i < s.length; i++) {
			result.add(s[i]);
		}
		return result;
	}
}
