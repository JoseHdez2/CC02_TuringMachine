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
public abstract class AutomatonIO implements AutomatonIOConst{
    
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
	
    //
    // Output functions.
    //
    
    protected abstract Integer getTransOutputSize();
    protected abstract Integer getStatusOutputSize();
    
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
     * Produce the representation of an automaton status, according to the internal IO convention.
     * @param as Automaton status to be represented.
     * @return Array of strings representing the given automaton status.
     */
    public static ArrayList<String> getStatusAsTokenizedLine(AutomatonStatus as){
        
        ArrayList<String> tokenizedLine = dummyTokensLine(getStatusOutputSize());
        
        tokenizedLine = setOutputStatusTokens(tokenizedLine, as);
        
        return tokenizedLine;
    }
    
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
    
    protected static final String DUMMY_STRING = "dummy";
    
    protected static ArrayList<String> dummyTokensLine(int numberOfTokens){
        ArrayList<String> tokenizedLine = new ArrayList<String>();
        
        // TODO: Try to one-line this?
        for (int i = 0; i < numberOfTokens; i++){
            tokenizedLine.add(DUMMY_STRING);
        }
        
        return tokenizedLine;
    }
   
    /*
     * ABSTRACT METHODS
     * 
     * To be implemented by inheriting classes.
     */
    
    // TODO: Not really abstract, since Java can't do static AND abstract methods.
    
    /*
     * Abstract input methods.
     */
    
    /**
     * Reads prepared automaton data from a given file.
     * @param fileName Path to machine definition file.
     * @return Data structure that semantically represents the automaton definition.
     * @throws IOException
     */
    static protected AutomatonData readPreparedMachineData(TokenizedLines tokLines) {
        return null;
    }
    
    /**
     * Reads transition rules from an array of string arrays.
     * @param transitionLines  Lines with transition tokens.
     * @return Data structure that semantically represents a set of transition rules.
     */
    static protected HashSet<AutomatonTransition> readTransitionRules(TokenizedLines transitionLines) {
        return null;
    }
    
    /*
     * Abstract output methods.
     */
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param tr Transition rule to be represented.
     * @return Array of strings representing the given transition rule.
     */
    static protected ArrayList<String> getTransitionAsTokenizedLine(AutomatonTransition at) {
        return null;
    }
    
    /**
     * Given a tokenized line with the correct number of (dummy) tokens, 
     * write the tokens representing the AutomatonStatus in the correct order.
     * @param tokenizedLine Line with dummy tokens to be replaced.
     * @param as AutomatonStatus to be represented.
     * @return Line with meaningful tokens representing the given automaton status.
     */
    static protected ArrayList<String> setOutputStatusTokens(ArrayList<String> tokenizedLine, AutomatonStatus as) {
        return null;
    }
}
