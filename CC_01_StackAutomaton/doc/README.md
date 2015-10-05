# Pushdown Automata Simulator

Pushdown Automata Simulator coded in Java by Jose Hernandez.

## Packages overview

* __gui__ contains the class(es) related to the Graphical User Interface.
* __main__ contains the main program code; classes that implement the algorithms.
* __structs__ contains the problem-specific data structures.
* __util__ contain the non-problem-specific algorithms and data structures.

## Source explanation

* _MainWindow_ fires up the GUI.
* When 'Load' is chosen, _AutomataCreator_ converts a file into an _AutomatonData_.
	* Which itself is used to create an _Automaton_.
* When 'Run' is chosen, the already created _Automaton_ executes a trace of the given string.