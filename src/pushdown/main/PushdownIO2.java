package pushdown.main;

import java.util.ArrayList;
import java.util.HashSet;

import automaton.main.AutomatonIO;
import automaton.structs.AutomatonTransition;
import common.structs.State;
import common.structs.Symbol;
import pushdown.structs.PushdownTransition;
import pushdown.structs.PushdownTransitionSet;
import util.TokenizedLines;

public class PushdownIO2 extends AutomatonIO implements PushdownIOConst2 {
    
    @Override
    protected HashSet<AutomatonTransition> readTransitionRules(TokenizedLines transitionLines) {
        PushdownTransitionSet pushdownTransitions = new PushdownTransitionSet();
        
        for (ArrayList<String> tl : transitionLines){
            
            State prevState = new State(tl.get(IN_TRAN_PREV_STATE));
            
            State nextState = new State(tl.get(IN_TRAN_NEXT_STATE));
            
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

}
