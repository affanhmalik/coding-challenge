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
                
        // Run Json file loader 
        jsonLoad(args[0]);
        

    }
    
    // Simple function to read a json file and create a list of Person objects    
    public static void jsonLoad(String inputFile) {
	    	JsonReader reader = null;
			try {
				reader = new JsonReader(new FileReader(inputFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	List<Person> jsonPersons = new Gson().fromJson(
	    	                                reader, 
	    	                                new TypeToken<List<Person>>() {}.getType());
	
	    	for (Person jsonPerson : jsonPersons) {	    		
	    		System.out.println(jsonPerson);
	    	}
    }
    
    
}
