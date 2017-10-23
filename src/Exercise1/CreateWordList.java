package Exercise1;

import pmp.filter.AbstractFilter;
import pmp.filter.DataTransformationFilter1;
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


    public CreateWordList(Writeable<ArrayList<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(String entity) {
        return null;
    }
}