package pushdown.main;

import automaton.main.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import pushdown.structs.PushdownData;

public class FramePushdownTrace extends AutomatonFrameTrace {

    public FramePushdownTrace(AutomatonData automatonData, String inputString) {
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
