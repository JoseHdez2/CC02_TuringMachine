package main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

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
		for (String token : tokenizedLines.get(0)) {
			stateSet.add(new State(token));
		}

		HashSet<Character> inputAlphabet = new HashSet<Character>();
		for (String token : tokenizedLines.get(1)) {
			inputAlphabet.add(token.charAt(0));
		}

		HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
		for (String token : tokenizedLines.get(2)) {
			stackAlphabet.add(new Symbol(token));
		}

		State initialState = new State(lines.get(3).trim());

		Symbol initialStackSymbol = new Symbol(lines.get(4).trim());

		HashSet<State> acceptStates = new HashSet<State>();
		for (String str : tokenizedLines.get(4)) {
			stateSet.add(new State(str));
		}
		
		HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
		for (ArrayList<String> tl : tokenizedLines.subList(5, tokenizedLines.size())){
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
	
	Automaton createAutomatonOld(String fileName) throws IOException {

		Path path = Paths.get(fileName);
		ArrayList<String> lines = new ArrayList<String>();

		// 1) Read file lines.
		
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2) Tokenize valid lines and discard useless ones.

		ArrayList<String> emptyLines = new ArrayList<String>();
		ArrayList<ArrayList<String>> tokenizedLines = new ArrayList<ArrayList<String>>();

		for (String line : lines) {
			if (Pattern.matches(EMPTY_LINE, line)) {
				emptyLines.add(line);
				continue;
			}
			ArrayList<String> tokenizedLine = new ArrayList<String>(
					Arrays.asList(line.trim().split("\\s+")));
			tokenizedLines.add(tokenizedLine);
		}
		lines.removeAll(emptyLines);

		// 3) Convert tokens into valid constructor arguments.
		
		HashSet<State> stateSet = new HashSet<State>();
		for (String token : tokenizedLines.get(0)) {
			stateSet.add(new State(token));
		}

		HashSet<Character> inputAlphabet = new HashSet<Character>();
		for (String token : tokenizedLines.get(1)) {
			inputAlphabet.add(token.charAt(0));
		}

		HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
		for (String token : tokenizedLines.get(2)) {
			stackAlphabet.add(new Symbol(token));
		}

		State initialState = new State(lines.get(3).trim());

		Symbol initialStackSymbol = new Symbol(lines.get(4).trim());

		HashSet<State> acceptStates = new HashSet<State>();
		for (String str : tokenizedLines.get(4)) {
			stateSet.add(new State(str));
		}

		HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
		for (ArrayList<String> tl : tokenizedLines.subList(5, tokenizedLines.size())){
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
	}
}
