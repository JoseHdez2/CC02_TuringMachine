package common.main;

/**
 * @author jose
 *  Temporal data structure used during a trace.
 *  
 *	Represents the status of a given machine
 *  as a frozen frame during the evaluation of a string.
 */
public abstract class MachineStatus {
    
	/*
	 * Equals and hashCode.
	 */
	
	public abstract boolean equals(Object ob);
	
	public abstract int hashCode();
    
}
