package com.interset.interview;

import org.junit.*;
import static org.junit.Assert.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Unit test for Runner
 */
public class PersonTest 
{

    // Test Person objects
    @Test
    public void createPersonObject()
    {     
        Person newPerson1 = new Person("DELIA","MCCRAE",5,"chicken","−08:00",601605300000L);
        Person newPerson2 = new Person("EUGENE","VANDERSTEEN",2,"Yogurt","+01:00",853371780000L);
    		Person newPerson3 = new Person("BERNARDINA","STWART",1,"Mozzarella cheese","+10:30",285926100000L);
    		
    		
    		assertTrue( newPerson1 instanceof Person);
    		assertTrue( newPerson2 instanceof Person);
    		assertTrue( newPerson3 instanceof Person);
    		assertEquals(newPerson1.getLast_name(),"MCCRAE");
    		assertEquals(newPerson2.getLast_name(),"VANDERSTEEN");
    		assertEquals(newPerson3.getLast_name(),"STWART");
    }
    
    @Test
    public void testBirthMonthFunction() {
	    	Person testPerson1 = new Person("Joe","One",3,"Chocolate","+03:00",760057996000L); // Monday, January 31, 1994 11:13:16 PM (UTC)
	    	Person testPerson2 = new Person("Joe","Two",1,"Chocolate","-05:00",560045596000L); // Thursday, October 1, 1987 12:13:16 AM (UTC)
	    	Person testPerson3 = new Person("Joe","Three",5,"Chocolate","+07:30",924264796000L); // Friday, April 16, 1999 12:13:16 PM (UTC)  
	    	Person testPerson4 = new Person("Joe","Four",0,"Chocolate","+10:00",383874301000L); // Wednesday, February 29, 1984 11:45:01 PM  
	    	
	    	testPersonBirthMonth(testPerson1, 2);
	    	testPersonBirthMonth(testPerson2, 9);
	    	testPersonBirthMonth(testPerson3, 4);
	    	testPersonBirthMonth(testPerson4, 3);
    	
    }
    
    // 
    public void testPersonBirthMonth(Person testPerson, int expectedBirthMonth)
    {
    		assertEquals(testPerson.getBirthMonth(),expectedBirthMonth);
    }
}

