/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** test class for conflict exception, which tests the two constructors
 * @author John Eakes
 */
class ConflictExceptionTest {

	/** Tests the constructor with a specific string parameter
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException(java.lang.String)}.
	 */
	@Test
	void testConflictExceptionString() {
		ConflictException ce = new ConflictException("There is a conflict.");
		assertEquals("There is a conflict.", ce.getMessage());
	}

	/** Tests the constructor with no parameter
	 * Test method for {@link edu.ncsu.csc216.wolf_scheduler.course.ConflictException#ConflictException()}.
	 */
	@Test
	void testConflictException() {
		ConflictException ce = new ConflictException();
		assertEquals("Schedule conflict.", ce.getMessage());
	}

}
