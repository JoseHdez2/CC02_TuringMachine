package pushdown.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import automaton.main.AutomatonIO;
import automaton.structs.AutomatonData;
import automaton.structs.AutomatonStatus;
import automaton.structs.AutomatonTransition;
import common.structs.State;
import common.structs.Symbol;
import pushdown.structs.PushdownTransition;
import pushdown.structs.PushdownTransitionSet;
import util.TokenizedLines;

public class PusdownIO2 extends AutomatonIO implements PushdownIOConst2 {
    
    /*
     * ABSTRACT METHODS
     * 
     * To be implemented by inheriting classes.
     */
    
    /*
     * Abstract input methods.
     */
    
    /**
     * Reads prepared automaton data from a given file.
     * @param fileName Path to machine definition file.
     * @return Data structure that semantically represents the automaton definition.
     * @throws IOException
     */
    protected static AutomatonData readPreparedMachineData(TokenizedLines tokLines) {
        return null;
    }
    
    /**
     * Reads transition rules from an array of string arrays.
     * @param transitionLines  Lines with transition tokens.
     * @return Data structure that semantically represents a set of transition rules.
     */
    static protected HashSet<AutomatonTransition> readTransitionRules(TokenizedLines transitionLines) {
        PushdownTransitionSet pushdownTransitions = new PushdownTransitionSet();
        
        for (ArrayList<String> tl : transitionLines){
            
            State prevState = new State(tl.get(PushdownIOConst.IN_TRAN_PREV_STATE));
            
            State nextState = new State(tl.get(PushdownIOConst.IN_TRAN_NEXT_STATE));
            
            Character requiredInputCharacter = tl.get(IN_TRAN_REQ_INP_CHAR).charAt(0);
            
            Symbol requiredStackSymbol = new Symbol(tl.get(IN_TRAN_REQ_STACK_SYM));
            
            // Read each of the symbols to push.
            ArrayList<Symbol> stackSymbolsToPush = new ArrayList<Symbol>();
            String[] stackCharsToPush = tl.get(IN_TRAN_STACK_SYM_TO_PUSH).split(",");
            for (String str : stackCharsToPush){
                stackSymbolsToPush.add(new Symbol(str));
            }
            
            pushdownTransitions.add(new PushdownTransition(prevState, nextState, 
                    requiredInputCharacter, requiredStackSymbol, stackSymbolsToPush));
        }
        
        return pushdownTransitions;
    }
    
    /*
     * Abstract output methods.
     */
    
    /**
     * Produce the representation of a transition, according to the internal IO convention.
     * @param tr Transition rule to be represented.
     * @return Array of strings representing the given transition rule.
     */
    protected static ArrayList<String> getTransitionAsTokenizedLine(AutomatonTransition at) {
        return null;
    }
    
    /**
     * Given a tokenized line with the correct number of (dummy) tokens, 
     * write the tokens representing the AutomatonStatus in the correct order.
     * @param tokenizedLine Line with dummy tokens to be replaced.
     * @param as AutomatonStatus to be represented.
     * @return Line with meaningful tokens representing the given automaton status.
     */
    protected static ArrayList<String> setOutputStatusTokens(ArrayList<String> tokenizedLine, AutomatonStatus as) {
        return null;
    }

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

    @Override
    protected Integer getTransOutputSize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Integer getStatusOutputSize() {
        // TODO Auto-generated method stub
        return null;
    }
}
