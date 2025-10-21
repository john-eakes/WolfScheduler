package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Tests checkConflict in Activity.Java
 * @author John Eakes
 */
class ActivityTest {
	
	/**
	 * Tests checkConflict for Activity
	 */
	@Test
	void testConflictCheck() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	}

	/**
	 * Tests checkConflict for different days, same times
	 */
	@Test
	void testNoConflictDifferentDays() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		
		
	}
	
	/**
	 * Tests checkConflict for same day, different times
	 */
	@Test
	void testNoConflictDifferentTime() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 830, 945);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
	}
	
	/**
	 * Tests checkConflict for same day, and overlapping times
	 */
	@Test
	void testConflictOnDay() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1100, 1215);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1000, 1115);
		
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
	    
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Tests checkConflict for same day, where the first class ends when the next one begins
	 */
	@Test
	void testConflictWithEndAtNextBeginning() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 830, 945);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 945, 1100);
		
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
	    
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());
	}
	
	/**
	 * Tests checkConflict for the same day and same exact time
	 */
	@Test
	void testConflictSameExactTime() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
	    assertEquals("Schedule conflict.", e1.getMessage());
	    
	    Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
	    assertEquals("Schedule conflict.", e2.getMessage());		
	}
	
	/**
	 * Tests multiple events
	 */
	@Test
	void testMultipleConflictedActivities() {
		Activity a1 = new Event("1", "MTWHF", 1200, 1300, "");
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "ixdoming", "MW", 1330, 1445);
		Activity a3 = new Course("CSC 226", "Discrete Mathematics for Computer Scientists", "001", 3, "dbsturgi", "MW", 935, 1025);
		Activity a4 = new Course("CSC 230", "C and Software Tools", "001", 3, "dbsturgi", "MW", 1145, 1300);
		
		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a3));
		assertDoesNotThrow(() -> a1.checkConflict(a3));
		assertDoesNotThrow(() -> a2.checkConflict(a4));
		assertDoesNotThrow(() -> a3.checkConflict(a4));

		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
	    assertEquals("Schedule conflict.", e1.getMessage());	


	}
	
}
