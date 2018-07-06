package com.interset.interview;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

/**
 * Unit test for Runner
 */
public class RunnerTest 
{

    // Example test
    @Test
    public void unitTests()
    {
		// Create a List<Person>
        Person newPerson1 = new Person("DELIA","MCCRAE",5,"chicken","−08:00",601605300000L);
        Person newPerson2 = new Person("EUGENE","VANDERSTEEN",2,"Yogurt","+01:00",853371780000L);
    		Person newPerson3 = new Person("BERNARDINA","STWART",1,"Mozzarella cheese","+10:30",285926100000L);
    		Person newPerson4 = new Person("LAJUANA","RAKE",5,"chicken","-07:00",96538800000L);
    		Person newPerson5 = new Person("DORTHEA","BRIGANCE",2,"chicken","-05:00",380433540000L);
    		Person newPerson6 = new Person("VICENTE","GARNETT	",1,"Yogurt","+01:00",56682740000L);
    		
    		// List<Person> personList
    		List<Person> personsList = new ArrayList<Person>();
    		personsList.add(newPerson1);
    		personsList.add(newPerson2);
    		personsList.add(newPerson3);
    		personsList.add(newPerson4);
    		personsList.add(newPerson5);
    		personsList.add(newPerson6);
    		
    		// Test 1
    		testGetAverageSiblingsFunction(personsList, 3);   		
    		
    		// Create expected output list
    		List<Entry<String, Integer>> expectedList = new ArrayList<Entry<String, Integer>>();
    		expectedList.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("chicken", 3));
    		expectedList.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("Yogurt", 2));
    		expectedList.add(new java.util.AbstractMap.SimpleEntry<String, Integer>("Mozzarella cheese", 1));
    		
    		// Test 2
    		testGetFaviorteFoodsCountFunction(personsList, expectedList);
    		
    		// Create expected output array for birthdates in each month
    		int[] test = new int[12];
    		test[0]=5;
    		test[9]=1;
    		
    		// Test 3
    		testGetBirthMonthCounts(personsList,test);
    		
    		
    }    
    
    public void testGetAverageSiblingsFunction(List<Person> personList, int expectAvg) { 		
    		
    		assertEquals(Runner.getAverageSiblings(personList),expectAvg);
    		
    }
    
    public void testGetFaviorteFoodsCountFunction(List<Person> personList, List<Entry<String, Integer>> sortedList) {
    		
    		List<Entry<String, Integer>> faviorteFoodsCount = Runner.getFaviorteFoodsCount(personList);
    		
        for(int n=0;n<3;n++) {
        		assertEquals(faviorteFoodsCount.get(n).getKey(), sortedList.get(n).getKey());
        }
    }
    
    public void testGetBirthMonthCounts(List<Person> personList, int[] expectedMonthCounts) {
    		int[] result = Runner.getBirthMonthCounts(personList);
    		for(int m=0;m<12;m++) {
    			assertEquals(result[m],expectedMonthCounts[m]);
    		}
    }
}

