package Exercise1;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class CreateWordList extends AbstractFilter<String, ArrayList<String>> {

    public CreateWordList(Writeable<ArrayList<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public void write(String value) throws StreamCorruptedException {

    }

    @Override
    public ArrayList read() throws StreamCorruptedException, FileNotFoundException {

        return null;
    }
}
