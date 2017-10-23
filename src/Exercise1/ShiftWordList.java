package Exercise1;

import pmp.filter.AbstractFilter;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class ShiftWordList extends DataTransformationFilter2<ArrayList<String>, String> {

    public ShiftWordList(Writeable output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public String read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }

    @Override
    public void write(ArrayList<String> value) throws IOException {

    }

    @Override
    protected String process(ArrayList<String> entity) {
        return null;
    }
}
