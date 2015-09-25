package main;

public class State {
	String name;

	State(String name){
		setName(name);
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
