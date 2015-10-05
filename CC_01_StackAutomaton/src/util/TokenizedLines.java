package util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author jose
 *
 *	Dumb typedef to not write ArrayList<ArrayList<String>> ever.
 */
public class TokenizedLines extends ArrayList<ArrayList<String>>{
    
    /**
     * Useful for using the calling object as JTable data.
     * @return  Itself as a matrix of literals.
     */
    public String[][] intoStringLiteralMatrix(){
        ArrayList<String[]> literalStrList = new ArrayList<String[]>();
        for (ArrayList<String> al : this){
            literalStrList.add((String[]) al.toArray());
        }
        return (String[][])literalStrList.toArray();
    }
}
