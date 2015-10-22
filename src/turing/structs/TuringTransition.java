package turing.structs;

import automaton.structs.AutomatonTransition;
import automaton.structs.State;
import automaton.structs.Symbol;

/**
 * @author jose
 * 
 *	Represents a transition rule, 
 *	which the automata use to move between states.
 */
public class TuringTransition extends AutomatonTransition {

	Symbol outputCharacter;
	Movement movement;
	
	public TuringTransition(State prevState, State nextState,
			Symbol inputCharacter,
			Symbol outputCharacter,
			Movement movement){
	    super(prevState, nextState, inputCharacter);
		this.outputCharacter = outputCharacter;
		this.movement = movement;
	}
	
	/*
	 * Equals and hashCode.
	 */
	
	public boolean equals(Object ob){
	    if (!super.equals(ob)) return false;
	    
	    TuringTransition other = (TuringTransition)ob;
		if (!outputCharacter.equals(other.outputCharacter)) return false;
		if (!movement.equals(other.movement)) return false;

		return true;
	}
	
	public int hashCode() {
		return super.hashCode() ^ outputCharacter.hashCode() ^
				movement.hashCode();
	}

    /*
     * Getters.
     * No setters since this is considered a static object.
     */

    public Symbol getOutputCharacter() {
        return outputCharacter;
    }

    public Movement getMovement() {
        return movement;
    }


}
