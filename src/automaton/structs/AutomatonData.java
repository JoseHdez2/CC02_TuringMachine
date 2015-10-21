package automaton.structs;

import java.util.HashSet;

import util.TokenizedLines;

/**
 * @author jose
 *
 *  Semantically represents the data 
 *  of an automaton definition.
 *  
 *  Extracted and given semantic meaning 
 *  by the MachineIO class.
 */
@SuppressWarnings("serial")
public abstract class AutomatonData extends TokenizedLines {

    protected HashSet<State> stateSet;
    protected HashSet<Character> inputAlphabet;
    protected State initialState;
    protected HashSet<State> acceptStates;
    protected AutomatonTransitionSet transitionRules;
    
    public AutomatonData(HashSet<State> stateSet,
        HashSet<Character> inputAlphabet,
        State initialState,
        HashSet<State> acceptStates,
        AutomatonTransitionSet transitionRules){
        
        this.stateSet = stateSet;
        this.inputAlphabet = inputAlphabet;
        this.initialState = initialState;
        this.acceptStates = acceptStates;
        this.transitionRules = transitionRules;
    }
    
    public AutomatonData(){};
    
//    public abstract AutomatonData(String fullFilePath);
    
    /*
     * Getters.
     * No setters since this is considered a static object.
     */

    public AutomatonTransitionSet getTransitionRules() {
        return transitionRules;
    }

    public HashSet<State> getStateSet() {
        return stateSet;
    }

    public HashSet<Character> getInputAlphabet() {
        return inputAlphabet;
    }

    public State getInitialState() {
        return initialState;
    }

    public HashSet<State> getAcceptStates() {
        return acceptStates;
    }
}
