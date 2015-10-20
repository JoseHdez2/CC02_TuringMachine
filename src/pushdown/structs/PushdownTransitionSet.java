package pushdown.structs;

import automaton.structs.AutomatonTransitionSet;

/**
 * @author jose
 * 
 *	Workaround.
 *  Inheritance problems: Java doesn't seem to find the relation between
 *  returning a HashSet<AutomatonTransition> and a HashSet<PushdownTransition>.
 *  
 *  So, we do it more explicitly here.
 */
public class PushdownTransitionSet extends AutomatonTransitionSet {
}
