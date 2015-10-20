package pushdown.gui;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import pushdown.structs.PushdownData;

public class PushdownFrameLoad extends AutomatonFrameLoad{

    @Override
    protected AutomatonFrameLoad createAutomatonFrameLoad() {
        return new PushdownFrameLoad();
    }

    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        // TODO: Nasty typecast, for the sake of inheritance.
        return new PushdownFrameTrace((PushdownData)automatonData, inputString);
    }
}
