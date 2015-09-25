package main;

import java.util.Stack;

/**
 * @author jose
 *	A tuple that represents the status of a given automaton.
 *	It holds the current state, the remaining string to be evaluated
 *	and the status of the stack. 
 */
public class AutomatonStatus {
	State currentState;
	String remainingInputString;
	Stack<Symbol> currentStack;
}
