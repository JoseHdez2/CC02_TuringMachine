package common.main;

/**
 * @author jose
 * 
 *	Represents a transition rule, 
 *	which the automata use to move between states.
 */
public abstract class AutomatonTransition {
	
	/*
	 * Equals and hashCode.
	 */
	
	public abstract boolean equals(Object ob);
	
	public abstract int hashCode();
}
