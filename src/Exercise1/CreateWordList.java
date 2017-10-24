package Exercise1;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class CreateWordList extends DataTransformationFilter2<String, ArrayList<String>> {
    protected int line = 1;

    public CreateWordList(Writeable<ArrayList<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(String entity) throws FileNotFoundException, StreamCorruptedException {
        String input = entity;

        String[] inputArray = input.split(" ");
        ArrayList<String> inputList = new ArrayList<>();

        for ( String s : inputList ) {
            inputList.add(s);
        }

        inputList.add(String.valueOf(line));

        line++;

        return inputList;
    }
}