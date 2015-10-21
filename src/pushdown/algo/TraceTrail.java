package pushdown.algo;

import java.util.ArrayList;

import pushdown.structs.PushdownStatus;

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
@SuppressWarnings("serial")
public class TraceTrail extends ArrayList<PushdownStatus>{
    public PushdownStatus getLast(){
        return this.get(this.size()-1);
    }
}
