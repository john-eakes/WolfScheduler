package edu.ncsu.csc216.wolf_scheduler.course;
/**
 * Creates the activity class that's extended from course
 * @author John Eakes
 */
public abstract class Activity implements Conflict {

	/** Upper possible hour */
	private static final int UPPER_HOUR = 24;
	/** Upper possible minute */
	private static final int UPPER_MINUTE = 60;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Constructor for activity that calls to course.java
	 * @param title title of the course
	 * @param meetingDays the days that the course meets
	 * @param startTime the classes starting time
	 * @param endTime the classes ending time
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
        super();
        setTitle(title);
        setMeetingDaysAndTime(meetingDays, startTime, endTime);
    }

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
	    if (title == null || "".equals(title)) {
	        throw new IllegalArgumentException("Invalid title.");
	    }
	
	    this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
	    return meetingDays;
	}

	/**
	 * Returns the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
	    return startTime;
	}

	/**
	 * Returns the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
	    return endTime;
	}

	/**
	 * Sets the Course's meeting days
	 * 
	 * @param meetingDays the meetingDays to set
	 * @param startTime   start time of course
	 * @param endTime     end time of course
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
	    // Check for null or empty string
	    if (meetingDays == null || "".equals(meetingDays)) {
	        throw new IllegalArgumentException("Invalid meeting days and times.");
	    }
	
	    // Handle arranged
	    if ("A".equals(meetingDays)) {
	    	if (startTime != 0 || endTime != 0) {
	    		throw new IllegalArgumentException("Invalid meeting days and times.");
	    	}
	        this.meetingDays = meetingDays;
	        this.startTime = 0;
	        this.endTime = 0;
	    } else {
	        // Check for valid characters in meeting days
	        int countM = 0;
	        int countT = 0;
	        int countW = 0;
	        int countH = 0;
	        int countF = 0;
	        int countU = 0;
	        int countS = 0;
	        for (int i = 0; i < meetingDays.length(); i++) {
	            char c = meetingDays.charAt(i);
	            switch (c) {
	            case 'M':
	                countM++;
	                break;
	            case 'T':
	                countT++;
	                break;
	            case 'W':
	                countW++;
	                break;
	            case 'H':
	                countH++;
	                break;
	            case 'F':
	                countF++;
	                break;
	            case 'U':
	            	countU++;
	            	break;
	            case 'S':
	            	countS++;
	            	break;
	            default:
	                throw new IllegalArgumentException("Invalid meeting days and times.");
	            }
	        }
	
	        if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1 || countU > 1 || countS > 1) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	
	        // Check for valid meeting times
	        int startHour = startTime / 100;
	        int startMin = startTime % 100;
	        int endHour = endTime / 100;
	        int endMin = endTime % 100;
	
	        if (startHour < 0 || startHour >= UPPER_HOUR) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (startMin < 0 || startMin >= UPPER_MINUTE) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (endHour < 0 || endHour >= UPPER_HOUR) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (endMin < 0 || endMin >= UPPER_MINUTE) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	        if (endTime < startTime) {
	            throw new IllegalArgumentException("Invalid meeting days and times.");
	        }
	
	        this.meetingDays = meetingDays;
	        this.startTime = startTime;
	        this.endTime = endTime;
	    }
	
	}

	/**
	 * Returns a string representation of the Course's meeting days and times.
	 * 
	 * @return Course's meeting days and times.
	 */
	public String getMeetingString() {
	    if ("A".equals(meetingDays)) {
	        return "Arranged";
	    }
	
	    return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
	}

	/**
	 * Returns the time in AM/PM format.
	 * 
	 * @param time as an integer
	 * @return time as a string
	 */
	private String getTimeString(int time) {
	    int hour = time / 100;
	    int min = time % 100;
	    boolean morning = true;
	
	    if (hour >= 12) {
	        hour -= 12;
	        morning = false;
	    }
	    if (hour == 0) {
	        hour = 12;
	    }
	
	    String minS = "" + min;
	    if (min < 10) {
	        minS = "0" + minS;
	    }
	
	    String end = morning ? "AM" : "PM";
	
	    return hour + ":" + minS + end;
	}
	
	/** Returns the hashcode
	 * @return integer value for hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/** Checks if objects are identical
	 * @param obj the object that we're checking against
	 * @return boolean whether they're equal or not
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	/**
	 * Method call to course.getShortDisplayArray
	 * @return string array with shortened display
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Method call to course.getLongDisplayArray
	 * @return string array with longer display
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Method call to isDuplicate in Event and Course classes
	 * @param activity the activity that is being checked for duplicates
	 * @return boolean true if the activity is a duplicate and false if not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Overrides the checkConflict to resolve compiler errors in Course and Event.
	 * An activity has a conflict with another if they are on the same day at the same time.
	 * @param possibleConflictingActivity the activity that we are checking for conflicts
	 * @throws ConflictException if the activities overlap
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		if("A".equals(this.meetingDays) || "A".equals(possibleConflictingActivity.meetingDays)) {
			return;
		}
		
		for(int i = 0; i < this.meetingDays.length(); i++) {
			char day = this.meetingDays.charAt(i);
			for(int j = 0; j < possibleConflictingActivity.meetingDays.length(); j++) {
				char otherDay = possibleConflictingActivity.meetingDays.charAt(j);
				if(day == otherDay) {
					boolean startOverlap = possibleConflictingActivity.startTime >= this.startTime && possibleConflictingActivity.startTime <= this.endTime;
					boolean endOverlap = possibleConflictingActivity.endTime >= this.startTime && possibleConflictingActivity.endTime <= this.endTime;
					boolean fullOverlap = possibleConflictingActivity.startTime <= this.startTime && possibleConflictingActivity.endTime >= this.endTime;
					
					if(startOverlap || endOverlap || fullOverlap) {
						throw new ConflictException();
					}
				}
			}
		}
		
	}

	
	
}