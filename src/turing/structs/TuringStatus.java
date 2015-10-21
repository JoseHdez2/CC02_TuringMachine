package turing.structs;

import java.util.ArrayList;

import automaton.structs.AutomatonStatus;
import common.structs.State;

/**
 * @author jose
 *  Temporal data structure used during a trace.
 *  
 *	Represents the status of a given Turing machine
 *  as a frozen frame during the evaluation of a string.
 */
public class TuringStatus extends AutomatonStatus{
	State currentState;
	String tape;
	Integer headPos;
	
	public TuringStatus(State currentState,
			String tape, 
			int headPos){
		this.currentState = currentState;
		this.tape = tape;
		this.headPos = headPos;
	}

	/*
	 * Equals and hashCode.
	 */
	
	public boolean equals(Object ob){
		if (ob == null) return false;
		if (ob.getClass() != getClass()) return false;
		TuringStatus other = (TuringStatus)ob;
		if (!currentState.equals(other.currentState)) return false;
		if (!tape.equals(other.tape)) return false;
		if (!headPos.equals(other.headPos)) return false;

		return true;
	}
	
	public int hashCode() {
		return 	currentState.hashCode() ^
				tape.hashCode() ^ 
				headPos.hashCode();
	}
	
    /*
     *  Getters.
     *  Setters do not exist, as objects of this class 
     *  are meant to be static representations.
     */

    public State getCurrentState() {
        return currentState;
    }

    public String getTape() {
        return tape;
    }

    public Integer getHeadPos() {
        return headPos;
    }

    @Override
    protected ArrayList<String> asStringArray() {
        
        ArrayList<String> strArr = new ArrayList<String>();
        
        
        // TODO Auto-generated method stub
        return strArr;
    }
    
}
