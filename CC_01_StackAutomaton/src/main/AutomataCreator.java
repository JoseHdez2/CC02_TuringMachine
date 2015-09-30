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
 *         This class creates an automaton from a file. It parses the file and
 *         extracts from it the needed arguments for the Automaton class
 *         constructor.
 */
public abstract class AutomataCreator {

	final static Charset ENCODING = StandardCharsets.UTF_8;
	final String EMPTY_LINE = "\\s*";

	Automaton createAutomaton(String fileName) throws IOException {

		Path path = Paths.get(fileName);
		ArrayList<String> lines = new ArrayList<String>();

		// 1) Read file. Get all but comment lines.
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 2) Convert valid lines into arguments.

		ArrayList<String> emptyLines = new ArrayList<String>();
		ArrayList<ArrayList<String>> tokenizedLines = new ArrayList<ArrayList<String>>();

		// Discard useless lines and tokenize valid ones.
		for (String line : lines) {
			if (Pattern.matches(EMPTY_LINE, line)) {
				emptyLines.add(line);
				continue;
			}
			ArrayList<String> tokenizedLine = new ArrayList<String>(
					Arrays.asList(line.trim().split("\\s+")));
			tokenizedLines.add(tokenizedLine);
		}

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
		ArrayList<ArrayList<String>> transLines = 
				(ArrayList<ArrayList<String>>)tokenizedLines.subList(5, tokenizedLines.size());
		
		for (ArrayList<String> tl : transLines){
			State prevState = new State(tl.get(0));
			Character requiredInputCharacter = tl.get(1).charAt(0);
			Symbol requiredStackSymbol = new Symbol(tl.get(2));
			State nextState = new State(tl.get(3));
			ArrayList<Character> stackSymbolsToPush = new ArrayList<Character>(tl.get(4).charAt(0));
			transitionRules.add(new TransitionRule(prevState, nextState, 
					requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
		}

		// 3) Feed arguments into Automaton constructor.
		return new Automaton(stateSet, inputAlphabet, stackAlphabet,
				initialState, initialStackSymbol, transitionRules, acceptStates);
	}
}
