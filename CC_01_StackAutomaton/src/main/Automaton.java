package main;

import java.util.Set;

import util.Sys;

public class Automaton {
	Set<State> stateSet;
	Set<Character> inputAlphabet;
	Set<Character> stackAlphabet;
	State initialState;
	Character initialStackSymbol;
	Set<TransitionRule> transitionRules;
	Set<State> acceptStates;
	
	Automaton(	Set<State> stateSet, 
				Set<Character> inputAlphabet,
				Set<Character> stackAlphabet,
				State initialState,
				Character initialStackSymbol,
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
	boolean evaluateString(String inputString){
		return false;
	}
}