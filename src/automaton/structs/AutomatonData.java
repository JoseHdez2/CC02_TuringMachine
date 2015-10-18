package automaton.structs;

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
public abstract class AutomatonData extends TokenizedLines {
    HashSet<AutomatonTransition> transitionRules;
    
    /*
     * Getters and setters.
     */

    public HashSet<AutomatonTransition> getTransitionRules() {
        return transitionRules;
    }
}
