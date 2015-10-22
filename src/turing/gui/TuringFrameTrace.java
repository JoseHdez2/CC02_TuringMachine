package turing.gui;

import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import turing.algo.TuringAutomaton;
import turing.structs.TuringData;

@SuppressWarnings("serial")
public class TuringFrameTrace extends AutomatonFrameTrace{

    public TuringFrameTrace(AutomatonData automatonData, String inputString) {
        super(automatonData, inputString);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void updateAutomatonWithGUIData() {
        // TODO Auto-generated method stub
        TuringData turingData = (TuringData)automatonData;
        myAutomaton = new TuringAutomaton(turingData);
    }

}
