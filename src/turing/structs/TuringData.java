package turing.structs;

import java.util.HashSet;

import automaton.structs.AutomatonData;
import automaton.structs.AutomatonTransitionSet;
import automaton.structs.State;
import automaton.structs.Symbol;

/**
 * @author jose
 *
 *  Semantically represents the data 
 *  of an automaton definition.
 */

@SuppressWarnings("serial")
public class TuringData extends AutomatonData {

    HashSet<Symbol> outputAlphabet;
    Symbol blankSymbol;
    
    public TuringData(HashSet<State> stateSet, 
                HashSet<Symbol> inputAlphabet,
                HashSet<Symbol> outputAlphabet,
                State initialState,
                Symbol blankSymbol,
                AutomatonTransitionSet transitionRules,
                HashSet<State> acceptStates){
        super(stateSet, inputAlphabet, initialState, acceptStates, transitionRules);
        
        this.outputAlphabet = outputAlphabet;
        this.blankSymbol = blankSymbol;
    }
    
    public TuringData(String fullFilePath){
        
    }

    public TuringData() {};

    /*
     * Getters.
     * No setters since this is considered a static object.
     */

    
}
