package edu.ncsu.csc216.wolf_scheduler.course;

/** Conflict checks for scheduling conflicts between any events and classes in the schedule
 * @author John Eakes
 */
public interface Conflict {

	/**
	 * checkConflict take in a parameter of the activity that could be conflicted and checks the schedule if it is
	 * @param possibleConflictingActivity the activity that is being checked for scheduling conflicts
	 * @throws ConflictException exception created by me for if there's a conflict in the schedule
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
