package automaton.structs;

import java.util.HashSet;

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
}
