package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;


import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author John Eakes
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
	    Scanner fileReader = new Scanner(new FileInputStream(fileName));  
	    ArrayList<Course> courses = new ArrayList<Course>(); 
	    while (fileReader.hasNextLine()) { 
	        try { 
	            Course course = readCourse(fileReader.nextLine()); 

	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course current = courses.get(i);
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    duplicate = true;
	                    break; 
	                }
	            }
	            if (!duplicate) {
	                courses.add(course); 
	            } 
	        } catch (IllegalArgumentException e) {
	        	System.out.print("");
	        }
	    }
	    fileReader.close();
	    return courses;
	}

	/**
	 * The readCourse method reads in a single line and processes it by using the comma as a delimiter
	 * @param line the line of the text file that's being processed
	 * @return a Course object into the Course constructor with all of the needed parameters
	 */
	private static Course readCourse(String line) {
		Scanner scnr = new Scanner(line);
		scnr.useDelimiter(",");
		
		try {
			String name = scnr.next();
			String title = scnr.next();
			String section = scnr.next();
			int creditHours = scnr.nextInt();
			String instructor = scnr.next();
			String meetingDays = scnr.next();
			
			if("A".equals(meetingDays)) {
				if(scnr.hasNext()) {
					throw new IllegalArgumentException("Invalid  input for arranged course.");
				}
				else {
					return new Course(name, title, section, creditHours, instructor, meetingDays);
				}
			}
			else {
				int startTime = scnr.nextInt();
				int endTime = scnr.nextInt();
				if(scnr.hasNext()) {
					throw new IllegalArgumentException("Invalid input for regular course.");
				}
				return new Course(name, title, section, creditHours, instructor, meetingDays, startTime, endTime);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Error processing course input.", e);
		} finally {
			scnr.close();
		}
	}

}