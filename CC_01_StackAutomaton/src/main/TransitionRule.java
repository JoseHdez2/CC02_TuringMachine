package main;

import java.util.ArrayList;

public class TransitionRule {
	State prevState;
	State nextState;
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
