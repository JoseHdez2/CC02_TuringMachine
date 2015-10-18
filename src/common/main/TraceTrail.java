package common.main;

import java.util.ArrayList;

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
public class TraceTrail extends ArrayList<MachineStatus>{
    public MachineStatus getLast(){
        return this.get(this.size()-1);
    }
}
