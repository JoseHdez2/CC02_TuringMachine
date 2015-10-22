package turing.structs;

import automaton.structs.Symbol;
import pushdown.structs.SymbolList;
import turing.algo.TuringIOConst;

@SuppressWarnings("serial")
public class Tape extends SymbolList implements TuringIOConst{
    private Integer headPos = 0;
    private Symbol blankSymbol;
    
    public Tape(String str){
        this(str, null, null);
    }
    
    public Tape(String str, Symbol blankSymbol){
        this(str, blankSymbol, null);
    }
    
    public Tape(String str, Symbol blankSymbol, Integer headPos){
        super(str,"");
        this.blankSymbol = (blankSymbol == null) ? DEFAULT_BLANK : blankSymbol;
        this.headPos = (headPos == null) ? 0 : headPos;
    }
    
    public Tape moveHead(Movement mov){
        Tape newTape = deepEnoughCopy();
        
        switch(mov.value){
        case LEFT: newTape.moveLeft();
        case RIGHT: newTape.moveRight();
        case STOP: break;
        }
        return newTape;
    }
    
    public Symbol getSymbolAtHead(){
        return this.get(headPos);
    }
    
    private void moveLeft(){
        headPos--;
        if(headPos < 0){
            this.add(0, blankSymbol);
            headPos = 0;
        }
    }
    
    private void moveRight(){
        headPos++;
        if(headPos >= this.size()){
            this.add(blankSymbol);
            headPos = this.size()-1;
        }
    }
    
    private Tape deepEnoughCopy(){
        Tape copyTape = new Tape("", blankSymbol, headPos);
        copyTape.addAll(this);
        return copyTape;
    }
}
