package turing.gui;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.main.AutomatonIO;
import automaton.structs.AutomatonData;

public class TuringFrameLoad extends AutomatonFrameLoad{

    @Override
    protected AutomatonFrameLoad createAutomatonFrameLoad() {
        // TODO Is this ok?
        return new TuringFrameLoad();
    }

    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AutomatonIO initializeIOModule() {
        // TODO Auto-generated method stub
        return null;
    }

}
