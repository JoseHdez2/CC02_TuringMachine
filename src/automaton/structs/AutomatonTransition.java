package automaton.structs;

import common.structs.State;
import util.StringArray;

/**
 * @author jose
 * 
 *	Represents a transition rule, 
 *	which the automata use to move between states.
 */
public class AutomatonTransition {
    State prevState;
    State nextState;
    Character inputCharacter;
    
    public AutomatonTransition(State prevState, State nextState, Character inputCharacter){
        this.prevState = prevState;
        this.nextState = nextState;
        this.inputCharacter = inputCharacter;
    }
    
    public StringArray asStringArray(){
        // TODO: Implement
        return new StringArray();
    }
    
    /*
     * Equals and hashCode.
     */
    
    public boolean equals(Object ob){
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        AutomatonTransition other = (AutomatonTransition)ob;
        if (!prevState.equals(other.prevState)) return false;
        if (!nextState.equals(other.nextState)) return false;
        if (!inputCharacter.equals(other.inputCharacter)) return false;

        return true;
    }
    
    public int hashCode() {
        return prevState.hashCode() ^ nextState.hashCode() ^ 
                inputCharacter.hashCode();
    }

    /*
     * Getters.
     * No setters since this is considered a static object.
     */
    
    public State getPrevState() {
        return prevState;
    }

    public State getNextState() {
        return nextState;
    }

    public Character getInputCharacter() {
        return inputCharacter;
    }
}
