package turing.structs;

import automaton.structs.Symbol;
import pushdown.structs.SymbolList;
import turing.algo.TuringIOConst;

@SuppressWarnings("serial")
public class Tape extends SymbolList implements TuringIOConst{
    private Integer headPos = 0;
    private Symbol blankSymbol = DEFAULT_BLANK;
    
    public Tape(String str){
        this(str, null, null);
    }
    
    public Tape(String str, Symbol blankSymbol){
        this(str, blankSymbol, null);
    }
    
    public Tape(String str, Symbol blankSymbol, Integer headPos){
        super(str,"");
        if(blankSymbol != null) this.blankSymbol = blankSymbol;
        if(headPos != null) this.headPos = headPos;
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
    
    /*
     * Equals and hashCode.
     */
    
    public boolean equals(Object ob){
        if (!super.equals(ob)) return false; // TODO: Hope this is ok.
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Tape other = (Tape)ob;
        if (!headPos.equals(other.headPos)) return false;
        if (!blankSymbol.equals(other.blankSymbol)) return false;

        return true;
    }
    
    public int hashCode() {
        return  super.hashCode() ^
                headPos.hashCode() ^
                blankSymbol.hashCode();
    }
}
