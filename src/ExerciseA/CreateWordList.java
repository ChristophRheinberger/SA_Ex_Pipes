package ExerciseA;

import ExerciseB.Line;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class CreateWordList extends DataTransformationFilter2<Line, ArrayList<String>> {
    protected int line = 1;

    public CreateWordList(Writeable<ArrayList<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(Line entity) throws FileNotFoundException, StreamCorruptedException {

        ArrayList<String> inputList = new ArrayList();

        if(entity.toString() != null) {

            String input = entity.toString();

            input = input.replaceAll("[\\W\\d_]+", " ");
            input = input.replaceAll("\\s\\s", "");
            input = input.trim();

            String[] inputArray = input.split(" ");

            for (String s : inputArray) {
                if (!input.isEmpty() || !input.equals("")) {
                    inputList.add(s);
                }
            }

            inputList.add(String.valueOf(line));

            line++;
        }
        return inputList;
    }
}