package main;

public class State {
	String name;

	State(String name){
		this.name = name;
	}
	
	/*
	 * Equals and hashCode.
	 */
	
	public boolean equals(Object ob){
		if (ob == null) return false;
		if (ob.getClass() != getClass()) return false;
		State other = (State)ob;
		if (!name.equals(other.name)) return false;
		return true;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	/*
	 * Getters and setters.
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}