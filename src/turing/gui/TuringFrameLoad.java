package turing.gui;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import turing.structs.TuringData;

@SuppressWarnings("serial")
public class TuringFrameLoad extends AutomatonFrameLoad{

    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AutomatonData readDataFromFile(String fullFilePath) {
        // TODO Auto-generated method stub
        return new TuringData(fullFilePath);
    }

    @Override
    protected AutomatonData initializeAutomatonData() {
        // TODO Auto-generated method stub
        return new TuringData();
    }

}
