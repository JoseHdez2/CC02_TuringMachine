package main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	// Regexes for detecting useless lines.
	final String LINE_EMPTY = "\\s*";
	final String LINE_COMMENT = LINE_EMPTY + "#.*";

	Automaton createAutomaton(String fileName) throws IOException {
		
	    Path path = Paths.get(fileName);
	    ArrayList<String> lines = new ArrayList<String>();
	    
	    // 1) Read file. Get all but comment lines.
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	    	while (scanner.hasNextLine()){
	    		String line = scanner.nextLine();
	    		if(Pattern.matches(LINE_COMMENT, line)) break;	// Comment line; ignore.
	    		if(Pattern.matches(LINE_EMPTY, line)) break;	// Empty line; ignore.
	    		lines.add(line);
	    	}
	    } catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	    
	    // 2) Convert valid lines into arguments.
	    
	    ArrayList<ArrayList<String>> splat = new ArrayList<ArrayList<String>>();
	    
	    // For each line: remove whitespace and tokenize.
	    for (String str : lines){
	    	ArrayList<String> splaat = 
	    			new ArrayList<String>(Arrays.asList(str.trim().split("\\s+"))); 
	    	splat.add(splaat);
	    }
	    
	    HashSet<State> stateSet = new HashSet<State>();
	    String[] split = lines.get(0).split("\\s+");
	    for (String str : split){
	    	stateSet.add(new State(str));
	    }
	    
	    HashSet<Character> inputAlphabet = new HashSet<Character>();
	    split = lines.get(1).split("\\s+");
	    for (String str : split){
	    	inputAlphabet.add(str.charAt(0));
	    }
	    
	    HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
	    split = lines.get(2).trim().split("\\s+");
	    for (String str : split){
	    	stackAlphabet.add(new Symbol(str));
	    }
	    
		State initialState = new State(lines.get(3).trim());
		
		Symbol initialStackSymbol = new Symbol(lines.get(4).trim());
		
		HashSet<State> acceptStates = new HashSet<State>();
		split = lines.get(4).trim().split("\\s+");
		for (String str : split){
			stateSet.add(new State(str));
		}
		
		HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
		ArrayList<String> tLines = (ArrayList<String>)lines.subList(5, lines.size());
		
	    // 3) Feed arguments into Automaton constructor.
	    return new Automaton(
	    		stateSet,
	    		inputAlphabet,
	    		stackAlphabet,
	    		initialState,
	    		initialStackSymbol,
	    		transitionRules,
	    		acceptStates);
	}
}
