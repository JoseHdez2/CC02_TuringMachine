package common.main;

import java.util.ArrayList;
import java.util.Stack;

import common.structs.State;
import common.structs.Symbol;
import pushdown.structs.PushdownData;
import pushdown.structs.AutomatonStatus;
import pushdown.structs.TransitionRule;
import util.TokenizedLines;

/**
 * @author jose
 *
 *  The automaton itself. The only class that knows how to work with
 *  the MachineData it contains (e.g. apply transitions).
 */
public abstract class Machine {
    String debugStr = "";
    
	PushdownData data;
	TokenizedLines traceHist = new TokenizedLines();
	
	public Machine(PushdownData data){
	    if (!correctMachineDefinition(data))
	        
		this.data = data;
	}
	
	/**
	 * Check the provided machine definition data is correct (self-coherent).
	 * @param data Machine definition data.
	 * @return Whether the provided machine data is correct.
	 */
	protected boolean correctMachineDefinition(PushdownData data){
	    //TODO: Implement method.
	    return true;
	}
	
	protected abstract MachineStatus createInitialStatus();
	
	protected abstract ArrayList<TransitionRule> findApplicableTransitionRules(MachineStatus ms);
	
	protected abstract MachineStatus applyTransition(MachineStatus ms, TransitionRule tr);
	
	protected abstract boolean acceptanceStatus(MachineStatus ms);
	
	/**
	 * Evaluates an input string, non-deterministically. The main purpose of a logical automaton.
	 * In case of success, the first successful trace is recorded into traceHist, for later retrieval.
	 * @return	Boolean indicating acceptance of input string.
	 */
	public boolean evaluateString(String inputString){
		
	    ArrayList<TraceTrail> possibleTrails = new ArrayList<TraceTrail>();
	    ArrayList<TraceTrail> newTrails = new ArrayList<TraceTrail>();
	    
		// Add the initial configuration into the statuses array.
		TraceTrail initialTrail = new TraceTrail();
		initialTrail.add(createInitialStatus());
		possibleTrails.add(initialTrail);
		
		// Iterate until all possibilities are exhausted.
		while (!possibleTrails.isEmpty()){

		    for (TraceTrail tt : possibleTrails){

    			ArrayList<TransitionRule> applicableTransitions = findApplicableTransitionRules(tt.getLast());
    			// Apply each of them to create a new status, that will be evaluated next round.
    			for (TransitionRule tr : applicableTransitions){
                    TraceTrail newTrail = new TraceTrail();
                    newTrail.addAll(tt);
    				MachineStatus newStatus = applyTransition(tt.getLast(), tr);
    				newTrail.add(newStatus);

    				if (acceptanceStatus(newStatus)){
    				    // Record winning trace into history.
                        traceHist = MachineIO.traceTrailAsTokenizedLines(newTrail);
    				    return true;
    				}

    				newTrails.add(newTrail);
    			}
    		}
    		possibleTrails.clear();
    		possibleTrails.addAll(newTrails);
    		newTrails.clear();
		}
		// If all possibilities have been exhausted and the string
		// hasn't been accepted, then the string is rejected. 
		return false;
	}
	
	// Special input string character and stack symbol.
	private final Character EPSILON_INPUT = '-';
	private final Symbol EPSILON_SYMBOL = new Symbol("-");
	
	/**
	 * Given an automaton status and applicable transition rule,
	 * compute and return the resulting automaton status.
	 * 
	 * Note: The transition rule should have previously been deemed applicable
	 * for the given automaton status (no checks are made).
	 * @param as	Machine status.
	 * @param tr	Transition rule to be applied to the status.
	 * @return	New automaton status.
	 */
	public AutomatonStatus applyTransition(AutomatonStatus as, TransitionRule tr){
		
	 // Special case
	    // TODO: Remove this because it should never happen?
        if (as.getCurrentStack().isEmpty()){
            System.err.println("Somehow, unapplicable transition was considered applicable.");
            System.exit(1);
        }
	    
        String oldString = as.getRemainingInputString();
	    
	    State newState = tr.getNextState();
		String newString = oldString.substring(1, oldString.length());
		
		Stack<Symbol> newStack = new Stack<Symbol>();
		
        for (Symbol s : as.getCurrentStack()){
		    newStack.push(s);
		}
        
        newStack.pop();

        
		if (!tr.getStackSymbolsToPush().get(0).getName().equals(EPSILON_SYMBOL.getName())){
		    for (Symbol s : tr.getStackSymbolsToPush()){
		        newStack.push(s);
		    }
		}
		
		AutomatonStatus newStatus = new AutomatonStatus(newState, newString, newStack);
		
		System.out.print(MachineIO.getStatusAsTokenizedLine(as) + "->");
		System.out.println(MachineIO.getStatusAsTokenizedLine(newStatus));
		return newStatus;
	}

	/*
	 * Getters and setters.
	 */
	
    public TokenizedLines getTraceHist() {
        return traceHist;
    }

    public String getDebugStr() {
        return debugStr;
    }
}