/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Creates the Course object and checks that all fields are valid.
 * @author John Eakes
 */
public class Course extends Activity {
    /** Min length of name field. */
    private static final int MIN_NAME_LENGTH = 5;
    /** Max length of name field. */
    private static final int MAX_NAME_LENGTH = 8;
    /** Min letter count in name */
    private static final int MIN_LETTER_COUNT = 1;
    /** Max letter count in name */
    private static final int MAX_LETTER_COUNT = 4;
    /** Number of digits in name */
    private static final int DIGIT_COUNT = 3;
    /** Length of section number */
    private static final int SECTION_LENGTH = 3;
    /** Max number of possible credits for a course */
    private static final int MAX_CREDITS = 5;
    /** Min number of possible credits for a course */
    private static final int MIN_CREDITS = 1;
    /** Course's name. */
    private String name;
    /** Course's section. */
    private String section;
    /** Course's credit hours */
    private int credits;
    /** Course's instructor */
    private String instructorId;
    /**
     * Constructs a Course object with values for all fields.
     * 
     * @param name         name of Course
     * @param title        title of Course
     * @param section      section of Course
     * @param credits      credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays  meeting days for Course as series of chars
     * @param startTime    start time for Course
     * @param endTime      end time for Course
     */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        setName(name);
        //setTitle(title);  <-- DELETE THIS, called in super
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        //setMeetingDaysAndTime(meetingDays, startTime, endTime); <-- DELETE THIS, called in super
    }

    /**
     * Creates a Course with the given name, title, section, credits, instructorId,
     * and meetingDays for courses that are arranged.
     * 
     * @param name         name of Course
     * @param title        title of Course
     * @param section      section of Course
     * @param credits      credit hours for Course
     * @param instructorId instructor's unity id
     * @param meetingDays  meeting days for Course as series of chars
     */
    public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
        this(name, title, section, credits, instructorId, meetingDays, 0, 0);
    }

    /**
     * Returns the Course's name
     * 
     * @return the name
     */
    public String getName() {
        return name;

    }

    /**
     * Sets the Course's name
     * 
     * @param name the name to set
     */
    private void setName(String name) {
        // Throw exception if the name is null
        if (name == null) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        // Throw exception if the name is an empty string
        // Throw exception if the name contains less than 5 character or greater than 8
        // characters
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        // Check for pattern of L[LLL] NNN
        int letterCount = 0;
        int digitCount = 0;
        boolean spaceFound = false;
        for (int i = 0; i < name.length(); i++) {
            if (!spaceFound) {
                if (Character.isLetter(name.charAt(i))) {
                    letterCount++;
                } else if (name.charAt(i) == ' ') {
                    spaceFound = true;
                } else {
                    throw new IllegalArgumentException("Invalid course name.");
                }
            } else  {
                if (Character.isDigit(name.charAt(i))) {
                    digitCount++;
                } else {
                    throw new IllegalArgumentException("Invalid course name.");
                }
            }
        }
        if (letterCount < MIN_LETTER_COUNT || letterCount > MAX_LETTER_COUNT) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        if (digitCount != DIGIT_COUNT) {
            throw new IllegalArgumentException("Invalid course name.");
        }

        this.name = name;
    }

    /**
     * Returns the Course's section
     * 
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * Sets the Course's section
     * 
     * @param section the section to set
     */
    public void setSection(String section) {
        if (section == null || section.length() != SECTION_LENGTH) {
            throw new IllegalArgumentException("Invalid section.");
        }
        for (int i = 0; i < section.length(); i++) {
            if (!Character.isDigit(section.charAt(i))) {
                throw new IllegalArgumentException("Invalid section.");
            }
        }
        this.section = section;
    }

    /**
     * Returns the Course's credits
     * 
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Sets the Course's credits
     * 
     * @param credits the credits to set
     */
    public void setCredits(int credits) {
        if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
            throw new IllegalArgumentException("Invalid credits.");
        }

        this.credits = credits;
    }

    /**
     * Returns the Course's instructor id
     * 
     * @return the instructorId
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Sets the Course's instructor id
     * 
     * @param instructorId the instructorId to set
     */
    public void setInstructorId(String instructorId) {
        if (instructorId == null || "".equals(instructorId)) {
            throw new IllegalArgumentException("Invalid instructor id.");
        }

        this.instructorId = instructorId;
    }

    
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
     * Returns a comma separated value String of all Course fields.
     * 
     * @return String representation of Course
     */
    @Override
    public String toString() {
        if ("A".equals(getMeetingDays())) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
                + getStartTime() + "," + getEndTime();
    }

    /**
	 * Returns two blanks, then title and meeting string
	 * @return string array with the above values
	 */
	@Override
	public String[] getShortDisplayArray() {
		return new String[] { name, section, getTitle(), getMeetingString() };
	}

	/**
	 * Returns two blanks, then title, then two more blanks, then meeting string, then event details
	 * @return string array with the above values
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] { name, section, getTitle(), String.valueOf(credits), instructorId, getMeetingString(), "" };
	}

	/**
	 * Override setMeetingDaysAndTime from Activity.java
	 * Does the same function, just without the two extra days (U and S, Saturday and Sunday respectively)
	 * @param meetingDays the meeting days
	 * @param startTime the starting time
	 * @param endTime the ending time
	 * @throws IllegalArgumentException if meeting days is invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		String validDays = "MTWHFA";
		for(int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);
			if(validDays.indexOf(day) == -1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Checks if the specific activity is a duplicate, which is if they have the same name
	 * @param activity the activity we're checking
	 * @return true if it's a duplicate and false if not
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course course = (Course) activity;
			return this.name.equals(course.name);
		}
		return false;
	}

	

}