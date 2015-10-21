package automaton.structs;

import java.util.HashSet;

import util.TokenizedLines;

/**
 * @author jose
 * 
 *	Workaround.
 *  Inheritance problems: Java doesn't seem to find the relation between
 *  returning a HashSet<AutomatonTransition> and a HashSet<PushdownTransition>.
 *  
 *  So, we do it more explicitly here.
 */
@SuppressWarnings("serial")
public abstract class AutomatonTransitionSet extends HashSet<AutomatonTransition> {
    
    /**
     * Produce the representation of all of the transitions in 
     * the automata definition, according to the internal IO convention.
     * @return Array of string arrays representing all the transition rules.
     */
    public TokenizedLines asStringMatrix(){
        
        TokenizedLines tl = new TokenizedLines();
        
        for (AutomatonTransition tr : this){            
            tl.add(tr.asStringArray());
        }
        
        return tl;
    }
}
