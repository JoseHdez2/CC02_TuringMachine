package main;

import java.util.ArrayList;
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

        HashSet<State> stateSet = new HashSet<State>();
        for (String token : tokLines.get(0)) {
            stateSet.add(new State(token));
        }

        HashSet<Character> inputAlphabet = new HashSet<Character>();
        for (String token : tokLines.get(1)) {
            inputAlphabet.add(token.charAt(0));
        }

        HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
        for (String token : tokLines.get(2)) {
            stackAlphabet.add(new Symbol(token));
        }

        State initialState = new State(tokLines.get(3).get(0));

        Symbol initialStackSymbol = new Symbol(tokLines.get(4).get(0));

        HashSet<State> acceptStates = new HashSet<State>();
        for (String str : tokLines.get(4)) {
            stateSet.add(new State(str));
        }
        
        HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
        for (ArrayList<String> tl : tokLines.subList(5, tokLines.size())){
            State prevState = new State(tl.get(0));
            Character requiredInputCharacter = tl.get(1).charAt(0);
            Symbol requiredStackSymbol = new Symbol(tl.get(2));
            State nextState = new State(tl.get(3));
            ArrayList<Character> stackSymbolsToPush = new ArrayList<Character>(tl.get(4).charAt(0));
            transitionRules.add(new TransitionRule(prevState, nextState, 
                    requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
        }
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
