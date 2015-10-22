package turing.algo;

import automaton.algo.Automaton;
import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import automaton.structs.AutomatonTransition;
import automaton.structs.State;
import turing.structs.TuringData;
import turing.structs.TuringStatus;
import turing.structs.TuringTransition;
import turing.structs.TuringTransitionSet;

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
    protected TuringTransitionSet findApplicableTransitionRules(AutomatonStatus as) {
        
        TuringStatus ts = (TuringStatus)as;
        
        TuringTransitionSet applicableTransitions = new TuringTransitionSet();
        
        for (AutomatonTransition tr : data.getTransitionRules()){
            TuringTransition ttr = (TuringTransition)tr;
            if (!tr.getPrevState().equals(ts.getCurrentState())) continue;
            if (!tr.getInputCharacter().equals(null)) continue;
            
            applicableTransitions.add(ttr);
        }
        return null;
    }

    @Override
    protected boolean acceptanceStatus(AutomatonStatus ms) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public TuringStatus applyTransition(AutomatonStatus as, AutomatonTransition tr) {
        // TODO Auto-generated method stub
        return null;
    }

}
