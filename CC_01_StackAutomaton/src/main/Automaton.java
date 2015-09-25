package main;

import java.util.Set;

public class Automaton {
	Set<State> stateSet;
	Set<Character> inputAlphabet;
	Set<Character> stackAlphabet;
	Character 	initialState,
				initialStackSymbol;
	Set<State> acceptStates;
	
	Automaton(	Set<State> stateSet, 
				Set<Character> inputAlphabet,
				Set<Character> stackAlphabet,
				Character initialState,
				Character initialStackSymbol,
				Set<State> acceptStates){
		this.stateSet = stateSet;
		this.inputAlphabet = inputAlphabet;
		this.stackAlphabet = stackAlphabet;
		this.initialState = initialState;
		this.initialStackSymbol = initialStackSymbol;
	}
	
}
