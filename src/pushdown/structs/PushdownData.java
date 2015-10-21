package pushdown.structs;

import java.util.HashSet;

import automaton.structs.AutomatonData;
import common.structs.State;
import common.structs.Symbol;

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
public class PushdownData extends AutomatonData {

    HashSet<State> stateSet;
    HashSet<Character> inputAlphabet;
    HashSet<Symbol> stackAlphabet;
    State initialState;
    Symbol initialStackSymbol;
    PushdownTransitionSet pushdownTransitions;
    HashSet<State> acceptStates;
    
    public PushdownData(HashSet<State> stateSet, 
                HashSet<Character> inputAlphabet,
                HashSet<Symbol> stackAlphabet,
                State initialState,
                Symbol initialStackSymbol,
                PushdownTransitionSet transitionRules,
                HashSet<State> acceptStates){
        super(stateSet, inputAlphabet, initialState, acceptStates, transitionRules);

        this.stackAlphabet = stackAlphabet;
        this.initialStackSymbol = initialStackSymbol;
    }
    
    /*
     * Getters.
     * No setters since this is considered a static object.
     */
    
    
}
