package turing.structs;

import java.util.HashSet;

import automaton.structs.AutomatonData;
import automaton.structs.AutomatonTransitionSet;
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
public class TuringData extends AutomatonData {

    HashSet<State> stateSet;
    HashSet<Character> inputAlphabet;
    HashSet<Character> outputAlphabet;
    State initialState;
    Character blankCharacter;
    HashSet<State> acceptStates;
    
    public TuringData(HashSet<State> stateSet, 
                HashSet<Character> inputAlphabet,
                HashSet<Character> outputAlphabet,
                State initialState,
                Character blankCharacter,
                AutomatonTransitionSet transitionRules,
                HashSet<State> acceptStates){
        super(stateSet, inputAlphabet, initialState, acceptStates, transitionRules);
        
        this.outputAlphabet = outputAlphabet;
        this.blankCharacter = blankCharacter;
    }

    /*
     * Getters.
     * No setters since this is considered a static object.
     */

    
}
