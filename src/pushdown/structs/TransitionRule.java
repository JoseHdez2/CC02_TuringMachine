package pushdown.structs;

import java.util.ArrayList;

/**
 * @author jose
 * 
 *	Represents a transition rule, 
 *	which the automata use to move between states.
 */
public class TransitionRule {
	State prevState;
	State nextState;
	Character requiredInputCharacter; // Must be first character in rest of input string.
	Symbol requiredStackSymbol;	// Must be in top of stack.
	ArrayList<Symbol> stackSymbolsToPush;
	
	public TransitionRule(State prevState, State nextState,
			Character requiredInputCharacter,
			Symbol requiredStackSymbol,
			ArrayList<Symbol> stackSymbolsToPush){
		this.prevState = prevState;
		this.nextState = nextState;
		this.requiredInputCharacter = requiredInputCharacter;
		this.requiredStackSymbol = requiredStackSymbol;
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
		if (!nextState.equals(other.nextState)) return false;
		if (!requiredInputCharacter.equals(other.requiredInputCharacter)) return false;
		if (!requiredStackSymbol.equals(other.requiredStackSymbol)) return false;
		if (!stackSymbolsToPush.equals(other.stackSymbolsToPush)) return false;

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
	
	public ArrayList<Symbol> getStackSymbolsToPush() {
		return stackSymbolsToPush;
	}

	public void setStackSymbolsToPush(ArrayList<Symbol> stackSymbolsToPush) {
		this.stackSymbolsToPush = stackSymbolsToPush;
	}
}
