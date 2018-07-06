package com.interset.interview;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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


        // Pass input file to respective method based on extension
        switch(getFileTypeFromExtension(args[0])) {
        		case "csv":
        			printOutput(csvLoad(args[0],false));
        			break;
        		case "json":
        			printOutput(jsonLoad(args[0],false));
        			break;
        		case "gz":
        			gzipFileHandler(args[0]);        			
        			break;
    			default :
    				// Nothing matches extension
    				System.out.println("Input file must be JSON, CSV or Gzip file.");
    	            System.exit(1);
        }
      
        
            

    }
    
    public static void gzipFileHandler(String inputFile) {
			if (getFileTypeFromExtension(getGzipFileTypeFromExtension(inputFile)) == "csv") {
				printOutput(csvLoad(inputFile,true));
			} else if (getFileTypeFromExtension(getGzipFileTypeFromExtension(inputFile)) == "json") {
				printOutput(jsonLoad(inputFile,true));
			} else {
				System.out.println("Input Gzipped file must be valid JSON or CSV file.");
	            System.exit(1);
			}
    }
    
    public static String getFileTypeFromExtension(String inputFile) {
    		return inputFile.substring(inputFile.lastIndexOf(".") + 1);
    }
    
    public static String getGzipFileTypeFromExtension(String inputFile) {
		return inputFile.substring(0,inputFile.lastIndexOf("."));
}
    
 // Simple function to read a csv file and create a list of Person objects    
    public static List<Person> csvLoad(String inputFile, boolean isGzip) {
    			Reader reader = null;
    			List<Person> csvPersons = new ArrayList<Person>();
    	
    			if (isGzip) {
    				/*
    				 * 
    				 * Tried to implement a gzip version of csvloader, but time constraints and technical challenge of converting CSVReader object to Reader prevented me from completing this part.  
    				 *  
    				 * */
    				System.out.println("Gzip parsing of csv files not yet implemented");
    				System.exit(0);
    				
    			} else if (!isGzip) {
    				/*
    				 * 
    				 * Parsing of non-gzipped csv file 
    				 * 
    				 * */
    				try {
    					reader = Files.newBufferedReader(Paths.get(inputFile));		
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    		        
    		        CsvToBean<Person> csvToBean = new CsvToBeanBuilder(reader)
    		                .withType(Person.class)
    		                .withIgnoreLeadingWhiteSpace(true)
    		                .build();
    		        
    		        
    		        Iterator<Person> personIterator = csvToBean.iterator();
    		        
    		        while (personIterator.hasNext()) {
    		        		csvPersons.add(personIterator.next());
    		        }
    			}      
	        
	        return csvPersons;
    }
    
    // Simple function to read a json file and create a list of Person objects    
    public static List<Person> jsonLoad(String inputFile, boolean isGzip) {
	    	JsonReader reader = null;
	    	List<Person> jsonPersons = new ArrayList<Person>();
	    	
			if (isGzip) {
				/*
				 * 
				 * Could not implement gzip version of json loader within time. Potential idea was to use GZIPOutputStream for a BufferedOutputStream which gets converted by gson.toJson()  
				 *  
				 * */
				System.out.println("Parsing of Gzipped json files not yet implemented");
				System.exit(0);
				
			} else if (!isGzip) {
				/*
				 * 
				 * Parsing of non-gzipped json file 
				 * 
				 * */
				try {
					reader = new JsonReader(new FileReader(inputFile));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    		jsonPersons = new Gson().fromJson(
		    	                                reader, 
		    	                                new TypeToken<List<Person>>() {}.getType());
			}
			

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
    public static List<Entry<String, Integer>> getFaviorteFoodsCount(List<Person> personList) {
    	
    		HashMap<String, Integer> map = new HashMap<>();
    		
    		for (Person person : personList) {
    			String favoriteFood = person.getFavourite_food();
    			Integer count = map.get(favoriteFood);
    			if (count == null) {
    				map.put(favoriteFood,1);
    			}
    			else {
    				map.put(favoriteFood,count + 1);
    			}
    		}
    		
    		List<Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
    		
    		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
    			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
    				return (o2.getValue()).compareTo(o1.getValue());
    			}
    		});
    		
    		return list;

    }
    
    public static int[] getBirthMonthCounts(List<Person> personsList) {
    		// Int array with each index for Months 1 - 12
		int[] output = new int[12];
		
		for (Person person : personsList) {
			int birthMonth = person.getBirthMonth()-1;			
			Integer count = output[birthMonth];

			output[birthMonth]=count + 1;
		}
		return output;
    } 
    
    public static void printOutput(List<Person> personsList) {
        // Get average siblig count
        System.out.println("Average siblings: " + getAverageSiblings(personsList));
        
        // Get a list of favorite foods and their counts (unsorted) 
        List<Entry<String, Integer>> faviorteFoodsCount = getFaviorteFoodsCount(personsList);
        System.out.print("Three favourite foods: ");
        
        System.out.print(faviorteFoodsCount.get(0).getKey() + " (" + faviorteFoodsCount.get(0).getValue() + "), ");
        System.out.print(faviorteFoodsCount.get(1).getKey() + " (" + faviorteFoodsCount.get(1).getValue() + "), ");
        System.out.println(faviorteFoodsCount.get(2).getKey() + " (" + faviorteFoodsCount.get(2).getValue() + ") ");
        
        // How many people were born in each month
        int[] birthMonths = getBirthMonthCounts(personsList);
        System.out.print("Birth Months: ");
        for(int n=1;n<12;n++) {
        		String month = Month.of(n).name().toLowerCase();
        		month = month.substring(0, 1).toUpperCase() + month.substring(1);        		
        		System.out.print(month + " (" + birthMonths[n-1] + ")" + ", ");
        		
        }
        
        String lastMonth = Month.of(12).name().toLowerCase();
		lastMonth = lastMonth.substring(0, 1).toUpperCase() + lastMonth.substring(1);
        
        System.out.println(lastMonth + " (" + birthMonths[11] + ")");
        
        
    }
    

    
}
