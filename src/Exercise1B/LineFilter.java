package Exercise1B;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class LineFilter extends DataCompositionFilter<Word, Line> {
    private int length;       // Länge einer Linie
    private Word tmp = null;

    public LineFilter(Writeable<Line> output, int length) throws InvalidParameterException {
        super(output);
        this.length = length;
    }

    @Override
    protected boolean fillEntity(Word nextVal, Line entity) {

        if ( tmp != null ) {
            entity.addWord(tmp);
            tmp = null;
        }

        if ( nextVal != null ) {
            if (nextVal.toString() != null && length >= (nextVal.length() + entity.length())) {
                entity.addWord(nextVal);
                return false;
            } else if(nextVal.toString() != null && length < (nextVal.length() + entity.length())) {
                tmp = nextVal;
            }
        }

        return true;
    }

    @Override
    protected Line getNewEntityObject() {
        return new Line();
    }

    @Override
    protected void beforeSendingData() {

    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
