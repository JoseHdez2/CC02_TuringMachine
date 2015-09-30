package main;

import java.util.HashSet;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) {
		System.out.println("This is a program, hello.");
		HashSet<State> stateSet = new HashSet<State>();
		HashSet<Character> inputAlphabet = new HashSet<Character>();
		HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
		State initialState = new State("a");
		Symbol initialStackSymbol = new Symbol("S");
		HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
		HashSet<State> acceptStates = new HashSet<State>();
		Automaton a = new Automaton(stateSet, inputAlphabet, stackAlphabet,
							initialState, initialStackSymbol, transitionRules, 
							acceptStates);
		a.evaluateString("programDestroyer");
		if (Pattern.matches("#.*", "# mi comment")) System.out.println("bien");
		
	}
}
