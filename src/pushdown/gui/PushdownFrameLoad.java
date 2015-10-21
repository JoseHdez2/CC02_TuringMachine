package pushdown.gui;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import pushdown.structs.PushdownData;

@SuppressWarnings("serial")
public class PushdownFrameLoad extends AutomatonFrameLoad{

    
    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        // TODO: Nasty typecast, for the sake of inheritance.
        return new PushdownFrameTrace((PushdownData)automatonData, inputString);
    }

    @Override
    protected AutomatonData readDataFromFile(String fullFilePath) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AutomatonData initializeAutomatonData() {
        return new PushdownData();
    }
}
