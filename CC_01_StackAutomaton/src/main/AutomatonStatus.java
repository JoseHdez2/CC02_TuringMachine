package main;

import java.util.Stack;

/**
 * @author jose
 *	A tuple that represents the status of a given automaton.
 *	It holds the current state, the remaining string to be evaluated
 *	and the status of the stack. 
 */
public class AutomatonStatus {
	State currentState;
	String remainingInputString;
	Stack<Symbol> currentStack;
	
	AutomatonStatus(State currentState,
			String remainingInputString, 
			Stack<Symbol> currentStack){
		this.currentState = currentState;
		this.remainingInputString = remainingInputString;
		this.currentStack = currentStack;
	}
	
	/*
	 *  Getters and setters.
	 */

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public String getRemainingInputString() {
		return remainingInputString;
	}

	public void setRemainingInputString(String remainingInputString) {
		this.remainingInputString = remainingInputString;
	}

	public Stack<Symbol> getCurrentStack() {
		return currentStack;
	}

	public void setCurrentStack(Stack<Symbol> currentStack) {
		this.currentStack = currentStack;
	}
}
