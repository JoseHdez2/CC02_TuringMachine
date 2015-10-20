package automaton.main;

import java.util.ArrayList;

import automaton.structs.AutomatonStatus;
import pushdown.structs.PushdownData;
import pushdown.structs.PushdownTransition;
import util.TokenizedLines;

/**
 * @author jose
 *
 *  The automaton itself. The only class that knows how to work with
 *  the MachineData it contains (e.g. apply transitions).
 */
public abstract class Automaton {
    String debugStr = "";
    
	PushdownData data;
	TokenizedLines traceHist = new TokenizedLines();
	
	public Automaton(PushdownData data){
	    if (!correctMachineDefinition(data)){
	        System.err.println("Incoherent automaton definition!");
	        // TODO: Figure out what to throw
	    }
		this.data = data;
	}
	
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

    			ArrayList<PushdownTransition> applicableTransitions = findApplicableTransitionRules(tt.getLast());
    			// Apply each of them to create a new status, that will be evaluated next round.
    			for (PushdownTransition tr : applicableTransitions){
                    TraceTrail newTrail = new TraceTrail();
                    newTrail.addAll(tt);
    				AutomatonStatus newStatus = applyTransition(tt.getLast(), tr);
    				newTrail.add(newStatus);

    				if (acceptanceStatus(newStatus)){
    				    // Record (first) successful trace into history.
                        traceHist = AutomatonIO.traceTrailAsTokenizedLines(newTrail);
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

    /*
     * ABSTRACT METHODS
     * 
     * To be implemented by inheriting classes.
     */
	   
    /**
     * Check the provided machine definition data is correct (self-coherent).
     * @param data Machine definition data.
     * @return Whether the provided machine data is correct.
     */
    protected abstract boolean correctMachineDefinition(PushdownData data);
	
    // TODO: Write descriptions for these.
    
    protected abstract AutomatonStatus createInitialStatus();
    
    protected abstract ArrayList<PushdownTransition> findApplicableTransitionRules(AutomatonStatus ms);
    
    protected abstract boolean acceptanceStatus(AutomatonStatus ms);
    
    /**
     * Given an automaton status and applicable transition rule,
     * compute and return the resulting automaton status.
     * 
     * Note: The transition rule should have previously been deemed applicable
     * for the given automaton status (no checks are made here).
     * @param as    Machine status.
     * @param tr    Transition rule to be applied to the status.
     * @return  New automaton status.
     */
    public abstract AutomatonStatus applyTransition(AutomatonStatus as, PushdownTransition tr);
	
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