package main;

import java.util.ArrayList;
import java.util.Stack;

import structs.AutomatonData;
import structs.AutomatonStatus;
import structs.State;
import structs.Symbol;
import structs.TransitionRule;
import util.Sys;
import util.TokenizedLines;

/**
 * @author jose
 *
 *  The automaton itself. The only class that knows how to work with
 *  the AutomatonData it contains (e.g. apply transitions).
 */
public class Automaton {
	AutomatonData data;
	TokenizedLines traceHist = new TokenizedLines();
	
	public Automaton(AutomatonData data){
		this.data = data;
		
		// TODO make sure all arguments are correct.
		
		// Make sure all acceptStates are in stateSet.
		for (State s : data.getAcceptStates()){
			if(!data.getStateSet().contains(s))
				Sys.abort("Algun estado de aceptacion no se encuentra entre los estados definidos.");
		}
	}
	
	/**
	 * Evaluates an input string. The main purpose of a logical automaton.
	 * @return	Boolean indicating acceptance of input string.
	 */
	public boolean evaluateString(String inputString){
		
	    ArrayList<TraceTrail> possibleTrails = new ArrayList<TraceTrail>();
	    ArrayList<TraceTrail> newTrails = new ArrayList<TraceTrail>();
	    
//	    ArrayList<AutomatonStatus> possibleStatuses = new ArrayList<AutomatonStatus>();
//	    ArrayList<AutomatonStatus> newStatuses = new ArrayList<AutomatonStatus>();
	    
		// Add the initial configuration into the statuses array.
		Stack<Symbol> initialStack = new Stack<Symbol>();
		initialStack.push(data.getInitialStackSymbol());
		AutomatonStatus initialStatus = new AutomatonStatus(data.getInitialState(), inputString, initialStack);
		TraceTrail initialTrail = new TraceTrail();
		initialTrail.add(initialStatus);
		possibleTrails.add(initialTrail);
//		possibleStatuses.add(initialStatus);
		
		// Iterate until all possibilities are exhausted.
		while (!possibleTrails.isEmpty()){
//		while (!possibleStatuses.isEmpty()){
    		// For each possible automata status...
		    for (TraceTrail tt : possibleTrails){
//		    for (AutomatonStatus as : possibleStatuses){
    		    // Find all applicable transitions to this status...
    			ArrayList<TransitionRule> applicableTransitions = findApplicableTransitionRules(tt.getLast());
    			// Apply each of them to create a new status, that will be evaluated next round.
    			for (TransitionRule tr : applicableTransitions){
                    TraceTrail newTrail = new TraceTrail();
                    newTrail.addAll(tt);
    				AutomatonStatus newStatus = applyTransition(tt.getLast(), tr);
    				newTrail.add(newStatus);
    				// If this new status has an empty stack AND input string... accept.
    				if (newStatus.getCurrentStack().isEmpty() &&
    						newStatus.getRemainingInputString().isEmpty()){
    				    // TODO record winning trace into history.
    				    traceHist = AutomataReader.traceTrailAsTokenizedLines(newTrail);
    					return true;
    				}
    				// TODO not limit ourselves to yes or no; do the trace.
    				newTrails.add(newTrail);
    			}
    		}
    		possibleTrails.clear();
    		possibleTrails.addAll(newTrails);
    		newTrails.clear();
		}
		// If all possibilities have been exhausted and the string
		// hasn't been accepted, then the string must be rejected. 
		return false;
	}
	
	// Special input string character and stack symbol.
	private final Character EPSILON_INPUT = '-';
	private final Symbol EPSILON_SYMBOL = new Symbol("-");
	
	/**
	 * @param as A frozen state of the automaton variables.
	 * @return	All applicable transition rules for the given automaton status.
	 */
	public ArrayList<TransitionRule> findApplicableTransitionRules(AutomatonStatus as){
	    
		ArrayList<TransitionRule> applicableTransitions = new ArrayList<TransitionRule>();
		
		// Special case
		if (as.getCurrentStack().isEmpty()) return applicableTransitions;
		
		// Normal
 		for (TransitionRule tr : data.getTransitionRules()){

 			if (tr.getPrevState().getName().equals(as.getCurrentState().getName()) &&
 				tr.getRequiredStackSymbol().getName().equals(as.getCurrentStack().peek().getName())){
 				System.out.println("app");
 			    if (tr.getRequiredInputCharacter() == EPSILON_INPUT ||
 			            tr.getRequiredInputCharacter() == as.getRemainingInputString().charAt(0)){
 			       System.out.println("supa app");
 			        applicableTransitions.add(tr);
 			    }
 			}
 		}
 		return applicableTransitions;
	}
	
	/**
	 * Given an automaton status and applicable transition rule,
	 * compute and return the resulting automaton status.
	 * 
	 * Note: The transition rule should have previously been deemed applicable
	 * for the given automaton status (no checks are made).
	 * @param as	Automaton status.
	 * @param tr	Transition rule to be applied to the status.
	 * @return	New automaton status.
	 */
	public AutomatonStatus applyTransition(AutomatonStatus as, TransitionRule tr){
		
	 // Special case
        if (as.getCurrentStack().isEmpty()){
            System.err.println("Somehow, unapplicable transition was considered applicable.");
            System.exit(1);
        }
	    
        String oldString = as.getRemainingInputString();
	    
	    State newState = tr.getNextState();
		String newString = oldString.substring(1, oldString.length());
		Stack<Symbol> newStack = as.getCurrentStack();
		newStack.pop();   //Always pop the top.
		
//		newStack.push(tr.getStackSymbolsToPush().get(0));
		/*
		if (!tr.getStackSymbolsToPush().get(0).getName().equals(EPSILON_SYMBOL.getName())){
		    for (Symbol s : tr.getStackSymbolsToPush()){
		        newStack.push(s);
		    }
		}*/
		
		return new AutomatonStatus(newState, newString, newStack);
	}

	/*
	 * Getters and setters.
	 */
	
    public TokenizedLines getTraceHist() {
        return traceHist;
    }
}