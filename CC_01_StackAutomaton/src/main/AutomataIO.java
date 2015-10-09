package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import structs.AutomatonData;
import structs.AutomatonStatus;
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
public abstract class AutomataIO extends IOConst{
	
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
    
	/**
	 * Reads automaton data from a given file.
	 * @param fileName Path to automaton file.
	 * @return AutomatonData representing the automaton.
	 * @throws IOException
	 */
	public static AutomatonData readAutomatonData(String fileName) throws IOException {
	    TokenizedLines tokLines = prepareAutomatonData(fileName);

        HashSet<State> stateSet = new HashSet<State>();
        for (String token : tokLines.get(IN_STATE_SET)) {
            stateSet.add(new State(token));
        }

        HashSet<Character> inputAlphabet = new HashSet<Character>();
        for (String token : tokLines.get(IN_INPUT_ALPH)) {
            inputAlphabet.add(token.charAt(0));
        }

        HashSet<Symbol> stackAlphabet = new HashSet<Symbol>();
        for (String token : tokLines.get(IN_STACK_ALPH)) {
            stackAlphabet.add(new Symbol(token));
        }

        State initialState = new State(tokLines.get(IN_INIT_STATE).get(0));

        Symbol initialStackSymbol = new Symbol(tokLines.get(IN_INIT_STACK).get(0));

        HashSet<State> acceptStates = new HashSet<State>();
        for (String str : tokLines.get(IN_ACCEPT_STATES)) {
            stateSet.add(new State(str));
        }
        
        HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
        
        for (ArrayList<String> tl : tokLines.subList(IN_TRANS_FUNCT, tokLines.size())){
            
            State prevState = new State(tl.get(IN_PREV_STATE));
            
            State nextState = new State(tl.get(IN_NEXT_STATE));
            
            Character requiredInputCharacter = tl.get(IN_REQ_INP_CHAR).charAt(0);
            
            Symbol requiredStackSymbol = new Symbol(tl.get(IN_REQ_STACK_SYM));
            
            ArrayList<Symbol> stackSymbolsToPush = new ArrayList<Symbol>();
            
            String[] stackCharsToPush = tl.get(IN_STACK_SYM_TO_PUSH).split(",");
            for (String str : stackCharsToPush){
                stackSymbolsToPush.add(new Symbol(str));
            }
            
            // TODO: allow for more than one stack symbol. currently a fix to let one.
            transitionRules.add(new TransitionRule(prevState, nextState, 
                    requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
        }
        
        return new AutomatonData(stateSet, inputAlphabet, stackAlphabet, initialState,
                initialStackSymbol, transitionRules, acceptStates);
	}
    
    /**
     * @param tr
     * @return
     */
    public static ArrayList<String> getTransitionAsTokenizedLine(TransitionRule tr){

//      ArrayList<String> transitionRuleLine = new ArrayList<String>(5);

//      TODO: Try to one-line this?
      ArrayList<String> transitionRuleLine = new ArrayList<String>();
      for (int i = 0; i < 5; i++){
          transitionRuleLine.add("dummy");
      }
      
      transitionRuleLine.set(OUT_LN_PREV_STATE, tr.getPrevState().toString());
      transitionRuleLine.set(OUT_LN_NEXT_STATE, tr.getNextState().toString());
      transitionRuleLine.set(OUT_LN_REQ_INP_CHAR, tr.getRequiredInputCharacter().toString());
      transitionRuleLine.set(OUT_LN_REQ_STACK_SYM, tr.getRequiredStackSymbol().toString());
      transitionRuleLine.set(OUT_LN_STACK_SYM_TO_PUSH, tr.getStackSymbolsToPush().toString());
      
      return transitionRuleLine;
    }
    
    public static TokenizedLines getTransitionsAsTokenizedLines(AutomatonData ad){
        
        TokenizedLines tl = new TokenizedLines();
        
        for (TransitionRule tr : ad.getTransitionRules()){            
            tl.add(getTransitionAsTokenizedLine(tr));
        }
        
        return tl;
    }
    
    public static ArrayList<String> getStatusAsTokenizedLine(AutomatonStatus as){
        
        ArrayList<String> tokenizedLine = new ArrayList<String>();
        
        for (int i = 0; i < 3; i++){
            tokenizedLine.add("dummy");
        }
        
        tokenizedLine.set(OUT_POS_CUR_STATE, as.getCurrentState().toString());
        tokenizedLine.set(OUT_POS_REMAIN_STR, as.getRemainingInputString().toString());
        tokenizedLine.set(OUT_POS_STACK_STATUS, as.getCurrentStack().toString());
        
        return tokenizedLine;
    }
    
    public static TokenizedLines traceTrailAsTokenizedLines(TraceTrail tt){
        
        TokenizedLines tokenizedLines = new TokenizedLines();
        
        for (AutomatonStatus as : tt){
            tokenizedLines.add(getStatusAsTokenizedLine(as));
        }
        
        return tokenizedLines;
    }
   
}
