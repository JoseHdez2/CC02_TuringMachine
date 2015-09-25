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
	 * Equals and hashCode.
	 */
	
	public boolean equals(Object ob){
		if (ob == null) return false;
		if (ob.getClass() != getClass()) return false;
		TransitionRule other = (TransitionRule)ob;
		if (!prevState.equals(other.prevState)) return false;
		// TODO rest of them
		return true;
	}
	
	public int hashCode() {
		return prevState.hashCode() ^ nextState.hashCode() ^ 
				requiredInputCharacter.hashCode() ^ requiredStackSymbol.hashCode() ^
				stackSymbolsToPush.hashCode();
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

	public Character getRequiredInputCharacter() {
		return requiredInputCharacter;
	}

	public void setRequiredInputCharacter(Character requiredInputCharacter) {
		this.requiredInputCharacter = requiredInputCharacter;
	}

	public Symbol getRequiredStackSymbol() {
		return requiredStackSymbol;
	}

	public void setRequiredStackSymbol(Symbol requiredStackSymbol) {
		this.requiredStackSymbol = requiredStackSymbol;
	}
	
	public ArrayList<Character> getStackSymbolsToPush() {
		return stackSymbolsToPush;
	}

	public void setStackSymbolsToPush(ArrayList<Character> stackSymbolsToPush) {
		this.stackSymbolsToPush = stackSymbolsToPush;
	}
}
