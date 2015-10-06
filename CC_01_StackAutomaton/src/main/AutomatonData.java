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
    final int TOK_LINE_STATE_SET = 0;
    final int TOK_LINE_INPUT_ALPH = 1;
    final int TOK_LINE_STACK_ALPH = 2;
    final int TOK_LINE_INIT_STATE = 3;
    final int TOK_LINE_INIT_STACK = 4;
    final int TOK_LINE_ACCEPT_STATES = 5;
    // No TOK_LINE_TRANS_FUNCT because it's multiline data.
    
    // Token positions in lines for transition functions.
    final int POS_PREV_STATE = 0;
    final int POS_NEXT_STATE = 3;
    final int POS_REQ_INP_CHAR = 1;
    final int POS_REQ_STACK_SYM = 2;
    final int POS_STACK_SYM_TO_PUSH = 4;
    // TODO: should we allow to push many symbols in same transition?
    
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
        for (String token : tokLines.get(TOK_LINE_STATE_SET)) {
            stateSet.add(new State(token));
        }

        HashSet<Character> inputAlphabet = new HashSet<Character>();
        for (String token : tokLines.get(TOK_LINE_INPUT_ALPH)) {
            inputAlphabet.add(token.charAt(0));
        }

        HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
        for (String token : tokLines.get(TOK_LINE_STACK_ALPH)) {
            stackAlphabet.add(new Symbol(token));
        }

        State initialState = new State(tokLines.get(TOK_LINE_INIT_STATE).get(0));

        Symbol initialStackSymbol = new Symbol(tokLines.get(TOK_LINE_INIT_STACK).get(0));

        HashSet<State> acceptStates = new HashSet<State>();
        for (String str : tokLines.get(TOK_LINE_ACCEPT_STATES)) {
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
