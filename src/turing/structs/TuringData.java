package turing.structs;

import java.util.HashSet;

import util.TokenizedLines;

import common.structs.State;

/**
 * @author jose
 *
 *  Semantically represents the data 
 *  of an automaton definition.
 *  
 *  Extracted and given semantic meaning 
 *  by the AutomataIO class.
 */

@SuppressWarnings("serial")
public class TuringData extends TokenizedLines {

    HashSet<State> stateSet;
    HashSet<Character> inputAlphabet;
    HashSet<Character> outputAlphabet;
    State initialState;
    Character blankCharacter;
    HashSet<State> acceptStates;
    HashSet<TuringTransition> turingTransitions;
    
    public TuringData(HashSet<State> stateSet, 
                HashSet<Character> inputAlphabet,
                HashSet<Character> outputAlphabet,
                State initialState,
                Character blankCharacter,
                HashSet<TuringTransition> turingTransitions,
                HashSet<State> acceptStates){
        this.stateSet = stateSet;
        this.inputAlphabet = inputAlphabet;
        this.outputAlphabet = outputAlphabet;
        this.initialState = initialState;
        this.blankCharacter = blankCharacter;
        this.turingTransitions = turingTransitions;
        this.acceptStates = acceptStates;
    }

    /*
     * Getters and setters.
     */
    
    public HashSet<State> getStateSet() {
        return stateSet;
    }

    public void setStateSet(HashSet<State> stateSet) {
        this.stateSet = stateSet;
    }

    public HashSet<Character> getInputAlphabet() {
        return inputAlphabet;
    }

    public void setInputAlphabet(HashSet<Character> inputAlphabet) {
        this.inputAlphabet = inputAlphabet;
    }

    public HashSet<Character> getOutputAlphabet() {
        return outputAlphabet;
    }

    public void setOutputAlphabet(HashSet<Character> outputAlphabet) {
        this.outputAlphabet = outputAlphabet;
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public Character getBlankCharacter() {
        return blankCharacter;
    }

    public void setBlankCharacter(Character blankCharacter) {
        this.blankCharacter = blankCharacter;
    }

    public HashSet<State> getAcceptStates() {
        return acceptStates;
    }

    public void setAcceptStates(HashSet<State> acceptStates) {
        this.acceptStates = acceptStates;
    }

    public HashSet<TuringTransition> getTransitionRules() {
        return turingTransitions;
    }

    public void setTransitionRules(HashSet<TuringTransition> turingTransitions) {
        this.turingTransitions = turingTransitions;
    }
    
}
