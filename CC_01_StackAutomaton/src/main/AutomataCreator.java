package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * @author jose
 * 
 * This class creates an automaton from a file.
 * It parses the file and extracts from it the needed arguments
 * for the Automaton class constructor.
 */
public abstract class AutomataCreator {
	
	// Regexes used as building blocks to create the patterns below.
	final String STR_SYM_ONE = "\\w";
	final String STR_SYM_MANY = "\\w(\\s+\\w+)*";
	final String STR_OPT_COMM = "(#.*)?";
	final String STR_OPT_SPC = "\\s*";
	
	// Regexes that represent the types of input lines.
	final String STR_LINE_COMM = STR_OPT_COMM;
	final String STR_LINE_ONE = STR_OPT_SPC + STR_SYM_ONE + STR_OPT_SPC + STR_OPT_COMM;
	final String STR_LINE_MANY = STR_OPT_SPC + STR_SYM_MANY + STR_OPT_SPC + STR_OPT_COMM;
	
	// Patterns used to determine the nature of each of the input file's lines.
	Pattern patternComment = Pattern.compile(STR_LINE_COMM);
	Pattern patternSymbolGroup = Pattern.compile(STR_LINE_MANY);
	Pattern patternSymbolSingle = Pattern.compile(STR_LINE_ONE);

	void createAutomaton(String fileName) throws IOException {
		
	    Path path = Paths.get(fileName);
	    ArrayList<String> lines = new ArrayList<String>();
	    
	    // Get all but comment lines.
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	    	while (scanner.hasNextLine()){
	    		String line = scanner.nextLine();
	    		if(Pattern.matches("\\s*#.*", line)) break;	// Comment line; ignore.
	    		if(Pattern.matches("", line)) break;	// Empty line; ignore.
	    		lines.add(line);
	    	}
	    } catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	    
	    // Remove empty lines.
	    for (String line : lines){
	    	if (line.length() == 0) lines.remove(line);
	    }
	    
	    // Convert lines
	}
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	/*
	void readLargerTextFile(String fileName) throws IOException {
	    Path path = Paths.get(fileName);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        //process each line in some way
	        log(scanner.nextLine());
	      }      
	    }
	 }
	 */
	
	/* Pattern pattern = 
            Pattern.compile(console.readLine("%nEnter your regex: "));

            Matcher matcher = 
            pattern.matcher(console.readLine("Enter input string to search: "));

            boolean found = false;
            while (matcher.find()) {
                console.format("I found the text" +
                    " \"%s\" starting at " +
                    "index %d and ending at index %d.%n",
                    matcher.group(),
                    matcher.start(),
                    matcher.end());
                found = true;
            }
            if(!found){
                console.format("No match found.%n");
            }
	 */
}
