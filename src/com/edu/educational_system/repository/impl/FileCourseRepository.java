package com.edu.educational_system.repository.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.edu.educational_system.model.Course;
import com.edu.educational_system.repository.CourseRepository;
import com.edu.educational_system.repository.CourseRepositoryException;
import com.edu.educational_system.utils.Parser;

public class FileCourseRepository implements CourseRepository {

	private List<String> courseCatalog = new ArrayList<>();

	public FileCourseRepository() {
		try {
			this.courseCatalog = getCatalogFromFile();
		} catch (IOException e) {
			throw new RuntimeException("No courses available");
		}
	}

	public List<String> getCourseCatalog() {
		return courseCatalog;
	}

	public void addCourse(Course course) throws CourseRepositoryException {

		if (!courseCatalog.contains(course.getName())) {
			try {
				writeCourseToFile(course);
				courseCatalog.add(course.getName());
				writeCatalogToFile(courseCatalog);
			} catch (IOException e) {
				throw new CourseRepositoryException(e);
			}
		}
	}

	public void saveCourse(Course course) throws CourseRepositoryException {
		try {
			writeCourseToFile(course);
		} catch (IOException e) {
			throw new CourseRepositoryException("saving failed", e);
		}

	}

	public Course getCourse(String name) throws CourseRepositoryException {

		String data = null;
		try {
			data = readCourseFromFile(name);
		} catch (IOException e) {
			throw new CourseRepositoryException("error reading file", e);
		}
		Course course = Parser.parseCourse(data);
		return course;
	}

	public List<Course> getAllCourses() throws CourseRepositoryException {

		List<Course> courses = new ArrayList<>();

		for (String name : courseCatalog) {
			courses.add(getCourse(name));
		}
		return courses;
	}

	public Course getObfuscatedCourse(String name) throws CourseRepositoryException {
		String data = null;
		try {
			data = readCourseFromFile(name);
		} catch (IOException e) {
			throw new CourseRepositoryException("error reading file", e);
		}
		Course course = Parser.parseCourseWithObfuscationPersonalData(data);
		return course;
	}

	private List<String> getCatalogFromFile() throws IOException {
		String data = readCatalogFromFile();
		List<String> catalog = Parser.parseCatalog(data);
		return catalog;
	}

	private String readCatalogFromFile() throws IOException {
		URL location = FileCourseRepository.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = location.getPath() + "data/" + "catalog.txt";
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		String file = content.toString();
		return file;
	}

	private String readCourseFromFile(String name) throws IOException {
		URL location = FileCourseRepository.class.getProtectionDomain().getCodeSource().getLocation();
		String filePath = location.getPath() + "data/Courses/" + name + ".txt";
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		String file = content.toString();
		return file;
	}

	private void writeCourseToFile(Course data) throws IOException {
		URL location = FileCourseRepository.class.getProtectionDomain().getCodeSource().getLocation();

		String filePath = location.getPath() + "data/Courses/" + data.getName() + ".txt";

		try (FileWriter writer = new FileWriter(filePath); PrintWriter printWriter = new PrintWriter(writer)) {

			String s = data.toString();
			printWriter.println(s);
		}
	}

	private void writeCatalogToFile(List<String> data) throws IOException {

		URL location = FileCourseRepository.class.getProtectionDomain().getCodeSource().getLocation();

		String filePath = location.getPath() + "data/catalog.txt";

		try (FileWriter writer = new FileWriter(filePath); PrintWriter printWriter = new PrintWriter(writer)) {

			StringBuffer toWrite = new StringBuffer();
			for (String s : data) {
				toWrite = toWrite.append(s + "\n");
			}
			printWriter.println(toWrite);
		}
	}
}
