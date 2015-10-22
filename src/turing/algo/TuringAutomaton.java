package turing.algo;

import automaton.algo.Automaton;
import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import automaton.structs.AutomatonTransition;
import automaton.structs.State;
import turing.structs.Tape;
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
    protected AutomatonStatus createInitialStatus(String inputString) {
        State newState = data.getInitialState();
        Tape newTape = new Tape(inputString, ((TuringData)data).getBlankSymbol());
        
        return new TuringStatus(newState, newTape);
    }

    @Override
    protected TuringTransitionSet findApplicableTransitionRules(AutomatonStatus as) {
        
        TuringStatus ts = (TuringStatus)as;
        
        TuringTransitionSet applicableTransitions = new TuringTransitionSet();
        
        for (AutomatonTransition tr : data.getTransitionRules()){
            TuringTransition ttr = (TuringTransition)tr;
            if (!tr.getPrevState().equals(ts.getCurrentState())) continue;
            if (!tr.getInputCharacter().equals(ts.getTape().readSymbolAtHead())) continue;
            
            applicableTransitions.add(ttr);
        }
        return applicableTransitions;
    }

    @Override
    protected boolean acceptanceStatus(AutomatonStatus as) {
        TuringStatus ts = (TuringStatus)as;
        
        if (!data.getAcceptStates().contains(ts.getCurrentState())) return false;
        if (!findApplicableTransitionRules(as).isEmpty()) return false;
        return true;
    }

    @Override
    public TuringStatus applyTransition(AutomatonStatus as, AutomatonTransition tr) {
        TuringStatus ts = (TuringStatus)as;
        TuringTransition ttr = (TuringTransition)tr;
        
        State newState = ttr.getNextState();
        Tape newTape = ts.getTape().deepEnoughCopy();
        newTape.moveHead(ttr.getMovement());
        newTape.writeSymbolAtHead(ttr.getOutputCharacter());
        
        return new TuringStatus(newState, newTape);
    }

}
