package common.main;

import java.util.HashSet;

import util.TokenizedLines;

/**
 * @author jose
 *
 *  Semantically represents the data 
 *  of an automaton definition.
 *  
 *  Extracted and given semantic meaning 
 *  by the MachineIO class.
 */
@SuppressWarnings("serial")
public abstract class MachineData extends TokenizedLines {
    HashSet<MachineTransition> transitionRules;
    
    /*
     * Getters and setters.
     */

    public HashSet<MachineTransition> getTransitionRules() {
        return transitionRules;
    }
}
