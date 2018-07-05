package com.interset.interview;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class Runner {

    /**
     * This is main method which is starting point for this application.
     * It requires 1 arguments to run successfully.
     *
     * @param: args[0] : Path to JSON or CSV file to read.
     *
     * The JSON and CSV files must contain the following fields:
     *  name, siblings, favourite_food, birth_timezone, birth_timestamp
     *
     * This application parses the files and prints out the following information:
     *       - Average number of siblings (round up)
     *       - Top 3 favourite foods
     *       - How many people were born in each month of the year (uses the month of each person's respective timezone of birth)
     *
     */
    public static void main(String args[]) {

        if (args.length != 1) {
            System.out.println("We currently only expect 1 argument! A path to a JSON or CSV file to read.");
            System.exit(1);
        }

//        System.out.println("Do cool stuff here!!");
        
//        List<Person> jsonPersons = jsonLoad(args[0]);
//	    	for (Person jsonPerson : jsonPersons) {	    		
//	    		System.out.println(jsonPerson);
//	    	}
                
        // Run Json file loader 
        getAverageSiblings(jsonLoad(args[0]));
        

    }
    
    // Simple function to read a json file and create a list of Person objects    
    public static List<Person> jsonLoad(String inputFile) {
	    	JsonReader reader = null;
			try {
				reader = new JsonReader(new FileReader(inputFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	List<Person> jsonPersons = new Gson().fromJson(
	    	                                reader, 
	    	                                new TypeToken<List<Person>>() {}.getType());
	    	return jsonPersons;
    }
    
    // Function to return average ( rounded up) of siblings for a List<Person>        
    public static int getAverageSiblings(List<Person> personsList) {
    		double average = personsList.stream()
                .mapToInt(p -> p.getSiblings())
                .average()
                .orElse(0);
    		return (int) Math.ceil(average);
    }
    
    // Function to return a list of favorite foods and counts in sorted order
    public static void getTopFaviorteFoods(List<Person> personList) {
    		
    }
    
    
}
