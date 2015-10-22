package turing.structs;

import pushdown.structs.SymbolList;

@SuppressWarnings("serial")
public class Tape extends SymbolList{
    Integer headPos = 0;
    
    public Tape(String str){
        this(str, null);
    }
    
    public Tape(String str, Integer headPos){
        super(str,"");
    }
}
