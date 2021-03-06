package Exercise1B;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.io.IOException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class ConstructWordsFilter extends DataCompositionFilter<Character, Word> {

    public ConstructWordsFilter(Writeable<Word> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, Word entity) {

        if (nextVal != null) {
            if (nextVal.equals(' ') || nextVal.equals('\t') || nextVal.equals('\n') || nextVal.equals('\r')) {
                return true;
            }
        }

        entity.addChar(nextVal);

        return false;    // zum senden des Wortes

    }

    @Override
    protected Word getNewEntityObject() {
        return new Word();
    }

    @Override
    protected void beforeSendingData() {

    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
