package ExerciseB;

import java.util.ArrayList;

/**
 * Created by ClemensB on 28.10.17.
 */
public class Word {
    private StringBuilder sb = new StringBuilder();
    String word;

    public void addChar(Character c){
        sb.append(c);
        word = sb.toString();
    }

    public int length(){
        return word.length();
    }

    public String toString(){
        return word;
    }
}
