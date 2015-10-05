package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import structs.State;
import structs.Symbol;
import structs.TransitionRule;
import util.StringProcessing;

/**
 * @author jose
 * 
 *         This class creates an automaton from a file. It parses the file and
 *         extracts from it the needed arguments for the Automaton class
 *         constructor.
 */
public abstract class AutomataCreator {
	
	ArrayList<ArrayList<String>> readAutomatonData(String fileName) throws IOException {
		
		// Read the file lines.
		ArrayList<String> lines = StringProcessing.readFileLines(fileName);
		
		// Remove comments.
		lines = StringProcessing.stripComments(lines);
		
		// Remove all empty lines.
		lines = StringProcessing.removeAllEmptyLines(lines);

		// Tokenize the remaining, valid lines.
		ArrayList<ArrayList<String>> tokenizedLines = StringProcessing.tokenizeLines(lines);
		
		return tokenizedLines;
	}
	
	/**
	 * Take automaton data, convert tokens into valid constructor arguments
	 * and call the Automaton constructor with them.
	 * @param automatonData
	 * @return
	 */
	/*
	Automaton createAutomatonFromData(ArrayList<ArrayList<String>> automatonData){
		

		// 4) Feed arguments into Automaton constructor.
		return new Automaton(stateSet, inputAlphabet, stackAlphabet,
				initialState, initialStackSymbol, transitionRules, acceptStates);
	};*/
}
