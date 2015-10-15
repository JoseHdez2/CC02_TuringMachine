package turing.structs;

import java.util.ArrayList;

/**
 * @author jose
 * 
 *  Temporal data structure used during a trace.
 *  
 *  Keeps the history of a given "trace trail" 
 *  (a possible path in a non-deterministic trace).
 *  
 *  Used for presenting the trace of a winning possibility.
 */
public class TraceTrail extends ArrayList<TuringStatus>{
    public TuringStatus getLast(){
        return this.get(this.size()-1);
    }
}
