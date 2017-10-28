package ExerciseB;

import java.util.ArrayList;

/**
 * Created by ClemensB on 28.10.17.
 */
public class Line {
    private StringBuilder sb = new StringBuilder();
    private String line;

    public void addWord(Word w){
        sb.append(w);
        line = sb.toString();
    }

    public int length(){
        return line.length();
    }

    public String toString(){
        return line;
    }
}
