package turing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import pushdown.structs.AutomatonData;
import pushdown.structs.AutomatonStatus;
import turing.structs.Movement;
import turing.structs.TraceTrail;
import turing.structs.TransitionRule;
import turing.structs.TuringData;
import util.StringProcessing;
import util.TokenizedLines;

import common.structs.State;
import common.structs.Symbol;

/**
 * @author jose
 * 
 * Extracts and gives semantic meaning
 * to Turing Machine data stored as raw text.
 * 
 * Also used to represent data in raw strings.
 * 
 * Only class that knows the input/output
 * convention (data format) used.
 */
public abstract class TuringIO extends IOConst{
    
    /*
     * Input functions.
     */
    
	/**
	 * Reads Turing machine data from a given file.
	 * @param fileName Path to Turing machine file.
	 * @return Data structure that semantically represents the automaton definition.
	 * @throws IOException
	 */
	public static TuringData readAutomatonData(String fileName) throws IOException {
	    
	    TokenizedLines tokLines = prepareTuringData(fileName);

        HashSet<State> stateSet = new HashSet<State>();
        for (String token : tokLines.get(IN_FILE_STATE_SET)) {
            stateSet.add(new State(token));
        }

        HashSet<Character> inputAlphabet = new HashSet<Character>();
        for (String token : tokLines.get(IN_FILE_INPUT_ALPH)) {
            inputAlphabet.add(token.charAt(0));
        }

        HashSet<Character> outputAlphabet = new HashSet<Character>();
        for (String token : tokLines.get(IN_FILE_OUTPUT_ALPH)) {
            outputAlphabet.add(token.charAt(0));
        }

        State initialState = new State(tokLines.get(IN_FILE_INIT_STATE).get(0));

        Character blankCharacter = tokLines.get(IN_FILE_BLANK_CHAR).get(0).charAt(0);

        HashSet<State> acceptStates = new HashSet<State>();
        for (String str : tokLines.get(IN_FILE_ACCEPT_STATES)) {
            stateSet.add(new State(str));
        }
        
        TokenizedLines transitionLines = 
                new TokenizedLines(tokLines.subList(IN_FILE_TRANS_FUNCT, tokLines.size()));
        
        HashSet<TransitionRule> transitionRules = readTransitionRules(transitionLines);
        
        return new TuringData(stateSet, inputAlphabet, outputAlphabet, initialState,
                blankCharacter, transitionRules, acceptStates);
	}
    
	/**
	 * Reads transition rules from an array of string arrays.
	 * @param transitionLines  Array of string arrays containing
	 * @return Data structure that semantically represents a set of transition rules.
	 */
	private static HashSet<TransitionRule> readTransitionRules(TokenizedLines transitionLines){
	    
	    HashSet<TransitionRule> transitionRules = new HashSet<TransitionRule>();
        
        for (ArrayList<String> tl : transitionLines){
            
            State prevState = new State(tl.get(IN_TRAN_PREV_STATE));
            
            State nextState = new State(tl.get(IN_TRAN_NEXT_STATE));
            
            Character inputCharacter = tl.get(IN_TRAN_INPUT_CHAR).charAt(0);
            
            Character outputCharacter = tl.get(IN_TRAN_OUTPUT_CHAR).charAt(0);
            
            Character movementChar = tl.get(IN_TRAN_MOVEMENT).charAt(0);
            
            Movement movement;
            // TODO how to transform char into uppercase.
            switch(movementChar){
            case 'L':
                movement = Movement.LEFT;
                break;
            case 'R':
                movement = Movement.RIGHT;
                break;
            case 'S':
                movement = Movement.STOP;
                break;
            default:
                // TODO which is the best exception type to throw? IOException? RuntimeException?
                throw new RuntimeException("Unrecognized movement character.");
            }
            
            transitionRules.add(new TransitionRule(prevState, nextState, 
                    inputCharacter, outputCharacter, movement));
        }
        
        return transitionRules;
	}
	
	/**
     * Standardizes input file, preparing it for Turing machine data extraction.
     * @param fileName Path to an automaton file (arbitrary file structure)
     * @return Standardized (expected) lines for reading automaton data.
     * @throws IOException
     */
    private static TokenizedLines prepareTuringData(String fileName) throws IOException {
        
        ArrayList<String> lines = StringProcessing.readFileLines(fileName);
        
        lines = StringProcessing.stripComments(lines, "#");
        
        lines = StringProcessing.removeAllEmptyLines(lines);

        TokenizedLines tokenizedLines = StringProcessing.tokenizeLines(lines, "\\s+");
        
        return tokenizedLines;
    }
	
    /*
     * Output functions.
     */
    
    private static final String DUMMY_STRING = "dummy";
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param tr Transition rule to be represented.
     * @return Array of strings representing the given transition rule.
     */
    public static ArrayList<String> getTransitionAsTokenizedLine(TransitionRule tr){

//      ArrayList<String> transitionRuleLine = new ArrayList<String>(5);

//      TODO: Try to one-line this?
      ArrayList<String> transitionRuleLine = new ArrayList<String>();
      for (int i = 0; i < 5; i++){
          transitionRuleLine.add(DUMMY_STRING);
      }
      
      transitionRuleLine.set(OUT_TRAN_PREV_STATE, tr.getPrevState().toString());
      transitionRuleLine.set(OUT_TRAN_NEXT_STATE, tr.getNextState().toString());
      transitionRuleLine.set(OUT_TRAN_REQ_INP_CHAR, tr.getRequiredInputCharacter().toString());
      transitionRuleLine.set(OUT_TRAN_REQ_STACK_SYM, tr.getRequiredStackSymbol().toString());
      transitionRuleLine.set(OUT_TRAN_STACK_SYM_TO_PUSH, tr.getStackSymbolsToPush().toString());
      
      return transitionRuleLine;
    }
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param ad AutomatonData to be represented.
     * @return Array of string arrays representing the given transition rule.
     */
    public static TokenizedLines getTransitionsAsTokenizedLines(AutomatonData ad){
        
        TokenizedLines tl = new TokenizedLines();
        
        for (TransitionRule tr : ad.getTransitionRules()){            
            tl.add(getTransitionAsTokenizedLine(tr));
        }
        
        return tl;
    }
    
    /**
     * Produce the representation of an automaton status, according to the internal IO convention.
     * @param as Automaton status to be represented.
     * @return Array of strings representing the given automaton status.
     */
    public static ArrayList<String> getStatusAsTokenizedLine(AutomatonStatus as){
        
        ArrayList<String> tokenizedLine = new ArrayList<String>();
        
//      TODO: Try to one-line this?
        for (int i = 0; i < 3; i++){
            tokenizedLine.add(DUMMY_STRING);
        }
        
        tokenizedLine.set(OUT_STAT_CUR_STATE, as.getCurrentState().toString());
        tokenizedLine.set(OUT_STAT_REMAIN_STR, as.getRemainingInputString().toString());
        tokenizedLine.set(OUT_STAT_STACK_STATUS, as.getCurrentStack().toString());
        
        return tokenizedLine;
    }
    
    /**
     * Produce the representation of a trace trail, according to the internal IO convention.
     * @param tt Trace trail to be represented.
     * @return  Array of string arrays representing the given trace trail.
     */
    public static TokenizedLines traceTrailAsTokenizedLines(TraceTrail tt){
        
        TokenizedLines tokenizedLines = new TokenizedLines();
        
        for (AutomatonStatus as : tt){
            tokenizedLines.add(getStatusAsTokenizedLine(as));
        }
        
        return tokenizedLines;
    }
   
}
