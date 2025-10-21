/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;


/**
 * Creates the Event class that extends to the Activity class
 * @author John Eakes
 */
public class Event extends Activity {
	/** String event details, which holds details of the course */
	private String eventDetails;

	/** Constructor that calls super for title, days, and times, and brings in it's own variable: event details
	 * @param title title of the course
	 * @param meetingDays meeting days of the course
	 * @param startTime start time of the course
	 * @param endTime end time of the course
	 * @param eventDetails any extra details for the course
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);
	}
	
	
	/** returns event details
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}
	

	/** Sets eventDetails variable
	 * @param eventDetails the eventDetails to set
	 * @throws IllegalArgumentException if event details is null. empty strings allowed
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}
	

	/**
	 * Returns two blanks, then title and meeting string
	 * @return string array with the above values
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[] {"", "", getTitle(), getMeetingString()};
	}
	
	/**
	 * Returns two blanks, then title, then two more blanks, then meeting string, then event details
	 * @return string array with the above values
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] {"", "", getTitle(), "", "", getMeetingString(), getEventDetails()};
	}

	/**
	 * Returns title, meeting days, start time, end time, and event details
	 * @return a string with the above details, in order
	 */
	@Override
	public String toString() {
		return getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getEventDetails();
	}

	/**
	 * Override setMeetingDaysAndTime from Activity.java
	 * Does the same function, just with two extra days (U and S, Saturday and Sunday respectively)
	 * @param meetingDays the meeting days
	 * @param startTime the starting time
	 * @param endTime the ending time
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		String validDays = "MTWHFUS";
		for(int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);
			if(validDays.indexOf(day) == -1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/** Checks if the events are a duplicate, which is if they have the same title
	 * @param activity the activity being checked
	 * @return boolean true if it's a duplicate and false if it's not
	 * 
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Event) {
			Event event = (Event) activity;
			return this.getTitle().equals(event.getTitle());
		}
		return false;
	}

	
}
