/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/** ConflictException is thrown if there is a conflict and we want to handle it

 * @author John Eakes
 */
public class ConflictException extends Exception {

	/** ID used for serialization */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor that calls down to the parameterless constructor to state that there's a conflict
	 * @param message the message that's stated if there's an exception
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructor that states that there's a conflict
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
