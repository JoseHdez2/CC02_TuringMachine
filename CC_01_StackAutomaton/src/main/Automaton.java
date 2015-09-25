package main;

import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;

import util.Sys;

public class Automaton {
	Set<State> stateSet;
	Set<Character> inputAlphabet;
	Set<Character> stackAlphabet;
	State initialState;
	Symbol initialStackSymbol;
	Set<TransitionRule> transitionRules;
	Set<State> acceptStates;
	
	Automaton(	Set<State> stateSet, 
				Set<Character> inputAlphabet,
				Set<Character> stackAlphabet,
				State initialState,
				Symbol initialStackSymbol,
				Set<State> acceptStates){
		this.stateSet = stateSet;
		this.inputAlphabet = inputAlphabet;
		this.stackAlphabet = stackAlphabet;
		this.initialState = initialState;
		this.initialStackSymbol = initialStackSymbol;
		
		// TODO make sure all arguments are correct.
		
		// Make sure all acceptStates are in stateSet.
		for (State s : acceptStates){
			if(!stateSet.contains(s))
				Sys.abort("Algun estado de aceptacion no se encuentra entre los estados definidos.");
		}
	}
	
	/**
	 * Evaluates an input string. The main purpose of a logical automata.
	 * @return	Boolean indicating acceptance of input string.
	 */
	public boolean evaluateString(String inputString){
		ArrayList<AutomatonStatus> possibleStatuses = new ArrayList<AutomatonStatus>();
		// Add the initial configuration into the statuses array.
		Stack<Symbol> initialStack = new Stack<Symbol>();
		initialStack.push(initialStackSymbol);
		possibleStatuses.add(new AutomatonStatus(initialState, inputString, initialStack));
		// Iterate until all possibilities are exhausted.
		for (AutomatonStatus as : possibleStatuses){
			
		}
		// If all possibilities have been exhausted and no
		return false;
	}
}