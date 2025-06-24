package com.edu.educational_system.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.edu.educational_system.controller.CourseController;
import com.edu.educational_system.model.Administrator;
import com.edu.educational_system.model.Course;
import com.edu.educational_system.model.Person;
import com.edu.educational_system.model.Student;
import com.edu.educational_system.model.Teacher;
import com.edu.educational_system.utils.Parser;

public class CourseConsoleView {
	private final Scanner scanner = new Scanner(System.in);
	private final CourseController controller;
	private Course currentCourse;

	public CourseConsoleView(CourseController controller) {
		this.controller = controller;
	}

	public void displayMenu() {
		while (true) {
			System.out.println("\n==== Course Management Menu ====");
			System.out.println("1. Choose Course");
			System.out.println("2. Create Course");
			System.out.println("3. Add Participant");
			System.out.println("4. Start Lesson");
			System.out.println("5. Show Course Info");
			System.out.println("6. Delete personal data of participants");
			System.out.println("7. Save course");
			System.out.println("8. Exit");
			System.out.print("Choose option: ");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1 -> choosCourse();
			case 2 -> createCourse();
			case 3 -> addParticipant();
			case 4 -> startLesson();
			case 5 -> showCourseInfo();
			case 6 -> deletePersonalData();
			case 7 -> saveCourse();
			case 8 -> {
				saveCourse();
				System.out.println("Exiting.");
				return;
			}
			default -> System.out.println("Invalid option.");
			}
		}
	}

	public void saveCourse() {
		String response = controller.doAction("SAVECOURSE" + "\n" + currentCourse.toString());
		System.out.println(response);
	}

	public void choosCourse() {

		List<String> allCourses = getCatalog();

		if (allCourses.size() == 0) {
			System.out.println("No courses available. Please create a course first.");
			return;
		}
		System.out.println("Courses available: ");

		for (int i = 0; i < allCourses.size(); i++) {
			System.out.println(i + 1 + " -- " + allCourses.get(i));
		}
		System.out.print("Enter course number: ");

		while (!scanner.hasNextInt()) {
			System.out.print("Enter course number: ");
			scanner.nextLine();
		}
		int courseNumber = scanner.nextInt();
		scanner.nextLine();
		currentCourse = getCourse(allCourses.get(courseNumber - 1));

	}

	private List<String> getCatalog() {
		String allCourses = controller.doAction("GETCATALOG" + "\n");
		if (!allCourses.equalsIgnoreCase("I couldn't do it, I have paws")) {
			List<String> courses = Parser.parseCatalog(allCourses);
			return courses;
		}
		return new ArrayList<String>();
	}

	private Course getCourse(String name) {

		String response = controller.doAction("GETCOURSE" + "\n" + name);

		if (!response.equalsIgnoreCase("I couldn't do it, I have paws")) {
			Course course = Parser.parseCourse(response);
			return course;
		}
		return currentCourse;
	}

	private void createCourse() {
		System.out.print("Course name: ");
		String courseName = scanner.nextLine();
		Course newCourse = new Course(courseName);
		
		String response = controller.doAction("CREATECOURSE" + "\n" + newCourse.getName());

		if (!response.equalsIgnoreCase("I couldn't do it, I have paws")) {
			currentCourse = newCourse;
			System.out.println(response);
			return;
		}
		System.out.println(response);
	}

	private void addParticipant() {
		if (currentCourse == null) {
			System.out.println("Please create a course first.");
			return;
		}

		System.out.println("Select role: 1 - Student, 2 - Teacher, 3 - Administrator");
		int role = Integer.parseInt(scanner.nextLine());

		System.out.print("Name: ");
		String name = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();

		Person person = null;

		switch (role) {
		case 1 -> {
			System.out.print("Group: ");
			String group = scanner.nextLine();
			System.out.print("Average grade: ");
			double grade = Double.parseDouble(scanner.nextLine());
			person = new Student(name, email, group, grade);
		}
		case 2 -> {
			System.out.print("Subject: ");
			String subject = scanner.nextLine();
			person = new Teacher(name, email, subject);
		}
		case 3 -> {
			System.out.print("Department: ");
			String dept = scanner.nextLine();
			person = new Administrator(name, email, dept);
		}
		default -> System.out.println("Invalid role.");
		}

		if (person != null) {
			String response = controller
					.doAction("REGISTERPERSON" + "\n" + currentCourse.toString() + "\n" + person.toString());
			System.out.println(response);
		}
		currentCourse.addParticipant(person);
		saveCourse();
	}

	private void startLesson() {
		if (currentCourse == null) {
			System.out.println("Please create a course first.");
			return;
		}
		controller.doAction("STARTLESSON" + "\n" + currentCourse.toString());
	}

	private void showCourseInfo() {
		if (currentCourse == null) {
			System.out.println("No course available.");
			return;
		}

		System.out.println("\nCourse: " + currentCourse.getName());
		List<Person> participants = currentCourse.getParticipants();
		for (Person p : participants) {
			System.out.println("- " + p.getName() + " | " + p.getRoleDescription());
		}
		System.out.printf("Average Student Grade: %.2f\n", currentCourse.calculateAverageGrade());

		List<Person> managers = currentCourse.getManagers();
		for (Person p : managers) {
			System.out.println("- " + p.getName() + " | " + p.getRoleDescription());
		}

	}

	public void deletePersonalData() {
		controller.doAction("SAVECOURSE" + "\n" + currentCourse.toString());
		String response = controller.doAction("DELETEPERSONALDATA" + "\n" + currentCourse.getName());
		if (!response.split(",", 2)[0].equalsIgnoreCase("I couldn't do it")) {
			currentCourse = Parser.parseCourse(response);
			return;
		}
		System.out.println(response);
	}
}
