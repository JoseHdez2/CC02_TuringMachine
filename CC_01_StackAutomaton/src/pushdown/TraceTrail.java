package pushdown;

import java.util.ArrayList;

import pushdown.structs.AutomatonStatus;

/**
 * @author jose
 *  Temporal data structure used during a trace.
 *  
 *  Keeps the history of a given "trace trail" 
 *  (a possible path in a non-deterministic trace).
 *  
 *  Used for presenting the trace of a winning possibility.
 */
public class TraceTrail extends ArrayList<AutomatonStatus>{
    public AutomatonStatus getLast(){
        return this.get(this.size()-1);
    }
}
