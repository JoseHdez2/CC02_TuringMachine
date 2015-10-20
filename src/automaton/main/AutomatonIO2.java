package automaton.main;

import java.util.ArrayList;
import java.util.HashSet;

import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import automaton.structs.AutomatonTransition;
import util.TokenizedLines;

// TODO: This class shouldn't even exist.
//  Needed for AutomatonIO to both have abstract methods and be able to be instantiated.
//  AutomatonIO needs to be instantiated to interface with Automaton class and others.
//  AutomatonIO needs to be instantiated because some methods couldn't be static.
//  These methods couldn't be static because they also had to be abstract.
//  Java can't do abstract static methods.

//  This class kind of defeats the point of having these abstract methods
//  (to force inheriting classes to implement)
//  I have to copy these over to inheriting classes since they are not forced.
//  But at least they are in one place.

public class AutomatonIO2 extends AutomatonIO{

    @Override
    protected AutomatonData readPreparedMachineData(TokenizedLines tokLines) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected HashSet<AutomatonTransition> readTransitionRules(TokenizedLines transitionLines) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ArrayList<String> getTransitionAsTokenizedLine(AutomatonTransition at) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ArrayList<String> setOutputStatusTokens(ArrayList<String> tokenizedLine, AutomatonStatus as) {
        // TODO Auto-generated method stub
        return null;
    }

}
