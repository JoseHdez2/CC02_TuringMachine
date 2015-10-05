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
	Automaton createAutomatonFromData(ArrayList<ArrayList<String>> automatonData){
		
		HashSet<State> stateSet = new HashSet<State>();
		for (String token : automatonData.get(0)) {
			stateSet.add(new State(token));
		}

		HashSet<Character> inputAlphabet = new HashSet<Character>();
		for (String token : automatonData.get(1)) {
			inputAlphabet.add(token.charAt(0));
		}

		HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
		for (String token : automatonData.get(2)) {
			stackAlphabet.add(new Symbol(token));
		}

		State initialState = new State(automatonData.get(3).get(0));

		Symbol initialStackSymbol = new Symbol(automatonData.get(4).get(0));

		HashSet<State> acceptStates = new HashSet<State>();
		for (String str : automatonData.get(4)) {
			stateSet.add(new State(str));
		}
		
		HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
		for (ArrayList<String> tl : automatonData.subList(5, automatonData.size())){
			State prevState = new State(tl.get(0));
			Character requiredInputCharacter = tl.get(1).charAt(0);
			Symbol requiredStackSymbol = new Symbol(tl.get(2));
			State nextState = new State(tl.get(3));
			ArrayList<Character> stackSymbolsToPush = new ArrayList<Character>(tl.get(4).charAt(0));
			transitionRules.add(new TransitionRule(prevState, nextState, 
					requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
		}

		// 4) Feed arguments into Automaton constructor.
		return new Automaton(stateSet, inputAlphabet, stackAlphabet,
				initialState, initialStackSymbol, transitionRules, acceptStates);
	};
}
