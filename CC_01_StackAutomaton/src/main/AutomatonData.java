package main;

import java.util.HashSet;

import structs.State;
import structs.Symbol;
import structs.TransitionRule;
import util.TokenizedLines;

/**
 * @author jose
 *
 *  Represents the literal data of an automata, extracted by AutomataCreator.
 *  Also helps AutomataCreator by providing the correct arguments
 *  for the Automaton constructor.
 *  The only class that 'knows' the low-level automata file structure.
 */
public class AutomatonData extends TokenizedLines {
    
    // Arbitrary automaton file structure constants.
    // Tokenized lines where each of the datums are located.
    int TOK_LN_STATE_SET = 0;
    int TOK_LN_STRING_ALPH = 1;
    int TOK_LN_STACK_ALPH = 2;
    int TOK_LN_INIT_STATE = 3;
    int TOK_LN_INIT_STACK = 4;
    int TOK_LN_ACCEPT_STATE = 5;
    // No TOK_LN_TRANS_FUNCT because it's multiline data.
    
    HashSet<State> stateSet;
    HashSet<Character> inputAlphabet;
    HashSet<Symbol> stackAlphabet;
    State initialState;
    Symbol initialStackSymbol;
    HashSet<TransitionRule> transitionRules;
    HashSet<State> acceptStates;
    
    /**
     * Only method that implements/uses/knows 
     * the arbitrary automaton file structure.
     */
    AutomatonData(TokenizedLines tokLines){
        //stateSet.
    }
    
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
    public HashSet<TransitionRule> getTransitionRules() {
        return transitionRules;
    }
    public void setTransitionRules(HashSet<TransitionRule> transitionRules) {
        this.transitionRules = transitionRules;
    }
    public HashSet<State> getAcceptStates() {
        return acceptStates;
    }
    public void setAcceptStates(HashSet<State> acceptStates) {
        this.acceptStates = acceptStates;
    }
    
    
}
