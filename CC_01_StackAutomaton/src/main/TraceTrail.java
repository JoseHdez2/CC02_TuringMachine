package main;

import java.util.ArrayList;

import structs.AutomatonStatus;

/**
 * @author jose
 *  Only way I could think of to successfully draw the 
 *  winning trace of non-deterministic automata.
 *  Keep the whole history before reaching the currentStatus. 
 */
public class TraceTrail extends ArrayList<AutomatonStatus>{
    public AutomatonStatus getLast(){
        return this.get(this.size()-1);
    }
}
