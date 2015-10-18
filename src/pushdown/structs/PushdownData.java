package pushdown.structs;

import java.util.HashSet;

import util.TokenizedLines;

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
public class PushdownData extends TokenizedLines {

    HashSet<State> stateSet;
    HashSet<Character> inputAlphabet;
    HashSet<Symbol> stackAlphabet;
    State initialState;
    Symbol initialStackSymbol;
    HashSet<PushdownTransition> pushdownTransitions;
    HashSet<State> acceptStates;
    
    public PushdownData(HashSet<State> stateSet, 
                HashSet<Character> inputAlphabet,
                HashSet<Symbol> stackAlphabet,
                State initialState,
                Symbol initialStackSymbol,
                HashSet<PushdownTransition> pushdownTransitions,
                HashSet<State> acceptStates){
        this.stateSet = stateSet;
        this.inputAlphabet = inputAlphabet;
        this.stackAlphabet = stackAlphabet;
        this.initialState = initialState;
        this.initialStackSymbol = initialStackSymbol;
        this.pushdownTransitions = pushdownTransitions;
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
    public HashSet<Symbol> getStackAlphabet() {
        return stackAlphabet;
    }
    public void setStackAlphabet(HashSet<Symbol> stackAlphabet) {
        this.stackAlphabet = stackAlphabet;
    }
    public State getInitialState() {
        return initialState;
    }
    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }
    public Symbol getInitialStackSymbol() {
        return initialStackSymbol;
    }
    public void setInitialStackSymbol(Symbol initialStackSymbol) {
        this.initialStackSymbol = initialStackSymbol;
    }
    public HashSet<PushdownTransition> getTransitionRules() {
        return pushdownTransitions;
    }
    public void setTransitionRules(HashSet<PushdownTransition> pushdownTransitions) {
        this.pushdownTransitions = pushdownTransitions;
    }
    public HashSet<State> getAcceptStates() {
        return acceptStates;
    }
    public void setAcceptStates(HashSet<State> acceptStates) {
        this.acceptStates = acceptStates;
    }
    
    
}
