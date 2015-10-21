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
    
    //
    // Input functions.
    //
    
	/**
	 * Reads automaton data from a given file.
	 * @param fileName Path to machine definition file.
	 * @return Data structure that semantically represents the automaton definition.
	 * @throws IOException
	 */
	public AutomatonData readAutomatonData(String fileName) throws IOException {
	    
	    TokenizedLines tokLines = prepareAutomatonData(fileName);

	    return readPreparedMachineData(tokLines);
	}
	
    //
    // Output functions.
    //
    
    /**
     * Produce the representation of all of the transitions in 
     * the automata definition, according to the internal IO convention.
     * @param ad Automaton definition data.
     * @return Array of string arrays representing all the transition rules.
     */
    public TokenizedLines getTransitionsAsTokenizedLines(AutomatonData ad){
        
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
    public ArrayList<String> getStatusAsTokenizedLine(AutomatonStatus as){
        
        ArrayList<String> tokenizedLine = dummyTokensLine(getStatusOutputSize());
        
        tokenizedLine = setOutputStatusTokens(tokenizedLine, as);
        
        return tokenizedLine;
    }
    

    
    /*
     * PROTECTED METHODS
     * Low-level methods that are only called internally.
     */
    
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
     * ABSTRACT METHODS
     * To be implemented by inheriting classes.
     */
    
    /*
     * Abstract input methods.
     */
    
    /**
     * Reads prepared automaton data from a given file.
     * @param fileName Path to machine definition file.
     * @return Data structure that semantically represents the automaton definition.
     * @throws IOException
     */
    protected abstract AutomatonData readPreparedMachineData(TokenizedLines tokLines);
    
    /**
     * Reads transition rules from an array of string arrays.
     * @param transitionLines  Lines with transition tokens.
     * @return Data structure that semantically represents a set of transition rules.
     */
    protected abstract HashSet<AutomatonTransition> readTransitionRules(TokenizedLines transitionLines);
    
    /*
     * Abstract output methods.
     */
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param tr Transition rule to be represented.
     * @return Array of strings representing the given transition rule.
     */
    protected abstract ArrayList<String> getTransitionAsTokenizedLine(AutomatonTransition at);
    
    /**
     * Given a tokenized line with the correct number of (dummy) tokens, 
     * write the tokens representing the AutomatonStatus in the correct order.
     * @param tokenizedLine Line with dummy tokens to be replaced.
     * @param as AutomatonStatus to be represented.
     * @return Line with meaningful tokens representing the given automaton status.
     */
    protected abstract ArrayList<String> setOutputStatusTokens(ArrayList<String> tokenizedLine, AutomatonStatus as);
    
    /**
     * @return Number of tokens a representation of a transition has.
     */
    protected abstract Integer getTransOutputSize();
    
    /**
     * @return Number of tokens a representation of a status has.
     */
    protected abstract Integer getStatusOutputSize();
}
