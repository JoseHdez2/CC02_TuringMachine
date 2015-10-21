package automaton.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import automaton.structs.AutomatonTransition;
import util.StringArray;
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
}
