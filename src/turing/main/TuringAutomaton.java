package turing.main;

import java.util.ArrayList;

import automaton.main.Automaton;
import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import common.structs.State;
import pushdown.structs.PushdownTransition;
import turing.structs.TuringData;

public class TuringAutomaton extends Automaton {

    public TuringAutomaton(AutomatonData data) {
        super(data);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean correctMachineDefinition(AutomatonData data) {
        // TODO Implement
        return true;
    }

    @Override
    protected AutomatonStatus createInitialStatus() {
        State initialState = ((TuringData) data).getInitialState();
        // TODO
//        return new TuringStatus();
        return null;
    }

    @Override
    protected ArrayList<PushdownTransition> findApplicableTransitionRules(AutomatonStatus ms) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected boolean acceptanceStatus(AutomatonStatus ms) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public AutomatonStatus applyTransition(AutomatonStatus as, PushdownTransition tr) {
        // TODO Auto-generated method stub
        return null;
    }

}
