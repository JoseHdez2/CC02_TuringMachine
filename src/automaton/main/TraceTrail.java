package automaton.main;

import java.util.ArrayList;

import automaton.structs.AutomatonStatus;

/**
 * @author jose
 * 
 *  Temporal data structure used during a trace.
 *  
 *  Keeps the history of a given "trace trail" 
 *  (a possible path in a non-deterministic trace).
 *  
 *  Used for presenting the trace of a successful possibility.
 */
@SuppressWarnings("serial")
public class TraceTrail extends ArrayList<AutomatonStatus>{
    public AutomatonStatus getLast(){
        return this.get(this.size()-1);
    }
}
