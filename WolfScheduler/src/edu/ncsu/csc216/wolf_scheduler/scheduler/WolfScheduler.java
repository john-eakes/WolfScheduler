package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/** Creates a schedule for a user and provides functionality for naming the schedule,
 * adding and removing classes, and resetting the schedule.
 * @author John Eakes
 */
public class WolfScheduler {
	/** This is the array list for the course catalog */
	private ArrayList<Course> catalog;
	
	/** This is the array list for the schedule */
	private ArrayList<Activity> schedule;
	
	/** This is the schedule title */
	private String title;
	
	/**
	 * Creates an object of the WolfScheduler class
	 * @param testFile the test file that's read into the program
	 */
	public WolfScheduler(String testFile) {
		schedule = new ArrayList<Activity>();
		title = "My Schedule";
		try {
			catalog = CourseRecordIO.readCourseRecords(testFile);
		} catch(Exception e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	
	/**
	 * Returns a string array of the catalog with a row for each course, and
	 * 3 columns for name, section, and title
	 * @return a 2d string array of the catalog
	 */
	public String[][] getCourseCatalog() {
		if(catalog.isEmpty()) {
			return new String[0][0];
		}
		String [][] catalogArray = new String[catalog.size()][4];
		for(int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			catalogArray[i] = course.getShortDisplayArray();
		}
		return catalogArray;
	}
	
	/**
	 * Returns a 2d string array with a row for each Course and three columns for 
	 * name, section, and title
	 * @return a 2D String array of the schedule
	 */
	public String[][] getScheduledActivities() {
		if(schedule.isEmpty()) {
			return new String[0][0];
		}
		String scheduleArray[][] = new String[schedule.size()][4];
		for(int i = 0; i < schedule.size(); i++) {
			Activity activity = schedule.get(i);
			scheduleArray[i] = activity.getShortDisplayArray();
		}
		return scheduleArray;
	}

	/**
	 * Returns a 2D String array of the schedule with all information with a row for each course
	 * and 6 columns for name, section, title, credits, instructorId, and meeting days
	 * @return a 2D String array of the schedule with all information
	 */
	public String[][] getFullScheduledActivities() {
		if(schedule.isEmpty()) {
			return new String[0][0];
		}
		String fullScheduleArray[][] = new String[schedule.size()][7];
		for(int i = 0; i < schedule.size(); i++) {
			Activity activity = schedule.get(i);
			fullScheduleArray[i] = activity.getLongDisplayArray();
		}
		return fullScheduleArray;
	}

	/**
	 * returns the schedule title
	 * @return schedule title
	 */
	public String getScheduleTitle() {
		return title;
	}
	
	/**
	 * throw exception if title is null
	 * @param title title of schedule
	 * @throws IllegalArgumentException if title is null
	 */
	public void setScheduleTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}

	/**
	 * writes file if possible. if not, throws exception
	 * @param fileName the file that the student's schedule is saved to
	 * @throws IllegalArgumentException if file can't be saved
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch(Exception e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}

	/**
	 * Returns a course from the catalog based on the given name and section.
	 * If the course with the given name and section is not found, returns null.
	 * 
	 * @param name the name of the course to find
	 * @param section the section of the course to find
	 * @return the Course object if found, null otherwise
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			if(course.getName().equals(name) && course.getSection().equals(section)) {
				return course;
			}
		}
		return null;
	}

	/**
	 * Adds a course to the student's schedule if the course exists in the catalog 
	 * and is not already in the schedule.
	 * 
	 * @param name the name of the course to add
	 * @param section the section of the course to add
	 * @return true if the course is successfully added, false if it does not exist in the catalog
	 * @throws IllegalArgumentException if the course is already in the schedule
	 */
	public boolean addCourseToSchedule(String name, String section) {
		Course course = getCourseFromCatalog(name, section);
		if(course == null) {
			return false;
		}
		for(Activity activity : schedule) {
			
			try {
				activity.checkConflict(course);
			}
			catch(ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			if(activity.isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * Removes a course from the student's schedule if the course exists in the schedule
	 * @param idx the index of the event being removed
	 * 
	 * @return true if the course was removed
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Resets the student's schedule by creating a new, empty ArrayList for the schedule.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<>();
	}
	
	/** Adds an event to schedule so long as it's not a duplicate
	 * @param eventTitle the events title
	 * @param eventMeetingDays the event's days
	 * @param eventStartTime the starting time
	 * @param eventEndTime the ending time
	 * @param eventDetails the details of the event
	 * @throws IllegalArgumentException if event is a conflict
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		for(Activity activity : schedule) {
			
			if(activity.isDuplicate(event)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
			try {
				activity.checkConflict(event);
			}
			catch(ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
			}
			
		}
		schedule.add(event);
	}
}
