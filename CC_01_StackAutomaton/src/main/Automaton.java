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
		
	    ArrayList<AutomatonStatus> possibleStatuses = new ArrayList<AutomatonStatus>();
	    ArrayList<AutomatonStatus> newStatuses = new ArrayList<AutomatonStatus>();
	    
		// Add the initial configuration into the statuses array.
		Stack<Symbol> initialStack = new Stack<Symbol>();
		initialStack.push(data.getInitialStackSymbol());
		possibleStatuses.add(new AutomatonStatus(data.getInitialState(), inputString, initialStack));
		
		// Iterate until all possibilities are exhausted.
		while (!possibleStatuses.isEmpty()){
    		// For each possible automata status...
		    for (AutomatonStatus as : possibleStatuses){
    		    // Find all applicable transitions to this status...
    			ArrayList<TransitionRule> applicableTransitions = findApplicableTransitionRules(as);
    			// Apply each of them to create a new status, that will be evaluated next round.
    			for (TransitionRule tr : applicableTransitions){
    				AutomatonStatus newStatus = applyTransition(as, tr);
    				System.out.println("heh....");
    				traceHist.add(AutomataReader.getStatusAsTokenizedLine(newStatus));
    				// If this new status has an empty stack AND input string... accept.
    				if (newStatus.getCurrentStack().isEmpty() &&
    						newStatus.getRemainingInputString().isEmpty())
    					return true;
    				// TODO not limit ourselves to yes or no; do the trace.
    				newStatuses.add(newStatus);
    			}
    		}
    		possibleStatuses.clear();
    		possibleStatuses.addAll(newStatuses);
    		newStatuses.clear();
		}
		// If all possibilities have been exhausted and the string
		// hasn't been accepted, then the string must be rejected. 
		return false;
	}
	
	/**
	 * @param as A frozen state of the automaton variables.
	 * @return	All applicable transition rules for the given automaton status.
	 */
	public ArrayList<TransitionRule> findApplicableTransitionRules(AutomatonStatus as){
		ArrayList<TransitionRule> applicableTransitions = new ArrayList<TransitionRule>();
 		for (TransitionRule tr : data.getTransitionRules()){
 		    if (tr.getPrevState().getName().equals(as.getCurrentState().getName()))
 		        System.out.println("prevState names are same!");
 		    System.out.println(tr.getPrevState().getName());
 		    System.out.println(as.getCurrentState().getName());
 		    if (tr.getRequiredInputCharacter() == as.getRemainingInputString().charAt(0))
 		       System.out.println("inputCharacter are same!");
 		    if (tr.getRequiredStackSymbol().getName() == as.getCurrentStack().peek().getName())
 		        System.out.println("requiredStack are same!");
 			if (tr.getPrevState().getName().equals(as.getCurrentState().getName()) &&
 				tr.getRequiredInputCharacter() == as.getRemainingInputString().charAt(0) &&
 				tr.getRequiredStackSymbol().getName().equals(as.getCurrentStack().peek().getName())){
 				applicableTransitions.add(tr);
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
		State newState = tr.getNextState();
		String oldString = as.getRemainingInputString();
		String newString = oldString.substring(1, oldString.length());
		Stack<Symbol> newStack = as.getCurrentStack();
		newStack.pop();
		
		return new AutomatonStatus(newState, newString, newStack);
	}

	/*
	 * Getters and setters.
	 */
	
    public TokenizedLines getTraceHist() {
        return traceHist;
    }
}