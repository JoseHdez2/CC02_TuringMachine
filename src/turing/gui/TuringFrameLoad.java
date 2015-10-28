package turing.gui;

import java.io.IOException;

import javax.swing.JTable;

import automaton.gui.AutomatonFrameLoad;
import automaton.gui.AutomatonFrameTrace;
import automaton.structs.AutomatonData;
import turing.structs.TuringData;
import turing.structs.TuringTransitionSet;
import util.TokenizedLines;

@SuppressWarnings("serial")
public class TuringFrameLoad extends AutomatonFrameLoad implements TuringGUIConst{

    @Override
    protected AutomatonFrameTrace createAutomatonFrameTrace(AutomatonData automatonData, String inputString) {
        return new TuringFrameTrace(automatonData, inputString);
    }

    @Override
    protected AutomatonData readDataFromFile(String fullFilePath) {
        try {
            return new TuringData(fullFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected AutomatonData initializeAutomatonData() {
        return new TuringData();
    }

    @Override
    protected JTable initializeTransitionTable() {
        return new JTable(TuringGUIConst.tableTransDummyData, TuringGUIConst.tableTransColumns[0]);
    }

    @Override
    protected TokenizedLines getTransitionLines() {
        TuringData td = (TuringData)automatonData;
        TuringTransitionSet tts = (TuringTransitionSet)td.getTransitionRules();
        return tts.asStringMatrix();
    }

}
