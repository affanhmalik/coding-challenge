package com.interset.interview;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Runner
 */
public class RunnerTest 
{

    // Example test
    @Test
    public void testApp()
    {
        assertTrue( true );
    }
    
    @Test
    public void testGetAverageSiblingsFunction() {
    		// Create a List<Person>
        Person newPerson1 = new Person("DELIA","MCCRAE",5,"chicken","−08:00",601605300000L);
        Person newPerson2 = new Person("EUGENE","VANDERSTEEN",2,"Yogurt","+01:00",853371780000L);
    		Person newPerson3 = new Person("BERNARDINA","STWART",1,"Mozzarella cheese","+10:30",285926100000L);
    		
    		// List<Person> personList
    		List<Person> personsList= new ArrayList<Person>();
    		personsList.add(newPerson1);
    		personsList.add(newPerson2);
    		personsList.add(newPerson3);
    		
    		assertEquals(Runner.getAverageSiblings(personsList),3);
    		
    		
    }
}

