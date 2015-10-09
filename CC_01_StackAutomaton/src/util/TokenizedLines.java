package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jose
 *
 *	ArrayList<ArrayList<String>> class renaming.
 */
public class TokenizedLines extends ArrayList<ArrayList<String>>{

    public TokenizedLines(){
        super();
    }
    
    public TokenizedLines(List<ArrayList<String>> subList) {
        super(subList);
    }
}
