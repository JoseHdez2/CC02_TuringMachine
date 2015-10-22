package turing.gui;

import java.io.IOException;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import turing.structs.TuringData;

@SuppressWarnings("serial")
public class TuringFrameLoad extends AutomatonFrameLoad{

    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        // TODO Auto-generated method stub
        return new TuringFrameTrace(automatonData, inputString);
    }

    @Override
    protected AutomatonData readDataFromFile(String fullFilePath) {
        // TODO Auto-generated method stub
        try {
            return new TuringData(fullFilePath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//            return new TuringData();
            return null;
        }
    }

    @Override
    protected AutomatonData initializeAutomatonData() {
        // TODO Auto-generated method stub
        return new TuringData();
    }

}
