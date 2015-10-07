package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import structs.AutomatonData;
import structs.State;
import structs.Symbol;
import structs.TransitionRule;
import util.StringProcessing;
import util.TokenizedLines;

/**
 * @author jose
 * 
 *         Collection of static functions to read automata data from a file.
 *         
 *         It contains the only functions that work with the low-level structure
 *         of the automata files.
 */
public abstract class AutomataReader {
	
	/**
	 * @param fileName Path to an automaton file (arbitrary file structure)
	 * @return
	 * @throws IOException
	 */
	public static TokenizedLines prepareAutomatonData(String fileName) throws IOException {
		
		// Read the file lines.
		ArrayList<String> lines = StringProcessing.readFileLines(fileName);
		
		// Remove comments.
		lines = StringProcessing.stripComments(lines);
		
		// Remove all empty lines.
		lines = StringProcessing.removeAllEmptyLines(lines);

		// Tokenize the remaining, valid lines.
		TokenizedLines tokenizedLines = StringProcessing.tokenizeLines(lines);
		
		return tokenizedLines;
	}
	
    // Arbitrary automaton file structure constants.
    // Tokenized lines where each of the datums are located.
    final static int TOK_LINE_STATE_SET = 0;
    final static int TOK_LINE_INPUT_ALPH = 1;
    final static int TOK_LINE_STACK_ALPH = 2;
    final static int TOK_LINE_INIT_STATE = 3;
    final static int TOK_LINE_INIT_STACK = 4;
    final static int TOK_LINE_ACCEPT_STATES = 5;
    final static int TOK_LINE_TRANS_FUNCT = 6; // Where the transition lines BEGIN.
    
    // Tokens positions in each transition rule line, in the automaton file.
    final static int POS_PREV_STATE = 0;
    final static int POS_NEXT_STATE = 3;
    final static int POS_REQ_INP_CHAR = 1;
    final static int POS_REQ_STACK_SYM = 2;
    final static int POS_STACK_SYM_TO_PUSH = 4;
    // TODO: should we allow to push many symbols in same transition?
    
	public static AutomatonData readAutomatonData(String fileName) throws IOException {
	    TokenizedLines tokLines = prepareAutomatonData(fileName);

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
        
        for (ArrayList<String> tl : tokLines.subList(TOK_LINE_TRANS_FUNCT, tokLines.size())){
            State prevState = new State(tl.get(POS_PREV_STATE));
            State nextState = new State(tl.get(POS_NEXT_STATE));
            Character requiredInputCharacter = tl.get(POS_REQ_INP_CHAR).charAt(0);
            Symbol requiredStackSymbol = new Symbol(tl.get(POS_REQ_STACK_SYM));
            ArrayList<Symbol> stackSymbolsToPush = new ArrayList<Symbol>();
            stackSymbolsToPush.add(new Symbol(String.valueOf(tl.get(POS_STACK_SYM_TO_PUSH).charAt(0))));
            // TODO: allow for more than one stack symbol. currently a fix to let one.
            transitionRules.add(new TransitionRule(prevState, nextState, 
                    requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
        }
        
        return new AutomatonData(stateSet, inputAlphabet, stackAlphabet, initialState,
                initialStackSymbol, transitionRules, acceptStates);
	}
	
	// Tokens positions in each transition rule line, in the internal program representation.
    final static int REPOS_PREV_STATE = 0;
    final static int REPOS_NEXT_STATE = 3;
    final static int REPOS_REQ_INP_CHAR = 1;
    final static int REPOS_REQ_STACK_SYM = 2;
    final static int REPOS_STACK_SYM_TO_PUSH = 4;
    
    public static TokenizedLines getTransitionsAsTokenizedLines(AutomatonData ad){
        
        TokenizedLines tl = new TokenizedLines();
        
        for (TransitionRule tr : ad.getTransitionRules()){
            
            ArrayList<String> transitionRuleLine = new ArrayList<String>(5);
            
            transitionRuleLine.set(REPOS_PREV_STATE, tr.getPrevState().toString());
            transitionRuleLine.set(REPOS_NEXT_STATE, tr.getNextState().toString());
            transitionRuleLine.set(REPOS_REQ_INP_CHAR, tr.getRequiredInputCharacter().toString());
            transitionRuleLine.set(REPOS_REQ_STACK_SYM, tr.getRequiredStackSymbol().toString());
            transitionRuleLine.set(REPOS_STACK_SYM_TO_PUSH, tr.getStackSymbolsToPush().toString());
            
            tl.add(transitionRuleLine);
        }
        
        return tl;
    }
}
