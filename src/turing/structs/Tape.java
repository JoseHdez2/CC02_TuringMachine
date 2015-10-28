package turing.structs;

import automaton.structs.Symbol;
import pushdown.structs.SymbolList;
import turing.algo.TuringIOConst;
import util.Debug;

public class Tape implements TuringIOConst{
    private SymbolList tape = new SymbolList();
    private Integer headPos = 0;
    private Symbol blankSymbol = DEFAULT_BLANK;
    
    public Tape(String str){
        this(str, null, null);
    }
    
    public Tape(String str, Symbol blankSymbol){
        this(str, blankSymbol, null);
    }
    
    public Tape(String str, Symbol blankSymbol, Integer headPos){
        tape = new SymbolList(str,"");
//        tape.
//        if(blankSymbol != null) this.blankSymbol = blankSymbol;
        if(headPos != null) this.headPos = headPos;
    }
    
    public void moveHead(Movement mov){
        Debug deb = new Debug(true);
        deb.out("beforeMove%nsize:%d, head:%d",tape.size(),headPos);
        checkHeadIntegrity();
        
        switch(mov.value){
        case LEFT: this.moveHeadLeft(); break;
        case RIGHT: this.moveHeadRight(); break;
        case STOP: break;
        }
        
        deb.out("afterMove%nsize:%d, head:%d",tape.size(),headPos);
    }
    
    public Symbol readSymbolAtHead(){
        return tape.get(headPos);
    }
    
    public Symbol writeSymbolAtHead(Symbol sym){
        return tape.set(headPos, sym);
    }
    
    private void checkHeadIntegrity(){
        if (headPos < 0){
            throw new RuntimeException("OOB head (underflow).");
        }
        if (headPos > tape.size()-1){
            throw new RuntimeException("OOB head (overflow).");
        }
    }
    
    private void moveHeadLeft(){
        
        Debug deb = new Debug(true);
        
        deb.out("Going left.");
        if (headPos.equals(0)){
            deb.out("Would underflow; prepending blank.");
            tape.add(0, blankSymbol);
        } else {
            headPos--;
        }
    }
    
    private void moveHeadRight(){

        Debug deb = new Debug(true);
        
        deb.out("Going right.");
        if (headPos == tape.size()-1){
            deb.out("Would overflow; appending blank.");
            tape.add(blankSymbol);
        }
        headPos++;
    }
    
    public Tape deepEnoughCopy(){
        Debug deb = new Debug(true);
        deb.out("beforeCopy%nsize:%d, head:%d",tape.size(),headPos);
        Tape copyTape = new Tape("", blankSymbol, headPos);
        copyTape.tape.addAll(this.tape);
        deb.out("afterCopy%nsize:%d, head:%d",tape.size(),headPos);
        return copyTape;
    }
    
    public String toString(){
        String str = "";
        for (int i = 0; i < tape.size(); i++){
            if (headPos.equals(i))
                str += String.format("[%s]", tape.get(i));
            else
                str += String.format("%s", tape.get(i));
        }
        return str;
    }
    
    /*
     * Equals and hashCode.
     */
    
    public boolean equals(Object ob){
        if (!super.equals(ob)) return false; // TODO: Hope this is OK.
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
