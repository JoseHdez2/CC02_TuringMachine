package pushdown.gui;

import java.io.IOException;

import javax.swing.JTable;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import pushdown.structs.PushdownData;
import util.TokenizedLines;

@SuppressWarnings("serial")
public class PushdownFrameLoad extends AutomatonFrameLoad{
    
    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        // TODO: Nasty typecast, for the sake of inheritance.
        return new PushdownFrameTrace((PushdownData)automatonData, inputString);
    }

    @Override
    protected AutomatonData readDataFromFile(String fullFilePath) {
        try {
            return new PushdownData(fullFilePath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//            return new PushdownData();
            return null;
        }
    }

    @Override
    protected AutomatonData initializeAutomatonData() {
        return new PushdownData();
    }

    @Override
    protected JTable initializeTransitionTable() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected TokenizedLines getTransitionLines() {
        // TODO Auto-generated method stub
        return null;
    }
}
