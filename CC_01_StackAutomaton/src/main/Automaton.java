package main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import util.Sys;

public class Automaton {
	HashSet<State> stateSet;
	HashSet<Character> inputAlphabet;
	HashSet<Symbol> stackAlphabet;
	State initialState;
	Symbol initialStackSymbol;
	HashSet<TransitionRule> transitionRules;
	HashSet<State> acceptStates;
	
	Automaton(	HashSet<State> stateSet, 
				HashSet<Character> inputAlphabet,
				HashSet<Symbol> stackAlphabet,
				State initialState,
				Symbol initialStackSymbol,
				HashSet<TransitionRule> transitionRules,
				HashSet<State> acceptStates){
		this.stateSet = stateSet;
		this.inputAlphabet = inputAlphabet;
		this.stackAlphabet = stackAlphabet;
		this.initialState = initialState;
		this.initialStackSymbol = initialStackSymbol;
		this.transitionRules = transitionRules;
		this.acceptStates = acceptStates;
		
		// TODO make sure all arguments are correct.
		
		// Make sure all acceptStates are in stateSet.
		for (State s : acceptStates){
			if(!stateSet.contains(s))
				Sys.abort("Algun estado de aceptacion no se encuentra entre los estados definidos.");
		}
	}
	
	/**
	 * Evaluates an input string. The main purpose of a logical automata.
	 * @return	Boolean indicating acceptance of input string.
	 */
	public boolean evaluateString(String inputString){
		ArrayList<AutomatonStatus> possibleStatuses = new ArrayList<AutomatonStatus>();
		// Add the initial configuration into the statuses array.
		Stack<Symbol> initialStack = new Stack<Symbol>();
		initialStack.push(initialStackSymbol);
		possibleStatuses.add(new AutomatonStatus(initialState, inputString, initialStack));
		// Iterate until all possibilities are exhausted.
		// TODO not add and remove from a list you are iterating.
		for (AutomatonStatus as : possibleStatuses){
			ArrayList<TransitionRule> applicableTransitions = findApplicableTransitionRules(as);
			if (applicableTransitions.isEmpty()){
				possibleStatuses.remove(as);
				break;
			}
			for (TransitionRule tr : applicableTransitions){
				AutomatonStatus newStatus = applyTransition(as, tr);
				if (newStatus.getCurrentStack().isEmpty() &&
						newStatus.getRemainingInputString().isEmpty())
					return true;
				// TODO not limit ourselves to yes or no; do the trace.
				possibleStatuses.add(newStatus);
			}
			possibleStatuses.remove(as);
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
 		for (TransitionRule tr : transitionRules){
 			if (tr.getPrevState() == as.getCurrentState() &&
 				tr.requiredInputCharacter == as.getRemainingInputString().charAt(0) &&
 				tr.requiredStackSymbol == as.getCurrentStack().peek()){
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
		String oldString = as.remainingInputString;
		String newString = oldString.substring(0, oldString.length()-2);
		Stack<Symbol> newStack = as.currentStack;
		newStack.pop();
		
		return new AutomatonStatus(newState, newString, newStack);
	}
}