package automaton.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import automaton.structs.AutomatonTransition;
import util.StringProcessing;
import util.TokenizedLines;

/**
 * @author jose
 * 
 * Extracts and gives semantic meaning
 * to Automaton data stored as raw text.
 * 
 * Also used to represent data in raw strings.
 * 
 * Only class that knows the input/output
 * convention (data format) used.
 */
public abstract class AutomatonIO extends AutomatonIOConst{
    
    /*
     * Input functions.
     */
    
	/**
	 * Reads automaton data from a given file.
	 * @param fileName Path to machine definition file.
	 * @return Data structure that semantically represents the automaton definition.
	 * @throws IOException
	 */
	public static AutomatonData readAutomatonData(String fileName) throws IOException {
	    
	    TokenizedLines tokLines = prepareAutomatonData(fileName);

	    return readPreparedMachineData(tokLines);
	}
	
	/**
     * Reads prepared automaton data from a given file.
     * @param fileName Path to machine definition file.
     * @return Data structure that semantically represents the automaton definition.
     * @throws IOException
     */
	protected static AutomatonData readPreparedMachineData(TokenizedLines tokLines) {
	    // TODO Java doesn't allow for abstract static methods (language shortcoming).
        return null;
    }
    
	/**
	 * Reads transition rules from an array of string arrays.
	 * @param transitionLines  Lines with transition tokens.
	 * @return Data structure that semantically represents a set of transition rules.
	 */
	protected abstract HashSet<AutomatonTransition> readTransitionRules(TokenizedLines transitionLines);
	
	/**
     * Standardizes input file, preparing it for automaton data extraction.
     * @param fileName Path to an automaton file (arbitrary file structure)
     * @return Standardized (expected) lines for reading automaton data.
     * @throws IOException
     */
    protected static TokenizedLines prepareAutomatonData(String fileName) throws IOException {
        
        ArrayList<String> lines = StringProcessing.readFileLines(fileName);
        
        lines = StringProcessing.stripComments(lines, "#");
        
        lines = StringProcessing.removeAllEmptyLines(lines);

        TokenizedLines tokenizedLines = StringProcessing.tokenizeLines(lines, "\\s+");
        
        return tokenizedLines;
    }
	
    /*
     * Output functions.
     */
    
    protected static final String DUMMY_STRING = "dummy";
    protected static final Integer TRANS_OUTPUT_SIZE = null;
    protected static final Integer STATUS_OUTPUT_SIZE = null;
    
    /**
     * Produce the representation of all of the transitions in 
     * the automata definition, according to the internal IO convention.
     * @param ad Automaton definition data.
     * @return Array of string arrays representing all the transition rules.
     */
    public static TokenizedLines getTransitionsAsTokenizedLines(AutomatonData ad){
        
        TokenizedLines tl = new TokenizedLines();
        
        for (AutomatonTransition tr : ad.getTransitionRules()){            
            tl.add(getTransitionAsTokenizedLine(tr));
        }
        
        return tl;
    }
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param tr Transition rule to be represented.
     * @return Array of strings representing the given transition rule.
     */
    protected static ArrayList<String> getTransitionAsTokenizedLine(AutomatonTransition at) {
        // TODO Java doesn't allow for abstract static methods (language shortcoming).
        return null;
    }
    
    // TODO further modularize tokenLine output.
    
    /* ------------------------------------------------
     * Output status.
     * ------------------------------------------------ /
    
    /**
     * Produce the representation of an automaton status, according to the internal IO convention.
     * @param as Automaton status to be represented.
     * @return Array of strings representing the given automaton status.
     */
    public static ArrayList<String> getStatusAsTokenizedLine(AutomatonStatus as){
        
        ArrayList<String> tokenizedLine = dummyTokensLine(STATUS_OUTPUT_SIZE);
        
        tokenizedLine = setOutputTokens(tokenizedLine, as);
        
        return tokenizedLine;
    }
    
    protected static ArrayList<String> setOutputTokens(ArrayList<String> tokenizedLine, AutomatonStatus as) {
        // TODO Java doesn't allow for abstract static methods (language shortcoming).
        return null;
    }
    
    /* ------------------------------------------------
     * Output trace.
     * ------------------------------------------------ /
    
    /**
     * Produce the representation of a trace trail, according to the internal IO convention.
     * @param tt Trace trail to be represented.
     * @return  Array of string arrays representing the given trace trail.
     */
    public static TokenizedLines traceTrailAsTokenizedLines(TraceTrail tt){
        
        TokenizedLines tokenizedLines = new TokenizedLines();
        
        for (AutomatonStatus as : tt){
            tokenizedLines.add(getStatusAsTokenizedLine(as));
        }
        
        return tokenizedLines;
    }
    
    protected static ArrayList<String> dummyTokensLine(int numberOfTokens){
        ArrayList<String> tokenizedLine = new ArrayList<String>();
        
        // TODO: Try to one-line this?
        for (int i = 0; i < numberOfTokens; i++){
            tokenizedLine.add(DUMMY_STRING);
        }
        
        return tokenizedLine;
    }
   
}