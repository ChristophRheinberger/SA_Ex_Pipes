package Exercise1;

import pmp.filter.AbstractFilter;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by Christoph on 23.10.2017.
 */
public class SortListFilter extends AbstractFilter <String, String> {

    public SortListFilter(Writeable output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public String read() throws StreamCorruptedException, FileNotFoundException {
        return null;
    }

    @Override
    public void write(String value) throws IOException {

    }
}
