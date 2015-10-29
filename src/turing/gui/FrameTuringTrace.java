package turing.gui;

import automaton.main.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import turing.structs.TuringData;

@SuppressWarnings("serial")
public class FrameTuringTrace extends AutomatonFrameTrace{

    public FrameTuringTrace(AutomatonData automatonData, String inputString) {
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
