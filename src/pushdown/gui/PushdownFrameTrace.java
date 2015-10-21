package pushdown.gui;

import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import pushdown.algo.PushdownAutomaton;
import pushdown.structs.PushdownData;

public class PushdownFrameTrace extends AutomatonFrameTrace {

    public PushdownFrameTrace(AutomatonData automatonData, String inputString) {
        super(automatonData, inputString);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void updateAutomatonWithGUIData() {
        // TODO Auto-generated method stub
        PushdownData pushdownData = (PushdownData)automatonData;
        myAutomaton = new PushdownAutomaton(pushdownData);
    }

}
