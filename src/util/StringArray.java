package util;

import java.util.ArrayList;

public class StringArray extends ArrayList<String> {
    
    protected static final String DUMMY_STRING = "dummy";
    
    protected static ArrayList<String> dummyTokensLine(int numberOfTokens){
        ArrayList<String> tokenizedLine = new ArrayList<String>();
        
        // TODO: Try to one-line this?
        for (int i = 0; i < numberOfTokens; i++){
            tokenizedLine.add(DUMMY_STRING);
        }
        
        return tokenizedLine;
    }
}
