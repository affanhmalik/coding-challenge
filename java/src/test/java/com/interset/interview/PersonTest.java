package com.interset.interview;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit test for Runner
 */
public class PersonTest 
{

    // Example test
    @Test
    public void createPersonObject()
    {     
        Person newPerson1 = new Person("DELIA","MCCRAE",5,"chicken","−08:00",601605300000L);
        Person newPerson2 = new Person("EUGENE","VANDERSTEEN",2,"Yogurt","+01:00",853371780000L);
    		Person newPerson3 = new Person("BERNARDINA","STWART",1,"Mozzarella cheese","+10:30",285926100000L);
    		
    		assertTrue( newPerson1 instanceof Person);
    		assertTrue( newPerson2 instanceof Person);
    		assertTrue( newPerson3 instanceof Person);
    }
}

