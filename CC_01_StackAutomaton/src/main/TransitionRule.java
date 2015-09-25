package main;

import java.util.ArrayList;

/**
 * @author jose
 * 
 *	Class that defines a transition rule, 
 *	which the automata use to move between states.
 */
public class TransitionRule {
	State prevState;
	State nextState;
	Character requiredInputCharacter; // Must be first character in rest of input string.
	Symbol requiredStackSymbol;	// Must be in top of stack.
	ArrayList<Character> stackSymbolsToPush;
	
	TransitionRule(State prevState, State nextState, ArrayList<Character> stackSymbolsToPush){
		this.prevState = prevState;
		this.nextState = nextState;
		this.stackSymbolsToPush = stackSymbolsToPush;
	}
	
	/*
	 * Setters and getters.
	 */

	public State getPrevState() {
		return prevState;
	}

	public void setPrevState(State prevState) {
		this.prevState = prevState;
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	public ArrayList<Character> getStackSymbolsToPush() {
		return stackSymbolsToPush;
	}

	public void setStackSymbolsToPush(ArrayList<Character> stackSymbolsToPush) {
		this.stackSymbolsToPush = stackSymbolsToPush;
	}
}
