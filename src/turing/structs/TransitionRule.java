package turing.structs;

import common.structs.State;

/**
 * @author jose
 * 
 *	Represents a transition rule, 
 *	which the automata use to move between states.
 */
public class TransitionRule {
	State prevState;
	State nextState;
	Character inputCharacter;
	Character outputCharacter;
	Movement movement;
	
	public TransitionRule(State prevState, State nextState,
			Character inputCharacter,
			Character outputCharacter,
			Movement movement){
		this.prevState = prevState;
		this.nextState = nextState;
		this.inputCharacter = inputCharacter;
		this.outputCharacter = outputCharacter;
		this.movement = movement;
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
		if (!inputCharacter.equals(other.inputCharacter)) return false;
		if (!outputCharacter.equals(other.outputCharacter)) return false;
		if (!(movement == other.movement)) return false;

		return true;
	}
	
	public int hashCode() {
		return prevState.hashCode() ^ nextState.hashCode() ^ 
				inputCharacter.hashCode() ^ outputCharacter.hashCode() ^
				movement.hashCode();
	}

    /**
     * @return the prevState
     */
    public State getPrevState() {
        return prevState;
    }

    /**
     * @return the nextState
     */
    public State getNextState() {
        return nextState;
    }

    /**
     * @return the inputCharacter
     */
    public Character getInputCharacter() {
        return inputCharacter;
    }

    /**
     * @return the outputCharacter
     */
    public Character getOutputCharacter() {
        return outputCharacter;
    }

    /**
     * @return the movement
     */
    public Movement getMovement() {
        return movement;
    }


}
